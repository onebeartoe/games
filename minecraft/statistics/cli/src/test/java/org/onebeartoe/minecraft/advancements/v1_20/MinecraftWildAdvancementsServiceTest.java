/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.onebeartoe.minecraft.advancements.v1_20;

import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 */
public class MinecraftWildAdvancementsServiceTest 
{
    private MinecraftWildAdvancementsService implementation;
    
    @BeforeTest
    private void initializeImplementation() throws IOException, ParseException
    {
        implementation = new MinecraftWildAdvancementsService();
    }
    
    @Test
    public void adventureTiming()
    {
        List<String> allAdventureTimes = implementation.allAdventureTimes();
        
        int expected = 53;
        
        var actual = allAdventureTimes.size();
        
        assertEquals(expected, actual);
    }    
}
