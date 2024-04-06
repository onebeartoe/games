package org.onebeartoe.minecraft.advencements;


import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.onebeartoe.minecraft.advencements.AdvancementsFxmlTest;
import org.testfx.framework.junit.ApplicationTest;


public class AdventureFxmlTest extends AdvancementsFxmlTest
{
    /**
     * This verifies the labels mentioned in the specification are present.
     */
    @Test
    public void haveAndHaveNotLabels()
    {
        
        
        SplitPane atSplitPane = (SplitPane) adventureTab.getContent();
        
        var textAreasSplitPane = (SplitPane) atSplitPane.getItems().get(1);
        
        var havesBorderPane = (BorderPane) textAreasSplitPane.getItems().get(0);
        
        var havesLabel = (Label) havesBorderPane.getTop();
        
        var havesActual = havesLabel.getText();
        
        var havesExpected = "Acquired";
        
        assertEquals(havesExpected, havesActual);
        
//TODO: do the 'have nots' text area too        
    }
}
