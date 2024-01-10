
package org.onebeartoe.minecraft.advancements.v1_20;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static net.minecraft.advancements.PlayerAdvancementsService.missingFromB;

/**
 *
 * @author roberto
 */
public class WildUserAdvancementsService 
{
    private final MinecraftWildAdvancementsService advancementsService;
    
    private List<String> adventureTimeItems;
    
    public WildUserAdvancementsService(Path inputFile) throws IOException, ParseException, URISyntaxException
    {
        advancementsService = new MinecraftWildAdvancementsService();
        
        JSONParser parser = new JSONParser();

        String s = Files.readString(inputFile);
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
                
        var adventureItems = (JSONObject) base.get("minecraft:adventure/adventuring_time");
        parseAdventureTimes(adventureItems);        
        
    }

    private void parseAdventureTimes(JSONObject adventureItems) 
    {
        List<String> allItems = new ArrayList<String>();
        
        JSONObject criteria = (JSONObject) adventureItems.get("criteria");
        
        criteria.forEach((advanementName, u) -> 
        {
            String itemName = advanementName.toString();
            
            allItems.add(itemName);            
        });
        
        adventureTimeItems
                = allItems.stream()
                .sorted()
                .collect( Collectors.toList() );
    }
 
    public List<String> missingAdventureTimes()
    {
        List<String> allAdventureTimes = advancementsService.allAdventureTimes();
        
        return missingFromB(allAdventureTimes, adventureTimeItems);
    }
}
