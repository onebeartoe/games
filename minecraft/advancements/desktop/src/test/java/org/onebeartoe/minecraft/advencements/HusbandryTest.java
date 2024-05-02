
package org.onebeartoe.minecraft.advencements;

import java.util.List;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 * This class verifies the specification for the Husbandry Advancements.
 * 
 * Each test clicks on the ImageView for the advancement and asserts the corresponding 
 * TextArea's contents for the 'obtained' and 'missing' criteria.
 */
//TODO: Make AdvancementsController#initialize() dynamically load the player's advancements.json file!!!!!
public class HusbandryTest extends AdvancementsFxmlTest
{
    @Test
    public void twoByTwo()
    {
        clickOn("Husbandry");

//        Tab husbandryTab = from(tabs)
//                            .lookup("#husbandryTab")
//                            .query();
        
        ImageView imageView = from(tabs)
                        .lookup("#twoByTwoImageView")
                        .query();
        
        clickOn(imageView);
        
        TextArea havesTextArea = from(tabs)
                                    .lookup("#husbandryHavesTextArea")
                                    .query();
        
        // assert there are 8 items in the obtainded text area
        var havesLines = havesTextArea.getText();
        
        String[] split = havesLines.split( System.lineSeparator() );
        
        List<String> lines = List.of(split);
        
        var expectedHaves = 8;
        
        var actualHaves = lines.size();
        
        assertEquals(expectedHaves, actualHaves);



        // assert obtained items contain cat, cow, sniffer
        // find the missing text area
        // assert there are 17 in the missing text area
        // assert missing area contains Mooshoom, Mule, Rabbit
    }
}
