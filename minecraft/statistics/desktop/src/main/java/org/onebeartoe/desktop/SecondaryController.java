
package org.onebeartoe.desktop;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SecondaryController 
{
    @FXML
    private void playSound()
    {
        System.out.println("playing sound");
        
        AudioClip buzzer = new AudioClip(getClass().getResource("/org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version.mp4").toExternalForm());
        
        buzzer.play();
    }
    
    @FXML
    private void playOtherSound()
    {
        System.out.println("playing other sound - ");
        
//String path = "https://ssl.gstatic.com/dictionary/static/sounds/20200429/hello--_gb_1.mp3";
//String path = "hello--_gb_1.mp3";
String path = "file:///home/roberto/Versioning/owner/github/games/minecraft/statistics/desktop/src/main/resources/org/onebeartoe/desktop/hello--_gb_1.wav";
        
        final Media sound = new Media(path);

        final MediaPlayer mediaPlayer = new MediaPlayer(sound);

        mediaPlayer.setAutoPlay(true);

        mediaPlayer.play();
    }
    
    @FXML
    private void switchToSplash() throws IOException 
    {
        App.setRoot("splash");
    }
}