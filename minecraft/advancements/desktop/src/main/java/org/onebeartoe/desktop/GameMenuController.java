package org.onebeartoe.desktop;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;

/**
 * The background "splash" animation is actually setup/started in the 
 * JavaFX application's main() class in the @Override start() method.
 */
public class GameMenuController 
{
    @FXML
    public MediaView mediaView;
    
    @FXML
    public VBox vBox;
    
    @FXML
    Button singlePlayerButton;
    
    @FXML
    Button backButton;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException
    {
//        String css = "-fx-border-color: red;\n" +
//                   "-fx-border-insets: 5;\n" +
//                   "-fx-border-width: 3;\n" +
//                   "-fx-border-style: dashed;\n";
//        
//        vBox.setStyle(css); 
    
//    backButton.prefWidthProperty().bind(Bindings.divide(vBox.widthProperty(), 0.01));
    backButton.prefWidthProperty().bind( vBox.widthProperty().multiply(0.4) );
//    backButton.prefWidthProperty().bind(Bindings.divide(primaryStage.widthProperty(), 10.0));


System.out.println("game menu initialized");        
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        System.out.println("play selected world -> advancements: " + mediaView);
        
        App.setRoot("advancements");
    }

    
    @FXML
    private void switchToPlay() throws IOException 
    {
        System.out.println("play selected world -> advancements: " + mediaView);
        
        App.setRoot("play");
    }    
}