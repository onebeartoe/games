
package org.onebeartoe.minecraft.advancements;

import java.io.IOException;
import net.minecraft.advancements.Advancements;
import net.minecraft.advancements.AdvancementsService;
import org.json.simple.parser.ParseException;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

//TODO: correct the spelling of this class' name
/**
 *
 */
public class HunbandryAdvancementsServiceTest
{
    //TODO: move this to the contrucutor once Advancement class replaces the List<String>    
    private final AdvancementsService implementation;
    
    private final Advancements minecraftAdvancements;

    public HunbandryAdvancementsServiceTest() throws IOException, ParseException 
    {
        implementation = new AdvancementsService();
        
        minecraftAdvancements = implementation.load();
    }

    @Test
    public void aBalancedDiet()
    {
        var criteria = minecraftAdvancements.husbandry.balancedDiet.criteria;
        
        var actual = criteria.size();

        var expected = 40;
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void twoByTwo()
    {
        var criteria = minecraftAdvancements.husbandry.twoByTwo.criteria;
        
        var actual = criteria.size();
        
        var expected = 25;
        
        assertEquals(expected, actual);
    }
}
