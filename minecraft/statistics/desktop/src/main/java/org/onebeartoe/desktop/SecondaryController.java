package org.onebeartoe.desktop;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController 
{
    @FXML
    private void switchToSplash() throws IOException 
    {
        App.setRoot("splash");
    }
}