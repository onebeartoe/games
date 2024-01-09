
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
//TODO: move this to the contrucutor once Advancement class replaces the List<String>    
    private final AdvancementsService implementation;
    
    private final Advancements minecraftAdvancements;

    public AdvancementsServiceTest() throws IOException, ParseException 
    {
        implementation = new AdvancementsService();
        
        minecraftAdvancements = implementation.load();
    }

    @Test
    public void bredAllAnimals()
    {
        int size = implementation.breedableAnimals().size();
        
        // the 1.20 Advancements screen shows there are this may animals to breed;
        int breedableCount = 25;

        assertEquals( breedableCount, size);
    }
            
            
    
    @Test
    public void balancedDietCountSanityCheck()
    {
        List<String> dietItems = implementation.balancedDietItems();

        assertEquals(40, dietItems.size());
    }
    
    @Test
    public void monstersHunted()
    {
        List<String> criteria = minecraftAdvancements.adventure.monstersHunted.criteria;
           
        int actualSize = criteria.size();
        
        int expected = 35;
        
        assertEquals(expected, actualSize );
    }
    
    @Test
    public void completeCatagoryCountSanityCheck()
    {        
        List<String> allCategrories = minecraftAdvancements.husbandry.aCompleteCatalogue.criteria;
        
        String cat1 = allCategrories.get(1);
        
//        assertTrue(!cat1.contains(":"));
//        
//        assertTrue(!cat1.contains("/"));
//        
//        assertTrue(!cat1.contains("."));
        
        assertTrue(allCategrories.size() == 11);
    }

    private void hotTouristDestinations(Advancement hotTouristDestinations)
    {
        var criteria = hotTouristDestinations.criteria;
        
        assertTrue(criteria.size() == 5);
        
        assertTrue( criteria.contains("minecraft:basalt_deltas") );
        assertTrue( criteria.contains("minecraft:crimson_forest") );
        assertTrue( criteria.contains("minecraft:nether_wastes") );
        assertTrue( criteria.contains("minecraft:soul_sand_valley") );
        assertTrue( criteria.contains("minecraft:warped_forest") );
    }
    
    @Test
    public void load() throws IOException, ParseException
    {
        Advancements advancements = implementation.load();
        
        hotTouristDestinations(advancements.nether.hotTouristDestinations);
    }
}
