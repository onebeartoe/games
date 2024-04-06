package org.onebeartoe.minecraft;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.fxml.FXML;

public class LauncherController 
{   
    @FXML
    public void initialize() throws URISyntaxException, IOException
    {
        System.out.println("this line executes - u38275gji7489yf");
    }

    @FXML
    private void handlePlayButton() throws IOException
    {
        App.setRoot("title-screen");
    }
    
}
