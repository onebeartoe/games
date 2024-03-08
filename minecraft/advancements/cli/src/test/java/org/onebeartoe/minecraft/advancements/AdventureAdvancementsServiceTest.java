
package org.onebeartoe.minecraft.advancements;

import java.io.IOException;
import java.util.List;
import net.minecraft.advancements.Advancements;
import net.minecraft.advancements.AdvancementsService;
import org.json.simple.parser.ParseException;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

/**
 * 
 */
public class AdventureAdvancementsServiceTest
{
    private final Advancements minecraftAdvancements;
    
    private final AdvancementsService implementation;
    
    public AdventureAdvancementsServiceTest() throws IOException, ParseException
    {
        implementation = new AdvancementsService();
        
        minecraftAdvancements = implementation.load();
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
    public void discoverEveryBiome()
    {
        List<String> criteria = minecraftAdvancements.adventure.discoverEveryBiome.criteria;
           
        int actualSize = criteria.size();
        
        int expected = 53;
        
//TODO:        assertEquals(expected, actualSize );                
    }
}
