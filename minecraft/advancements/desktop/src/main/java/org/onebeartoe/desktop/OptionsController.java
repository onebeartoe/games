
package org.onebeartoe.desktop;

import javafx.stage.DirectoryChooser;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import java.util.prefs.BackingStoreException;

/**
 *
 */
public class OptionsController
{
    @FXML
    private void selectSavedFolder() throws IOException, BackingStoreException
    {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("JavaFX Projects");

        File defaultDirectory = new File("/home/roberto/.minecraft/");
        chooser.setInitialDirectory(defaultDirectory);

        File selectedDirectory = chooser.showDialog(null);     
        
        var path  = selectedDirectory.getAbsolutePath();
        
        CompanionAppPreferences.savesPath(path);
    }

    @FXML
    private void switchToSuperSecretOptions() throws IOException 
    {
        App.switchToSuperSecret();
    }
    
    @FXML
    private void switchToGameMenu() throws IOException
    {
        App.switchToGameMenu();
    }
    
}
