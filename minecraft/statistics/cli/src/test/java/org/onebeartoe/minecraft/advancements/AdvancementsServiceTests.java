
package org.onebeartoe.minecraft.advancements;

import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

/**
 *
 */
public class AdvancementsServiceTests
{
    AdvancementsService implementation;

    public AdvancementsServiceTests() throws IOException, ParseException 
    {
        this.implementation = new AdvancementsService();
    }

    @Test
    public void balancedDietCountSanityCheck()
    {
        List<String> dietItems = implementation.balancedDietItems();
        
        assertTrue(dietItems.size() == 40);
    }
    
    @Test
    public void completeCatagoryCountSanityCheck()
    {
        List<String> allCategrories = implementation.allCatCategories();
        
        assertTrue(allCategrories.size() == 11);
    }    
}
