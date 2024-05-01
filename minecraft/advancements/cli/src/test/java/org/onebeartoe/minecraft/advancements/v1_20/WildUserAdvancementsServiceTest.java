
package org.onebeartoe.minecraft.advancements.v1_20;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import org.json.simple.parser.ParseException;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author roberto
 */
public class WildUserAdvancementsServiceTest 
{
    private WildUserAdvancementsService implementation;
    
    @BeforeTest
    private void initializeImplementation() throws IOException, ParseException, URISyntaxException
    {
     final String resourcesPath = "src/test/resources/";
    
     final String savesPath = resourcesPath + "minecraft/saves/1.20/";

//TODO: path this value in as a constructor argument    
     String advancementsPath = savesPath +  "advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";            
        
        File infile = new File(advancementsPath);
        
        Path inputFile = infile.toPath();
                
        implementation = new WildUserAdvancementsService(inputFile);
    }

    /**
     * The sample input file should have 2 missing biomes. 
     */
    @Test
    public void adventureTiming()
    {
        List<String> missingTimes = implementation.missingAdventureTimes();
        
        int expected = 8;
        
        var actual = missingTimes.size();
        
        assertEquals(expected, actual);
    }
}
