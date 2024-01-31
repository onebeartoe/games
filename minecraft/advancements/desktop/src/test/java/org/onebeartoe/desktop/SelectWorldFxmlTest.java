
package org.onebeartoe.desktop;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.testng.AssertJUnit.assertEquals;
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
        System.setProperty("user.home", "src/test/resources/minecraft/saves");
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("select-world" + ".fxml"));        
        Object root = fxmlLoader.load();        
        Parent parent = (Parent) root;                  
        
        scene = new Scene(parent, 640, 480);         
        
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }
    
    @Test
    public void userInterfaceComponents()
    {
        var root = (BorderPane) scene.getRoot();
        
        staticComponents(root);

        dynamicComponents(root);
    }

    private void staticComponents(BorderPane root)
    {
        // BorderPane top
        VBox top = (VBox) root.getTop();
        
        ObservableList<Node> children = top.getChildren();
        
        assertNotNull(children);

//TODO:        
//        borderPane -> top -> VBox -> 
//                    lable -> "Selcect World"
//                    textField -> exists        


        //TODO: verify center pane
                        
        //TODO: verify botton pane
    }

    private void dynamicComponents(BorderPane root) 
    {
        var savesContainer = (VBox) root.getCenter();
        
        ObservableList<Node> children = savesContainer.getChildren();

        // button text
//TODO: remove the 3 hardcoded values in the FXML
        var cringyGullButton = (Button) children.get(3);
        
        var actual = cringyGullButton.getText();
        
        var expected = "cringygull";
        
        assertEquals(expected, actual);
        
        // button icon
        var graphic = cringyGullButton.getGraphic();

//TODO: pick up here        
//        graphic.
        
throw new UnsupportedOperationException("do the icon now!");         
        
        // root (BorderPane) -> center -> VBoX -> children
        //                          -> CringyGull
//                                            -> Text
//                                            -> Icon
        
        
    }
}
