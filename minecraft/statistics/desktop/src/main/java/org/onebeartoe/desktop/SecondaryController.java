
package org.onebeartoe.desktop;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;

public class SecondaryController 
{
    @FXML
    private void playSound()
    {
        System.out.println("playing sound");
        
        AudioClip buzzer = new AudioClip(getClass().getResource("/org/onebeartoe/desktop/y2meta.com-Rick-Rolled-Short-Version.mp3").toExternalForm());
        
        buzzer.play();
    }
    
    @FXML
    private void switchToSplash() throws IOException 
    {
        App.setRoot("splash");
    }
}