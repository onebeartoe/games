package org.onebeartoe.minecraft.advencements;

import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

public class AdventureFxmlTest extends AdvancementsFxmlTest
{
    /**
     * This specification verifies that the 'haves' (or Acquired) label is present.
     */
    @Test
    public void haveLabels()
    {
        SplitPane atSplitPane = (SplitPane) adventureTab.getContent();
        
        var textAreasSplitPane = (SplitPane) atSplitPane.getItems().get(1);
        
        var havesBorderPane = (BorderPane) textAreasSplitPane.getItems().get(0);
        
        var havesLabel = (Label) havesBorderPane.getTop();
//havesLabel.setTooltip(tltp);
        var havesActual = havesLabel.getText();
        
        var havesExpected = "Acquired";
        
        assertEquals(havesExpected, havesActual);
    }
    
    /**
     * This specification verifies that the 'have nots' (or Missing) label is present.
     */
    @Test
    public void haveNotLabels()
    {
        SplitPane atSplitPane = (SplitPane) adventureTab.getContent();
        
        var textAreasSplitPane = (SplitPane) atSplitPane.getItems().get(1);
        
        var havesBorderPane = (BorderPane) textAreasSplitPane.getItems().get(1);
        
        var havesLabel = (Label) havesBorderPane.getTop();
        
        var havesActual = havesLabel.getText();
        
        var havesExpected = "Missing";
        
        assertEquals(havesExpected, havesActual);
    }
}
