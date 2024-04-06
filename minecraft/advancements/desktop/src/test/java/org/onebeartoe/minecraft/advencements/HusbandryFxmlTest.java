
package org.onebeartoe.minecraft.advencements;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.junit.Test;
import org.onebeartoe.desktop.AdvancementsFxmlTest;

/**
 *
 */
public class HusbandryFxmlTest extends AdvancementsFxmlTest
{
    @Test
    public void husbandry_completeCatalogue_imageIsAvailable() throws IOException 
    {
        System.out.println("this = " + this);

        System.out.println("scene = " + scene);      

        rootNode = (TabPane) scene.getRoot();

        Button button = from(rootNode).lookup(".button").query();

        assertEquals("Husbandry", button.getText());
        
        Tab netherTab = rootNode.getTabs().get(0);
        
        SplitPane splitPane = (SplitPane) netherTab.getContent();
        
        HBox hBox = (HBox) splitPane.getItems().get(0);
        
        ObservableList<Node> children = hBox.getChildren();

        ImageView imageView = (ImageView) children.get(0);
        
        Image image = imageView.getImage();
        
        String url = image.getUrl();
        
        assertNotNull(url);
    }    
}
