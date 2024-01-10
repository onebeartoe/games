
package org.onebeartoe.minecraft.advancements.v1_20;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 */
public class MinecraftWildAdvancementsService 
{
    private List<String> monstersHunted;
    
    public MinecraftWildAdvancementsService() throws IOException, ParseException, URISyntaxException
    {
        String statsPath = "net/minecraft/advancements/20.json";

        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(statsPath);
        
        String s = new String(systemResourceAsStream.readAllBytes(), 
                StandardCharsets.UTF_8);
                
        JSONParser parser = new JSONParser();
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        
        JSONObject adventure = (JSONObject) base.get("adventure");

        parseAdventure(adventure);        
    }
    
    public void parseAdventure(JSONObject adventureJson)
    {
        JSONObject monstersHuntedJson = (JSONObject) adventureJson.get("minecraft:adventure/adventuring_time");
        
        JSONArray criteria = (JSONArray) monstersHuntedJson.get("criteria");
        
        monstersHunted = new ArrayList<String>();
        
        criteria.forEach(m -> 
        {
            String monster = m.toString();
            
            monstersHunted.add(monster);
        });
    }
    
    public List<String> allAdventureTimes()
    {
        return List.copyOf(monstersHunted);
    }
}
