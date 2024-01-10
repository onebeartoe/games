package org.onebeartoe.desktop;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;

/**
 * The background "splash" animation is actually setup/started in the 
 * JavaFX application's main() class in the @Override start() method.
 */
public class SelectWorldController 
{
    @FXML
    public MediaView mediaView;
    
    @FXML
    public VBox vBox;
    
    @FXML
    Button singlePlayerButton;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException
    {
//?????????
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        System.out.println("select world -> play selected world: " + mediaView);
        
        App.setRoot("game-menu");
    }
}