
package org.onebeartoe.minecraft.playerdata;

import java.io.IOException;
import java.net.URISyntaxException;
import org.json.simple.parser.ParseException;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

/**
 *
 */
public class PlayerDataServiceTest
{
    private final PlayerData playerData;
    
    public PlayerDataServiceTest() throws IOException, URISyntaxException, InterruptedException, ParseException
    {
        PlayerDataService implementation = new PlayerDataService();
    
        String inpath = "src/test/resources/minecraft/saves/1.20/level.dat";
//        String inpath = "src/test/resources/minecraft/saves/1.20/playerdata/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.dat";
        
        playerData = implementation.load(inpath);
    }
    
    @Test
    public void name()
    {
        var actual = playerData.name;
        
        var expected = "betoblock";
        
        assertEquals(expected, actual);
    }
}
