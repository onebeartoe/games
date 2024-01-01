package org.onebeartoe.desktop;

import java.io.IOException;
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

    @Override
    public void init()
    {
//        splashButton.translateXProperty()
//            .bind(scene.widthProperty().subtract(splashButton.widthProperty())
//                    .divide(2));
//
//        splashButton.translateYProperty()
//                .bind(scene.heightProperty().subtract(splashButton.heightProperty())
//                        .divide(2));        
    }
    
    @Override
    public void start(Stage stage) throws IOException 
    {
        String [] screenNames = {"launcher", "play", "splash", "advancements"};
        
        var initialRoot = screenNames[3];

        Parent parent = (Parent) loadFXML(initialRoot);        
        
        scene = new Scene(parent, 640, 480);
        
        stage.setScene(scene);
        
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
    }

    private static Object loadFXML(String fxml) throws IOException 
    {
//TODO: maybe use a Map to hold handfull of FXML layotuts???
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
