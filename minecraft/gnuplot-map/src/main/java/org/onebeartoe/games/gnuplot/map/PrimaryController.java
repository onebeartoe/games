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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class PrimaryController 
{
    @FXML
    public TextField xField;

    @FXML
    public TextField yField;
    
    @FXML
    public Button inputDirectoryButton;
    
    private DirectoryChooser directoryChooser = new DirectoryChooser();
    
    private List<MapMarker> mapMarkers;

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
            
            loadInputFiles(selectedDirectory);
        });
        
        directoryChooser.setInitialDirectory(new File("/"));        
    }
    
    @FXML
    private void switchToSecondary() throws IOException 
    {
        App.setRoot("secondary");
    }

    private void loadInputFiles(File selectedDirectory) throws IOException 
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
        
        mapMarkers = parseMapMarkers(dataFiles);
    }

    private List<MapMarker> parseMapMarkers(List<Path> dataFiles) 
    {
        var allMarkers = new ArrayList<MapMarker>();
        
        for(Path infile : dataFiles)
        {
            var markers = parseOneMapMarkerFile(infile);
            
            allMarkers.addAll(markers);
        }
        
        return allMarkers;
    }

    private List<MapMarker> parseOneMapMarkerFile(Path infile) 
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
