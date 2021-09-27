
package org.onebeartoe.minecraft.advancements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class provides service methods for user specific advancements.
 */
public class UserAdvancementsService
{    
    private List<String> balancedDietItems;
            
    private List<Advancement> incompleteUserAdvancements;

    public UserAdvancementsService() throws IOException, ParseException
    {        
        String statsPath = "/home/roberto/.minecraft/saves/Dragon Fart 2020 - 1_15_2/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";

        File inile = new File(statsPath);
                
        JSONParser parser = new JSONParser();

        String s = Files.readString( inile.toPath() );
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        
        parseIncompleteUserAdvancements(base);
        
        JSONObject dietItems = (JSONObject) base.get("minecraft:husbandry/balanced_diet");
        parseBalancedDiet(dietItems);
    }

    public List<String> balancedDietItems()
    {
        return List.copyOf(balancedDietItems);                
    }
    
    public List<String> unbredMobs()
    {
        List unbreadMobs = null;
        
        return unbreadMobs;
    }

    public List<Advancement> incompleteUserAdvancements() throws IOException, ParseException 
    {
        return incompleteUserAdvancements;
    }
    
    public void parseIncompleteUserAdvancements(JSONObject base) throws IOException, ParseException 
    {                
        incompleteUserAdvancements = new ArrayList();

        base.forEach((advanementName, u) -> 
        {
            Class<? extends Object> uClass = u.getClass();
                                    
            if( !advanementName.equals("DataVersion") )   // skip the DataVersion entry
            {
                JSONObject payload = (JSONObject) u;
                
                JSONObject criteria = (JSONObject) payload.get("criteria");
                
                boolean done = (boolean) payload.get("done");
                
                if( !done )
                {
                    Class<? extends Object> tClass = advanementName.getClass();

                    criteria.forEach((name, date) ->
                    {                        
                        Advancement adv = new Advancement();
                        adv.name = name.toString();
                        
                        incompleteUserAdvancements.add(adv);
                    });                    
                }
            }
        });
    }

    public List<Advancement> loadUserAdvancements() throws IOException, ParseException 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void parseBalancedDiet(JSONObject dietItems) 
    {
        List<String> allItems = new ArrayList<String>();
        
        JSONObject criteria = (JSONObject) dietItems.get("criteria");
        
        criteria.forEach((advanementName, u) -> 
        {
            String itemName = advanementName.toString();
            
            allItems.add(itemName);            
        });
        
//        Stream sorted 
balancedDietItems                
                = allItems.stream()
                .sorted()
                .collect( Collectors.toList() );
        
//        balancedDietItems = allItems;
    }
}
