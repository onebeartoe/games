
package org.onebeartoe.desktop;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.testng.AssertJUnit.assertNotNull;

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
    
    @FXML
    @Override
    public void start(Stage stage) throws Exception 
    {
        System.setProperty("user.home", "src/test/resources");
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("select-world" + ".fxml"));        
        Object root = fxmlLoader.load();        
        Parent parent = (Parent) root;                  
        
        scene = new Scene(parent, 640, 480);         
        
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }
    
    @Test
    public void staticComponents()
    {
        var root = (BorderPane) scene.getRoot();
        
        // BorderPane top
        VBox top = (VBox) root.getTop();
        
        ObservableList<Node> children = top.getChildren();
        
        assertNotNull(children);
//        borderPane -> top -> VBox -> 
//                    lable -> "Selcect World"
//                    textField -> exists
                
        
        
    }    
}
