
package org.onebeartoe.minecraft;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.prefs.BackingStoreException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
//import static org.testng.AssertJUnit.assertEquals;
//import static org.testng.AssertJUnit.assertNotNull;
//import static org.testng.AssertJUnit.assertTrue;
//import org.testng.annotations.Test;

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

        System.setProperty("minecraft.home", "src/test/resources/minecraft");
    }
    
    @FXML
    @Override
    public void start(Stage stage) throws Exception, BackingStoreException
    {
        CompanionAppPreferences.savesPath("src/test/resources/minecraft/saves");
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("select-world" + ".fxml"));        
        Object root = fxmlLoader.load();        
        Parent parent = (Parent) root;                  
        
        scene = new Scene(parent, 640, 480);         
        
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }
    
    @Test
    public void userInterfaceComponents() throws MalformedURLException
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

    private void dynamicComponents(BorderPane root) throws MalformedURLException 
    {
        var savesContainer = (VBox) root.getCenter();
        
        ObservableList<Node> children = savesContainer.getChildren();

        // button text
//TODO: remove the 3 hardcoded values in the FXML
        var cringyGullLabel = (Label) children.get(0);
        
        var actual = cringyGullLabel.getText();
        
        // the first dynamic world is named after CringyGull
        var expected = "cringygull";

        assertEquals(expected, actual);
        
        // button icon
        ImageView imageView = (ImageView) cringyGullLabel.getGraphic();
        
        var image = (Image) imageView.getImage();
        
        assertNotNull(image);
        
        String location = image.getUrl();
        
        assertTrue(location.endsWith("icon.png") );
        
        URL url = new URL(location);
        
        File file = new File(url.getFile() );
        
        assertTrue(file.exists());
    }
}
