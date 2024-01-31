package org.onebeartoe.desktop;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;
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
        // find the Minecraft saves directory
        // usually "~/.minecraft/saves", but will dynamically check
        // the user home directory as a 
        var path = System.getProperty("user.home");
        System.out.println("SelectWorldContorller user home = " + path);
        
        var minecraftSavesPath = path + "";
        
        var savesDir = new File(path);
        var exists = savesDir.exists();
        System.out.println("exists = " + exists);
        
        String[] list = savesDir.list();
        
//        System.out.println("world list unfiltered= ");
//        Stream.of(list)
//                .forEach(System.out::println);
        
        var children = vBox.getChildren();

        System.out.println("world list = ");        
        Stream.of(list)
                .filter(item -> !item.startsWith("."))
                .forEach(save -> 
                {
                    System.out.println("save:" + save);

                    Button button = new Button(save);

                    children.add(button);
                });
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        System.out.println("select world -> sf452: " + mediaView);
        
        App.setRoot("game-menu");
    }
}