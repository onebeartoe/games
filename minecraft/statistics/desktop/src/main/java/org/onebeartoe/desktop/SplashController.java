package org.onebeartoe.desktop;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class SplashController 
{
    @FXML
    public MediaView mediaView;
    
    @FXML
    public void initialize() throws URISyntaxException
    {
//TODO: use parent node and child node width and height binding as seen here
//          https://stackoverflow.com/questions/42774863/how-to-call-a-mediaview-from-another-fxml-file        
        
        
        System.out.println("mediaView: " + mediaView);
        
        URL resource = App.class.getResource("y2meta.com-Rick-Rolled-Short-Version.mp4");
            
            
// String path = "/home/javatpoint/Downloads/test.mp4";  
  

        String path = resource.toURI().toString();
        
//        String path = "file:///home/roberto/Versioning/owner/github/games/minecraft/statistics/desktop/target/classes/org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version.ogg";
//        String path = "file:///home/roberto/Versioning/owner/github/games/minecraft/statistics/desktop/target/classes/org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version.mp4";
//        String path = "file:///home/roberto/Versioning/owner/github/games/minecraft/statistics/desktop/target/classes/org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version-codecs.mp4";
//        String path = "file:///home/roberto/Versioning/owner/github/games/minecraft/statistics/desktop/target/classes/org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version.avi";
//        String path = "file:////home/roberto/Workspace/owner/first-song/recording.2021.11.25.14.41.35.mp3";
        System.out.println("path = " + path);

        //Instantiating Media class
        
        Media media = new Media(path);  
          
        
 //       Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);  
          
        //Instantiating MediaView class   
        mediaView = new MediaView(mediaPlayer);  
          
        //by setting this property to true, the Video will be played   
        mediaPlayer.setAutoPlay(true);             
    }
    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        
        System.out.println("mediaView again: " + mediaView);
        App.setRoot("primary");
    }
}