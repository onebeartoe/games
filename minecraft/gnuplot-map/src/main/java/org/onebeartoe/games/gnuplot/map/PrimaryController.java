package org.onebeartoe.games.gnuplot.map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import static org.onebeartoe.games.gnuplot.map.App.INPUT_DIRECORTY_KEY;

public class PrimaryController 
{
    @FXML
    public TextField xField;

    @FXML
    public TextField yField;
    
    @FXML
    public ListView inputFilesListView;
    
    @FXML
    public Button inputDirectoryButton;
    
    @FXML
    public VBox mapMarkersVbox;
    
    @FXML
    public ScrollPane mapMarkersScrollPane;
    
    @FXML
    public TextArea outputTextArea;
    
    private File selectedDirectory;
    
    private DirectoryChooser directoryChooser = new DirectoryChooser();
    
    private List<MapMarker> mapMarkers;

    private GnuplotDataVerification dataVerification = new GnuplotDataVerification();
    
    @FXML
    public void initialize()
    {
        var numericFormater = new TextFormatter<>(c -> 
        {
            if (!c.getControlNewText().matches("\\d*")) 
            {
                return null;
            }
            else
            {
                return c;
            }            
        });
        
        xField.setTextFormatter(numericFormater);
   
        xField.setOnKeyReleased((t) -> 
        {
            System.out.println("ploop");
        });

        var yNumericFormater = new TextFormatter<>(c -> 
        {
            if (!c.getControlNewText().matches("\\d*")) 
            {
                return null;
            }
            else
            {
                return c;
            }            
        });

        yField.setTextFormatter(yNumericFormater);

        yField.setOnKeyReleased((t) -> 
        {
            System.out.println("yloop");
        });
        
        inputDirectoryButton.setOnAction((t) -> 
        {
            Stage primaryStage = App.stage;
            
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            
            App.preferences.put(INPUT_DIRECORTY_KEY, selectedDirectory.getAbsolutePath());

            loadInputFiles();
        });
        
        
        String inputPath = App.preferences.get(INPUT_DIRECORTY_KEY, "bad-direcotry-path");
        
        var initialDirectory = new File(inputPath);
        
        if(initialDirectory.exists() && initialDirectory.isDirectory())
        {
            directoryChooser.setInitialDirectory(initialDirectory);
            
            selectedDirectory = initialDirectory;
        }
        else
        {
            selectedDirectory = new File("/home/roberto/Versioning/owner/github/games/minecraft/saves/dragon-fart-2000/maps/");
            
            directoryChooser.setInitialDirectory(selectedDirectory);        
            
            outputTextArea.appendText("\n--------------------\ninitialization error\n");
            outputTextArea.appendText("saved prefered input directory is set but does not exists");
            outputTextArea.appendText("\n\t" + inputPath + "\n\n");
        }
        
        loadInputFiles();
        
    }
    
    @FXML
    private void switchToSecondary() throws IOException 
    {
        App.setRoot("secondary");
    }
    
    private void loadInputFiles()
    {
        
            System.out.println(selectedDirectory.getAbsolutePath());
            
            try 
            {
                List<Path> inputFiles = loadInputFiles(selectedDirectory);
                
                List<String> filesToText = 
                        inputFiles.stream()
                                                .map(path -> 
                                                
                                                    path.toFile().getPath()
                                                )
                                                .toList();
                
                ObservableList<String> items = FXCollections.observableArrayList(filesToText);
                
                inputFilesListView.getItems().addAll(items);
                
                mapMarkers = parseMapMarkers(inputFiles);
                
                mapMarkers.forEach(marker -> 
                {
                    var text = new StringBuffer(marker.toString());
                    
                    var textArea = new TextArea(text.toString());
                    
                    mapMarkersVbox.getChildren()
                                    .add(textArea);
                });
                            
            }
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }        
    }

    private List<Path> loadInputFiles(File selectedDirectory) throws IOException 
    {
        var start = selectedDirectory.toPath();
        
        List<Path> dataFiles;
        
        try (Stream<Path> walk = Files.walk(start)) 
        {
            var fileExtension = ".data";
            
            dataFiles = walk.filter(Files::isRegularFile)   
                            .filter(p -> p.getFileName().toString().endsWith(fileExtension))
                            .collect(Collectors.toList());
        }

        return dataFiles;
    }

    private List<MapMarker> parseMapMarkers(List<Path> dataFiles)// throws IOException 
    {
        var allMarkers = new ArrayList<MapMarker>();
        
        for(Path infile : dataFiles)
        {
            
            try 
            {
                var markers = parseOneMapMarkerFile(infile);
                
                markers.forEach(marker -> {
                    if(marker.valid())
                    {
                                        allMarkers.addAll(markers);
                    }
                    else
                    {
                        var message = "errors here: " + marker.id() +
                                      "\n" + marker.description() + 
                                      "\n-----------------\n";
                        
                        outputTextArea.appendText(message);
                    }
                });
                       
                        
                
//                allMarkers.addAll(markers);
            } 
            catch (IOException ex) 
            {
                var message = "Error in infile: " + infile.toString() + "\n" + 

//                               " with line: " + 
                        ex.getMessage();
                
                outputTextArea.appendText(message);
                
                outputTextArea.appendText("\n\n------------------------\n\n");
                
                ex.printStackTrace();
            }
        }
        
        return allMarkers;
    }

    private List<MapMarker> parseOneMapMarkerFile(Path infile) throws IOException 
    {
        MapMarkerParser parser = new MapMarkerParser();
        
        List<MapMarker> markers = parser.parse(infile.toFile());
        
        
//        List<String> allLines = Files.readAllLines(infile);
//        
//        var errors = new StringBuffer();       
//        
//        List<MapMarker> markers = allLines.stream()
//                .map(line -> 
//                {
//                    MapMarker marker =null;
//                    try
//                    {
//                        marker = dataVerification.isValid(line) ;
//                    }
//                    catch(NullPointerException npe)
//                    {
//                        var error = npe.getMessage() + "\n__________________\n";
//                        
//                        errors.append(error);
//                    }
//                    
//                    return marker;
//                })
//                .toList();
        
//        if(errors.length() > 0)
//        {
//            throw new IllegalArgumentException(errors.toString() + "\n\n");
//        }
        
        
        return markers;
    }
}
