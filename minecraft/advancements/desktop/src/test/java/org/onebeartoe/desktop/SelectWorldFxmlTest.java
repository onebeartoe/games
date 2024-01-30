
package org.onebeartoe.desktop;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 */
public class SelectWorldFxmlTest extends ApplicationTest
{
    Scene scene;
    
    @FXML
    @Override        
    public void init() throws IOException
    {
System.out.println("init() is called");
    }

    @FXML
    public void initialize() throws IOException
    {
System.out.println("initialize() is called");
    }    

    @Test
    public void someTest()
    {
        System.out.println("9999 this is a test - 12345431 - kjljl;kjlkjl;j");
    }
    
    @FXML
    @Override
    public void start(Stage stage) throws Exception 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("select-world" + ".fxml"));        
        Object root = fxmlLoader.load();        
        Parent parent = (Parent) root;                  

        scene = new Scene(parent, 640, 480);         

        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }
}
