
package org.onebeartoe.minecraft.advancements;

import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

/**
 *
 */
public class AdvancementsServiceTest
{
    private AdvancementsService implementation;

    public AdvancementsServiceTest() throws IOException, ParseException 
    {
        implementation = new AdvancementsService();
    }

    @Test
    public void balancedDietCountSanityCheck()
    {
        List<String> dietItems = implementation.balancedDietItems();

        assertEquals(40, dietItems.size());
    }
    
    @Test
    public void completeCatagoryCountSanityCheck()
    {
        List<String> allCategrories = implementation.allCatCategories();
        
        assertTrue(allCategrories.size() == 11);
    }    
}
