package org.onebeartoe.desktop;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
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
    ImageView imageView;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException
    {
//        imageView.setFitWidth(680);
        
//        imageView.fitWidthProperty().bind(scene.widthProperty());
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        var root = "launcher";
        
        System.out.println("switching to: " + root);

        App.setRoot(root);
    }
}