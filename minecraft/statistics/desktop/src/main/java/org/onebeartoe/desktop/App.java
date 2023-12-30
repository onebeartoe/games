package org.onebeartoe.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

/**
 * This is a JavaFX Application to show Minecraft advancements.
 */
public class App extends Application 
{
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException 
    {
        Parent parent = (Parent) loadFXML("splash");
        
        // at this point we know the root is a VBox
        VBox splash = (VBox) parent;
        
        var url = "file:///home/roberto/Versioning/owner/beto-land-owner/Imaging/felix-the-cat/felix.gif";

        var image = new Image(url,232,232,false,true);
        
        var backgroundImage= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        
        var background = new Background(backgroundImage);

        splash.setBackground(background);
        
        scene = new Scene(parent, 640, 480);
        
        stage.setScene(scene);
        
        stage.show();
        
        spinyDelay();
    }
    
    private void spinyDelay()
    {
        long millis = 1000 * 4;
        
        TimerTask task = new TimerTask() 
        {
            @Override
            public void run() 
            {
                Platform.runLater(() -> 
                {
                    try
                    {
                        Thread.sleep(millis);
                    } 
                    catch (InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }

                    var root = "launcher";

                    System.out.println("switcho: " + root);

                    try 
                    {
                        App.setRoot(root);
                    }
                    catch (IOException ex) 
                    {
                        ex.printStackTrace();
                    }
                });                
            }
        };
        
        Timer timer = new Timer();
        
        long delay = 0;
        
        timer.schedule(task, delay);
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
        launch();
    }
}
