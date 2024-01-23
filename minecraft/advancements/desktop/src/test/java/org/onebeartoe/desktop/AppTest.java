
package org.onebeartoe.desktop;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import javafx.scene.text.Font;
import org.junit.Test;

/**
 *
 */
public class AppTest
{
    @Test
    public void loadMinecraftFont()
    {
        Font font = App.loadMinecraftFont();
        
        assertNotNull(font);
        
        var actualName = font.getName();
        
        var expected = "Minecraft";
        
        assertEquals(expected, actualName);
    }
}
