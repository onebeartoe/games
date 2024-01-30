
package org.onebeartoe.desktop;


import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;
import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertEquals;

/**
 *
 */
public class AdvancementsFxmlTest extends ApplicationTest
{
//    private AdvancementsController implementation = new AdvancementsController();
    
    Scene scene;
    
//TODO: find a way to verify the font
//      or get rid of this test    
//    @Test( )
@Deprecated    
    public void adventure_monstersHunted_font()
    {
System.out.println("this is a test - fonto");

        BorderPane borderPane = (BorderPane) scene.getRoot();

        TabPane rootNode = (TabPane) borderPane.getCenter();
        
        Tab adventureTab = rootNode.getTabs().get(1);
        
        String title = adventureTab.getText();
        
        assertEquals("Adventure", title);
        
        SplitPane splitPane = (SplitPane) adventureTab.getContent();

        ScrollPane scrollPane = (ScrollPane) splitPane.getItems().get(1);
        
        SplitPane textAreaSplitPane = (SplitPane) scrollPane.getContent();
        
        TextArea haveNotsTextArea = (TextArea) textAreaSplitPane.getItems().get(1);
        
        Font font = haveNotsTextArea.getFont();
        
        var expected = "minecraft";
        
        var actual = font.getName();
        
        assertEquals(expected, actual);
    }
    
    @FXML
    @Override
    public void start(Stage stage) throws Exception 
    {
        String path = App.class.getResource("minecraft.ttf").toExternalForm();
        Font loadFont = Font.loadFont(path, 10);
        

        System.out.println("inititititit");  
        
        BorderPane p = new BorderPane();
        
        scene = new Scene(p, 640, 480);         
//        scene = new Scene(parent, 640, 480);         

        var fontSheet = "/org/onebeartoe/desktop/fonts.css";
        scene.getStylesheets().add(fontSheet);        

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("advancements" + ".fxml"));        
        Object root = fxmlLoader.load();        
        Parent parent = (Parent) root;    

        
        parent.setStyle("""
                            .title {
                                -fx-font-family: "minecraft";
                                -fx-font-size: 20;
                            }                        
                        """);
        p.setCenter(parent);
        
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }
  
    @org.junit.Test
    public void husbandry_completeCatalogue_imageIsAvailable() throws IOException 
    {
        System.out.println("this = " + this);

        System.out.println("scene = " + scene);      

        BorderPane borderPane = (BorderPane) scene.getRoot();

        TabPane rootNode = (TabPane) borderPane.getCenter();

        Button button = from(rootNode).lookup(".button").query();

        assertEquals("Husbandry", button.getText());
        
        Tab netherTab = rootNode.getTabs().get(0);
        
        SplitPane splitPane = (SplitPane) netherTab.getContent();
        
        HBox hBox = (HBox) splitPane.getItems().get(0);
        
        ObservableList<Node> children = hBox.getChildren();

        ImageView imageView = (ImageView) children.get(0);
        
        Image image = imageView.getImage();
        
        String url = image.getUrl();
        
        AssertJUnit.assertNotNull(url);
    }    
}
