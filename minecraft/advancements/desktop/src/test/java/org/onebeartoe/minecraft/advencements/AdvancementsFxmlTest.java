
package org.onebeartoe.minecraft.advencements;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.onebeartoe.minecraft.App;
import static junit.framework.Assert.assertEquals;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 */
public class AdvancementsFxmlTest extends ApplicationTest
{
//    private AdvancementsController implementation = new AdvancementsController();
    
    protected Scene scene;
    
    protected TabPane tabs;
    
    protected Tab netherTab;
    
    protected Tab adventureTab;

    protected Tab husbandryTab;
    
//TODO: find a way to verify the font
//      or get rid of this test    
//    @Test( )
@Deprecated    
    public void adventure_monstersHunted_font()
    {
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
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("advancements" + ".fxml"));        

        Object root = fxmlLoader.load();        

        Parent parent = (Parent) root;
                 
        scene = new Scene(parent, 640, 480);

        tabs = (TabPane) scene.getRoot();
        
        netherTab = tabs.getTabs().get(0);

        adventureTab = tabs.getTabs().get(1);
        
        husbandryTab = tabs.getTabs().get(2);
        
        var fontSheet = "/org/onebeartoe/desktop/fonts.css";

        scene.getStylesheets().add(fontSheet);
        
        parent.setStyle("""
                            .title {
                                -fx-font-family: "minecraft";
                                -fx-font-size: 20;
                            }                        
                        """);
        
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }   
}
