package org.onebeartoe.desktop;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import org.json.simple.parser.ParseException;

import org.onebeartoe.minecraft.advancements.v1_20.MinecraftWildAdvancementsService;

/**
 * The background "splash" animation is actually setup/started in the 
 * JavaFX application's main() class in the @Override start() method.
 */
public class AdvancementsController 
{
    @FXML
    public MediaView mediaView;
    
    @FXML
    public VBox vBox;
    
    @FXML
    Button singlePlayerButton;
    
    @FXML
    TextArea havesTextArea;
    
    private MinecraftWildAdvancementsService implementation;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException, ParseException
    {
//TODO: use parent node and child node width and height binding as seen here
//          https://stackoverflow.com/questions/42774863/how-to-call-a-mediaview-from-another-fxml-file        
     
        implementation = new MinecraftWildAdvancementsService();

        System.out.println("mediaView: " + mediaView);
        
        URL resource = App.class.getResource("y2meta.com-Rick-Rolled-Short-Version.mp4");

        String path = resource.toURI().toString();
        
        System.out.println("path = " + path);
        
        Media media = new Media(path);  
          
        List<String> allAdventureTimes = implementation.allAdventureTimes();
        
        havesTextArea.setText(allAdventureTimes.toString());
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        System.out.println("mediaView again: " + mediaView);
        
        App.setRoot("play-selected-world");
    }
}