package org.onebeartoe.desktop;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;

/**
 * The background "splash" animation is actually setup/started in the 
 * JavaFX application's main() class in the @Override start() method.
 */
public class SplashController 
{
    @FXML
    public MediaView mediaView;
    
    @FXML
    public VBox vBox;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException
    {
//TODO: use parent node and child node width and height binding as seen here
//          https://stackoverflow.com/questions/42774863/how-to-call-a-mediaview-from-another-fxml-file        

        System.out.println("mediaView: " + mediaView);
        
        URL resource = App.class.getResource("y2meta.com-Rick-Rolled-Short-Version.mp4");

        String path = resource.toURI().toString();
        
        System.out.println("path = " + path);        
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        System.out.println("mediaView again: " + mediaView);

        App.setRoot("launcher");
    }
}