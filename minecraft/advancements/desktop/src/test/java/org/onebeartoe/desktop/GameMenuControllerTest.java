
package org.onebeartoe.desktop;

import java.io.IOException;
import static java.lang.ProcessBuilder.Redirect.from;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.testng.AssertJUnit.assertEquals;


/**
 *
 */
public class GameMenuControllerTest extends ApplicationTest
{
    private GameMenuController implementation;
    
    Scene scene;
//    Scene scene = null;
    
    
    
@FXML
  @Override        
    public void init() throws IOException
    {
System.out.println("is called????");
    }
    
@FXML
//  @Override        
    public void initialize() throws IOException
    {
        System.out.println("never called????");
    }
    
    
    @Test
    public void someTest()
    {
        System.out.println("this is a test - 12345431 - kjljl;kjlkjl;j");
    }
    
@FXML
  @Override
  public void start(Stage stage) throws Exception 
  {
//      System.out.println("starting");
//      initialize();
//      
//      init();
//      implementation = new GameMenuController();
      

    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("game-menu" + ".fxml"));        
    Object root = fxmlLoader.load();        
    Parent parent = (Parent) root;                  
    System.out.println("inititititit");              
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