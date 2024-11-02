package org.onebeartoe.games.gnuplot.map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


import java.util.prefs.Preferences;

/**
 * JavaFX App
 */
public class App extends Application 
{
    public static Stage stage;

    private static Scene scene;
    
    static String PREFERENCES_KEY = "org.onebeartoe.minecraft.gnuplot.preferences";
    
    static String WIDTH_KEY = PREFERENCES_KEY + ".WIDTH";
    
    static String HEIGHT_KEY = PREFERENCES_KEY + ".HEIGHT";
    
    public static final  Preferences preferences = Preferences.userRoot().node(PREFERENCES_KEY);

        

    @Override
    public void start(Stage stage) throws IOException 
    {
        double width = preferences.getDouble(WIDTH_KEY, 640.0);
        
        double height = preferences.getDouble(HEIGHT_KEY, 480.0);
        
//        scene = new Scene(loadFXML("primary"));
        scene = new Scene(loadFXML("primary"), width, height);
    
this.stage = stage;

        stage.setScene(scene);
        
        stage.show();
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