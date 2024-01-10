package org.onebeartoe.desktop;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * The background "splash" animation is actually setup/started in the 
 * JavaFX application's main() class in the @Override start() method.
 */
public class SuperSecretOptionsController 
{
    @FXML
    public MediaView mediaView;
    
    @FXML
    public VBox vBox;
    
    @FXML
    Button backButton;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException
    {
        String uri = loadBackgroundSoundUri();
   
        final Media sound = new Media(uri);

        final MediaPlayer mediaPlayer = new MediaPlayer(sound);

        mediaPlayer.setAutoPlay(true);

        mediaPlayer.play();        
    }
    
    String loadBackgroundSoundUri() throws URISyntaxException
    {
        URL resource = ClassLoader.getSystemClassLoader().getResource("org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version.wav");
        
        String uri = resource.toURI().toString();        
        
        return uri;
    }
    
    @FXML
    private void switchToGameMenu() throws IOException 
    {        
        App.setRoot("game-menu");
    }
    
    @FXML
    private void switchToPlay() throws IOException 
    {
        System.out.println("super secret -> 0ijgu94jf: " + mediaView);
        
        App.setRoot("play");
    }    
}