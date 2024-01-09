package org.onebeartoe.desktop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This is a JavaFX Application to show Minecraft advancements.
 */
public class App extends Application 
{
    private static Scene scene;
    
    @FXML
    public Button splashButton;    
    

    private static final String [] screenNames = 
    {
        "splash", "launcher", "title-screen", "select-world", "game-menu", "advancements"
    };

    private static  Map<String, String> fmxlToStyleSheets;

//TODO: can we use this in this app?    
//        splashButton.translateYProperty()
//                .bind(scene.heightProperty().subtract(splashButton.heightProperty())
//                        .divide(2));        
    

    public App()
    {
        fmxlToStyleSheets = HashMap.newHashMap(6);
        
        fmxlToStyleSheets.put(screenNames[0], screenNames[0] + ".css");
        fmxlToStyleSheets.put(screenNames[1], screenNames[1] + ".css");
        fmxlToStyleSheets.put(screenNames[2], screenNames[2] + ".css");
        fmxlToStyleSheets.put(screenNames[3], screenNames[3] + ".css");
        fmxlToStyleSheets.put(screenNames[4], screenNames[4] + ".css");
        fmxlToStyleSheets.put(screenNames[5], screenNames[5] + ".css");
    }

    
    @Override
    public void start(Stage stage) throws IOException 
    {
        
        var initialRoot = screenNames[1];

        Parent parent = (Parent) loadFXML(initialRoot);        
        
        scene = new Scene(parent, 640, 480);
        
        stage.setScene(scene);

        // dark mode
        scene.getRoot().setStyle("-fx-base:black");
        
        scene.getStylesheets().add("/org/onebeartoe/desktop/title-screen.css");
        
        stage.show();
                     

//        splashButton.translateXProperty()
//            .bind(scene.widthProperty().subtract(splashButton.widthProperty())
//                    .divide(2));
//
//        splashButton.translateYProperty()
//                .bind(scene.heightProperty().subtract(splashButton.heightProperty())
//                        .divide(2));           
    }

    static void setRoot(String fxml) throws IOException 
    {
        Parent parent = (Parent) loadFXML(fxml);
        
        scene.setRoot(parent);
        
        // dark mode
        scene.getRoot().setStyle("-fx-base:black");
        
        var styleSheets = "/org/onebeartoe/desktop/" + fmxlToStyleSheets.get(fxml);

        scene.getStylesheets().add(styleSheets);
    }

    private static Object loadFXML(String fxml) throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        
        Object root = fxmlLoader.load();
        
        return root;
    }

    public static void main(String[] args) 
    {
        // show a splash screen (sure)
//        System.setProperty("javafx.preloader", SplashScreen.class.getName());
   
        launch();
    }
}
