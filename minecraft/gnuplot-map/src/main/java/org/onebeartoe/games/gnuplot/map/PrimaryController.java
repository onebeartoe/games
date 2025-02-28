package org.onebeartoe.games.gnuplot.map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static org.onebeartoe.games.gnuplot.map.App.INPUT_DIRECORTY_KEY;
import static org.onebeartoe.games.gnuplot.map.App.TARGET_X_KEY;
import static org.onebeartoe.games.gnuplot.map.App.TARGET_Y_KEY;

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
    public TextArea mapMarkersTextArea;
    
    @FXML
    public ScrollPane mapMarkersScrollPane;
    
    @FXML
    public TextArea outputTextArea;
    
//    private File selectedDirectory;
    
//    private ObservableList<String> inputFileItems;
    
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    
    private List<MapMarker> mapMarkers;

    private GnuplotDataVerification dataVerification = new GnuplotDataVerification();
    
    @FXML
    public void initialize() 
    {
//        inputFileItems = FXCollections.observableArrayList();
        
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
        
//        xField.setTextFormatter(numericFormater);
   
        xField.setOnKeyReleased((t) -> 
        {
            updateMarkers();
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

//        yField.setTextFormatter(yNumericFormater);

        yField.setOnKeyReleased((t) -> 
        {
            updateMarkers();
            
            
        });
        
        var x = App.preferences.getInt(TARGET_X_KEY, 0);
        xField.setText(String.valueOf(x));
        
        
        var y = App.preferences.getInt(TARGET_Y_KEY, 0);
        yField.setText(String.valueOf(y));
        
        inputDirectoryButton.setOnAction((t) -> 
        {
            Stage primaryStage = App.stage;
            
            File selectedDirectory = directoryChooser.showDialog(primaryStage);

            try {
                addFolder(directoryChooser, selectedDirectory);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
//TODO: rename the key            
            App.preferences.put(INPUT_DIRECORTY_KEY, selectedDirectory.getAbsolutePath());

            loadInputFiles();
        });
                
        String inputPath = App.preferences.get(INPUT_DIRECORTY_KEY, "bad-direcotry-path");
        
        var initialDirectory = new File(inputPath);
        
        if(initialDirectory.exists() && initialDirectory.isDirectory())
        {
            directoryChooser.setInitialDirectory(initialDirectory);
            
//            selectedDirectory = initialDirectory;
        }
        else
        {
            var selectedDirectory = new File("/home/roberto/Versioning/owner/github/games/minecraft/saves/dragon-fart-2000/maps/");
            
            directoryChooser.setInitialDirectory(selectedDirectory);        
            
            outputTextArea.appendText("\n--------------------\ninitialization error\n");
            outputTextArea.appendText("saved prefered input directory is set but does not exists");
            outputTextArea.appendText("\n\t" + inputPath + "\n\n");
        }
        
        loadInputFiles();
    }
    
    
    
    @FXML
    private void addFile()
    {
        var chooser = new FileChooser();
        
        File file = chooser.showOpenDialog(App.stage);
        
        addFile(file);
    }
    
    public void addFile(File file)
    {
//        inputFileItems.add(file.getAbsolutePath());
        
        inputFilesListView.getItems().addAll(file.getPath());
//        inputFilesListView.getItems().addAll(file.getAbsolutePath());
        
//        selectedDirectory = file;

        loadInputFiles();
    }
    
    @FXML
    private void clearInputFilesList()
    {
System.out.println("jkjfajdfkj;fjsfkja;fjasdjfa;sjflaskdjf;asdjfakls;j");        
        inputFilesListView.getItems().clear();
    }
    
    @FXML
    private void switchToSecondary() throws IOException 
    {
        App.setRoot("secondary");
    }
    
//TODO: rename to updateMarkers()    
    private void loadInputFiles()
    {
//        System.out.println(selectedDirectory.getAbsolutePath());

//        try 
//        {


//!!!!!!!!!!            
//            inputFileItems.addAll(filesToText);//!!!!!!!!!!!
//            ObservableList<String> inputFileItems = FXCollections.observableArrayList(filesToText);

//inputFilesListView.seti
//            inputFilesListView.getItems().clear();

List<String> inputFileItems = inputFilesListView.getItems();

//            inputFilesListView.getItems().addAll(inputFileItems);

            mapMarkers = parseMapMarkers( new ArrayList<Path>(inputFileItems.stream()
                                                        .map(i -> Path.of(i) )
                                                        .collect(Collectors.toList() ) ));
                                                            
//            mapMarkers = parseMapMarkers(inputFiles);

            boolean containsInvalid = false;

            for(var marker : mapMarkers)
            {
                if( !marker.valid() )
                {
                    containsInvalid = true;

                    break;
                }
            };

            if(containsInvalid)
            {
                outputTextArea.appendText("-------------------------\ncheck input files for errors\n---------------------\n");

                mapMarkers.forEach(marker ->
                {
                    if(!marker.valid())
                    {
                        var message = marker.id() + "\n" + 
                                        marker.description() + "\n" +
                                        "-----------------\n";

                        outputTextArea.appendText(message);
                    }
                });
            }
            else
            {
                updateMapMarkersDispaly(mapMarkers);

            }
//        }
//        catch (IOException ex) 
//        {
//            ex.printStackTrace();
//        }        
    }
    
    public void addFolder(DirectoryChooser chooser, File file) throws IOException
    {
            List<Path> inputFiles = findInputFilesUnder(file);
//            List<Path> inputFiles = findInputFilesUnder(selectedDirectory);

            List<String> filesToText = inputFiles.stream()
                                                 .map(path -> path.toFile().getPath() )
                                                 .toList();        
        
            inputFilesListView.getItems().addAll(filesToText);
//            inputFileItems.addAll(filesToText);
        
//        selectedDirectory = file;

        loadInputFiles();
                
        System.out.println("chooser is re-set");
    }

    private List<Path> findInputFilesUnder(File selectedDirectory) throws IOException 
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
                
                markers.forEach(marker -> 
                {
                    if(marker.valid())
                    {
                        allMarkers.add(marker);
                    }
                    else
                    {
                        var message = "errors here: " + marker.id() +
                                      "\n" + marker.description() + 
                                      "\n-----------------\n";
                        
                        outputTextArea.appendText(message);
                    }
                });

            } 
            catch (IOException ex) 
            {
                var message = "Error in infile: " + infile.toString() + "\n" + 

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
        
        return markers;
    }

    private String toString(MapMarker marker) 
    {
        return marker.id() + "\n" +
                marker.location() + "\n" +
                marker.description() + "\n\n";   
    }

    private void updateMarkers() 
    {
        System.out.println("ploop");
        
        var x = Integer.valueOf( xField.getText() );
        
        var y = 0;
        
        var z = Integer.valueOf( yField.getText() );
        
        var origin = new Point3D(x, y, z);
        
        List closestPoints = MapMarkers.closestPoints(origin, mapMarkers);

        updateMapMarkersDispaly(closestPoints);
    }

    private void updateMapMarkersDispaly(List<MapMarker> updatedMapMarkers) 
    {
        mapMarkersTextArea.setText("");
        
        updatedMapMarkers.forEach(marker -> 
        {
            var text = toString(marker);

            mapMarkersTextArea.appendText(text);
        });
    }
}
