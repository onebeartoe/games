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
                
//                Label l;
                
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
        });
        
        directoryChooser.setInitialDirectory(new File("/"));        
    }
    
    @FXML
    private void switchToSecondary() throws IOException 
    {
        App.setRoot("secondary");
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
                
                allMarkers.addAll(markers);
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
        List<String> allLines = Files.readAllLines(infile);
        
        List<MapMarker> markers = allLines.stream()
                .map(line -> dataVerification.isValid(line) )
                .toList();
        
        return markers;
    }
}
