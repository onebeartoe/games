package org.onebeartoe.minecraft;

import java.io.IOException;
import java.net.URISyntaxException;
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
    public VBox vBox;
    
    @FXML
    Button singlePlayerButton;
    
    @FXML
    Button backButton;
    
    @FXML
    Button advancementsButton;

    @FXML
    Button optionsButton;

    @FXML
    Button saveAndQuitButton;
    
    @FXML
    Button statisticsButton;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException
    {
        String css = """
                     -fx-border-insets: 5;
                     -fx-text-fill: green;
                     """;        
//        String css = """
//                     -fx-border-color: red;
//                     -fx-border-insets: 5;
//                     -fx-border-width: 3;
//                     -fx-border-style: dashed;
//                     -fx-text-fill: green;
//                     """;

        vBox.setStyle(css);
        
        float widthPercentage = 0.4f;
        
        backButton.prefWidthProperty().bind( vBox.widthProperty().multiply(widthPercentage) );
        backButton.setStyle(css);
        
        advancementsButton.prefWidthProperty().bind( vBox.widthProperty().multiply(widthPercentage) );

        statisticsButton.prefWidthProperty().bind( vBox.widthProperty().multiply(widthPercentage) );

        optionsButton.prefWidthProperty().bind( vBox.widthProperty().multiply(widthPercentage) );

        saveAndQuitButton.prefWidthProperty().bind( vBox.widthProperty().multiply(widthPercentage) );
    }
    
    @FXML
    private void switchToAdvancements() throws IOException
    {
        App.switchToAdvancements();
    }
    
    @FXML
    private void switchToOptions() throws IOException 
    {        
        App.switchToOptions();
    }
    
    @FXML
    private void switchToTitle() throws IOException
    {
        App.switchToTitle();
    }   
}