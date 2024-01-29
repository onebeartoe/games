package org.onebeartoe.desktop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * This is a JavaFX Application to show Minecraft advancements.
 */
public class App extends Application 
{
    private static Scene scene;

    private static void handleEscapeKey(String fxml) 
    {
        //TODO: use a switch statement        
        if(fxml.equals("advancements"))
        {
                scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
          () {

                @Override
                public void handle(KeyEvent t) {
                  if(t.getCode()==KeyCode.ESCAPE)
                  {
                      System.out.println("click on escape");

                      try {
                          setRoot("game-menu");
        //           Stage sb = (Stage)label.getScene().getWindow();//use any one object
        //           sb.close();
                      } catch (IOException ex) {
                          ex.printStackTrace();
                      }
                  }
                }
            });    
        }
    }
    
    @FXML
    public Button splashButton;    
    

    private static final String [] screenNames = 
    {
        "splash", "launcher", "title-screen", "select-world", "game-menu", "advancements",

        "super-secret"
    };

    private static  Map<String, String> fmxlToStyleSheets;

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
        loadMinecraftFont();
        
        var initialRoot = screenNames[1];

        Parent parent = (Parent) loadFXML(initialRoot);        
        
        scene = new Scene(parent, 640, 480);
        
        stage.setScene(scene);
        
        stage.setTitle("Minecraft Companion App");

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
        
        handleEscapeKey(fxml);
                
        // dark mode
        scene.getRoot().setStyle("-fx-base:black");
         
        var styleSheets = "/org/onebeartoe/desktop/" + fmxlToStyleSheets.get(fxml);
        scene.getStylesheets().add(styleSheets);
        
//var fontSheet =         getClass().getResource("custom-font-styles.css").toExternalForm()
        var fontSheet = "/org/onebeartoe/desktop/fonts.css";
        scene.getStylesheets().add(fontSheet);
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

    public static Font loadMinecraftFont() 
    {
        String path = App.class.getResource("minecraft.ttf").toExternalForm();

        var font = Font.loadFont(path, 10);

        return font;
    }
}
