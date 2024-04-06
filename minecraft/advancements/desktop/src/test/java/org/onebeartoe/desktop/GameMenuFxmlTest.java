
package org.onebeartoe.desktop;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
//import static org.testng.AssertJUnit.assertEquals;

/**
 *
 */
public class GameMenuFxmlTest extends ApplicationTest
{
//    private GameMenuController implementation;
    
    Scene scene;
    
    @FXML
    @Override        
    public void init() throws IOException
    {
        System.out.println("is called????");
    }
    
    @FXML
    public void initialize() throws IOException
    {
        System.out.println("never called????");
    }
    
    @FXML
    @Override
    public void start(Stage stage) throws Exception 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("game-menu" + ".fxml"));        
        Object root = fxmlLoader.load();        
        Parent parent = (Parent) root;                  
           
        scene = new Scene(parent, 640, 480);         

        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }
  
    @Test
    public void hasHelloWorldButton() throws IOException 
    {
        System.out.println("this = " + this);

        System.out.println("scene = " + scene);      

        StackPane rootNode = (StackPane) scene.getRoot();
        Button button = from(rootNode).lookup(".button").query();
        assertEquals("Back to Game", button.getText());
    }
}