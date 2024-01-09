package org.onebeartoe.desktop;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;

/**
 * The background "splash" animation is actually setup/started in the 
 * JavaFX application's main() class in the @Override start() method.
 */
public class TitleScreenController 
{
    @FXML
    public AnchorPane rootAnchor;
    
    @FXML
    public MediaView mediaView;
    
    @FXML
    public VBox vBox;
    
    @FXML
    Button singlePlayerButton;
    
    @FXML
    public void initialize() throws URISyntaxException, IOException
    {
// thsi doesnt work because teh scene has not been set on the Node       
//        rootAnchor.getScene().getStylesheets().add("/styles/Styles.css");
        
        
//TODO: use parent node and child node width and height binding as seen here
//          https://stackoverflow.com/questions/42774863/how-to-call-a-mediaview-from-another-fxml-file        
        
        
//        System.out.println("mediaView: " + mediaView);
//        
//        URL resource = App.class.getResource("y2meta.com-Rick-Rolled-Short-Version.mp4");
            
            
// String path = "/home/javatpoint/Downloads/test.mp4";  
  

//        String path = resource.toURI().toString();
        
//        String path = "file:///home/roberto/Versioning/owner/github/games/minecraft/statistics/desktop/target/classes/org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version.ogg";
//        String path = "file:///home/roberto/Versioning/owner/github/games/minecraft/statistics/desktop/target/classes/org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version.mp4";
//        String path = "file:///home/roberto/Versioning/owner/github/games/minecraft/statistics/desktop/target/classes/org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version-codecs.mp4";
//        String path = "file:///home/roberto/Versioning/owner/github/games/minecraft/statistics/desktop/target/classes/org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version.avi";
//        String path = "file:////home/roberto/Workspace/owner/first-song/recording.2021.11.25.14.41.35.mp3";
//        System.out.println("path = " + path);

        //Instantiating Media class
        
//        Media media = new Media(path);  
          
        
 //       Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
//        MediaPlayer mediaPlayer = new MediaPlayer(media);  
          
        //Instantiating MediaView class   
//        mediaView = new MediaView(mediaPlayer);  
          
        //by setting this property to true, the Video will be played   
//        mediaPlayer.setAutoPlay(true);
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {        
        System.out.println("mediaView again: " + mediaView);

        App.setRoot("select-world");
    }
}
