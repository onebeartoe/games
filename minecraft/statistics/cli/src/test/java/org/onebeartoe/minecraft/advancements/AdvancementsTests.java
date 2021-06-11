
package org.onebeartoe.minecraft.advancements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

/**
 *  These tests explore the advancements in a Minecraft world for a single player.
 * 
 * The tests assume the world data is stored in the default location:
 * 
 *     ~/.minecraft/saves/Dragon Fart 2020 - 1_15_2/
 * 
 */
public class AdvancementsTests 
{    
    @Test
    public void advancementsTest() throws IOException, ParseException
    {
        List<Advancement> allAdvancements = loadAllAdvancements();
    }

    private List<Advancement> loadAllAdvancements() throws IOException, ParseException 
    {
        JSONParser parser = new JSONParser();
        
        String statsPath = "/home/roberto/.minecraft/saves/Dragon Fart 2020 - 1_15_2/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
        
        File inile = new File(statsPath);

        String s = Files.readString( inile.toPath() );
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        
        System.out.println("Advancements Not Done");
        base.forEach((t, u) -> 
        {
//            System.out.println(t + " - " + u.getClass());
            
            String name = t.toString();
            
            if( !name.equals("DataVersion") )   // skip the DataVersion entry
            {
                JSONObject payload = (JSONObject) u;

                boolean done = (boolean) payload.get("done");

                if( !done )
                {
                    System.out.println(t.toString() + "");
                }
            }
        });
        
        return null;
    }
    
    private class Advancement
    {
        String name;
        
        List<String> criteria;
        
        boolean done;
    }
}
