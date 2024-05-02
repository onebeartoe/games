
package org.onebeartoe.minecraft.advencements;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;

/**
 *
 */
public class HusbandryFxmlTest extends AdvancementsFxmlTest
{
    @Test
    public void husbandry_completeCatalogue_imageIsAvailable() throws IOException 
    {
//TODO: use lookup based on node ID        
        Button button = from(tabs).lookup(".button").query();

        assertEquals("Husbandry", button.getText());
        
        SplitPane splitPane = (SplitPane) husbandryTab.getContent();
        
        HBox hBox = (HBox) splitPane.getItems().get(0);
        
        ObservableList<Node> children = hBox.getChildren();

        ImageView imageView = (ImageView) children.get(0);
        
        Image image = imageView.getImage();
        
        String url = image.getUrl();
        
        assertTrue(url.endsWith("/dirt.jpg") );
    }

    @Test
    public void husbandry_aBalancedDiet_imageIsAvailable()
    {
        ImageView imageView = from(tabs)
                                .lookup("#balancedDietImageView")
                                .query();
        
        var image = imageView.getImage();
        
        var url = image.getUrl();
        
        assertTrue(url.endsWith(("balanced-diet.png")));
    }

    @Test
    public void husbandry_twoByTwo_imageIsAvailable()
    {
        ImageView imageView = from(tabs)
                                .lookup("#twoByTwoImageView")
                                .query();
        
        var image = imageView.getImage();
        
        var url = image.getUrl();
        
        assertTrue(url.endsWith(("two-by-two.png")));
    }
}
