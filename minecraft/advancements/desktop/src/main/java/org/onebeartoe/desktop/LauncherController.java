package org.onebeartoe.desktop;

import java.io.IOException;
import javafx.fxml.FXML;

public class LauncherController 
{
    @FXML
    private void switchToSecondary() throws IOException
    {
        App.setRoot("secondary");
    }
    
    @FXML
    private void handlePlayButton() throws IOException
    {
        App.setRoot("title-screen");
    }
}
