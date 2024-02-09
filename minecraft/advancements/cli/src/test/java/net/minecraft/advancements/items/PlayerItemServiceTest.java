
package net.minecraft.advancements.items;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import org.onebeartoe.minecraft.advancements.PlayerAdvancementsServiceTest;
import org.onebeartoe.minecraft.statistics.items.PlayerItemService;
import org.onebeartoe.minecraft.statistics.items.PlayerItems;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

/**
 *
 */
public class PlayerItemServiceTest 
{
    private PlayerItemService implementation;
    
    private PlayerItems playerItems;
    
    public PlayerItemServiceTest() throws IOException, ParseException
    {
        implementation = new PlayerItemService();
        
        var path = PlayerAdvancementsServiceTest.advancementsPath;
//        var path = "/home/roberto/.minecraft/saves/worldo/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
        
        var infile = new File(path);
        
        playerItems = implementation.load(infile);
    }
    
    @Test
    public void armorTrims() throws IOException, ParseException
    {
        List<String> aquiredArmorTrims = playerItems.aquiredArmorTrims();
        
        int actual = aquiredArmorTrims.size();

        int expected = 12;
                
        assertEquals(expected, actual);
        
        // verify a couple 'knowns' are present
        doesHave(aquiredArmorTrims, "Vex");
        doesHave(aquiredArmorTrims, "Snout");
        
        // if manually checked which 5 were missing
        doesNotHave(aquiredArmorTrims, "Netherite");
        doesNotHave(aquiredArmorTrims, "Shaper");
        doesNotHave(aquiredArmorTrims, "Host");
        doesNotHave(aquiredArmorTrims, "Spire");
        doesNotHave(aquiredArmorTrims, "Rib");
    }
    
    private boolean contains(List<String> aquiredArmorTrims, String targetTrim)
    {
        var contains = false;
        
        for(var trim : aquiredArmorTrims)
        {
            var target = targetTrim.toLowerCase();
            
            contains = trim.contains(target);
            
            if(contains)
            {
                break;
            }
        }
 
        return contains;
    }

    private void doesHave(List<String> aquiredArmorTrims, String targetTrim)
    {
        boolean contains = contains(aquiredArmorTrims, targetTrim);
        
        assertTrue(contains);
    }

    private void doesNotHave(List<String> aquiredArmorTrims, String targetTrim) 
    {
        boolean contains = contains(aquiredArmorTrims, targetTrim);
        
        assertFalse(contains);
    }
}
