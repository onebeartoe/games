package org.onebeartoe.desktop;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
    public VBox topVBox;
    
    @FXML
    public HBox bottomHBox;

    @FXML
    public VBox vBox;
    
    @FXML
    Button singlePlayerButton;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException
    {
        // find the Minecraft saves directory
        // usually "~/.minecraft/saves", but will dynamically check
        // the user home directory
        
        var minecraftSavesPath = CompanionAppPreferences.savesPath();
        
        var savesDir = new File(minecraftSavesPath);
        
        String[] list = savesDir.list();
        
        var children = vBox.getChildren();  

        Stream.of(list)
                .filter(item -> !item.startsWith("."))
                .forEach(save -> 
                {
                    Label label = new Label(save);

                    var child = "/" + save + "/icon.png";
                    
                    var iconFile = new File(savesDir, child);

                    var imagePath = iconFile.toURI().toString();
                    
                    Image image = new Image(imagePath);
                    
                    ImageView imageView = new ImageView(image);
                    
                    label.setGraphic(imageView);
                    
                    
                    
                    label.setOnMouseClicked(
                    (clicked) ->
                        {
                        try {
                            App.switchToAdvancements();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        }
                    );


                    children.add(label);
                });
        
        String styleSheet = "/org/onebeartoe/desktop/select-world.css";
        topVBox.getStylesheets().add(styleSheet);
        topVBox.getStyleClass().add("dirtBackgroundImage");
        
        bottomHBox.getStylesheets().add(styleSheet);
        bottomHBox.getStyleClass().add("dirtBackgroundImage");
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        System.out.println("select world -> sf452: " + mediaView);
        
        App.setRoot("game-menu");
    }
}