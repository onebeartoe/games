package org.onebeartoe.games.gnuplot.map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


import java.util.prefs.Preferences;
//import static org.onebeartoe.games.gnuplot.map.PrimaryController.xField;
//import static org.onebeartoe.games.gnuplot.map.PrimaryController.yField;

/**
 * This is the entry point for the Minecraft Companion Gnuplot Map application.
 */
public class App extends Application 
{
    public static Stage stage;

    private static Scene scene;
    
    static String PREFERENCES_KEY = "org.onebeartoe.minecraft.gnuplot.preferences";
    
    static String WIDTH_KEY = PREFERENCES_KEY + ".WIDTH";
    
    static String HEIGHT_KEY = PREFERENCES_KEY + ".HEIGHT";
    
    static String INPUT_DIRECORTY_KEY = PREFERENCES_KEY + ".INPUT_DIRECTORY";
    
    static String TARGET_X_KEY = PREFERENCES_KEY + ".TAGET_X";
    
    static String TARGET_Y_KEY = PREFERENCES_KEY + ".TAGET_Y";
    
    public static final  Preferences preferences = Preferences.userRoot().node(PREFERENCES_KEY);

    @Override
    public void start(Stage stage) throws IOException 
    {
        var width = preferences.getDouble(WIDTH_KEY, 640.0);
        
        var height = preferences.getDouble(HEIGHT_KEY, 480.0);
        
        var parent = loadFXML("primary"); 
        
        scene = new Scene(parent, width, height);
    
        this.stage = stage;

        stage.setScene(scene);
        
        stage.show();
    }
    
    @Override
    public void stop()
    {
        preferences.putDouble(WIDTH_KEY, scene.getWidth());
        
        preferences.putDouble(HEIGHT_KEY, scene.getHeight());
        
//        var x = Integer.valueOf(xField.getText() );
//        preferences.putInt(TARGET_X_KEY, x);
//        
//        var y = Integer.valueOf(yField.getText() );
//        preferences.putInt(TARGET_Y_KEY, y);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
