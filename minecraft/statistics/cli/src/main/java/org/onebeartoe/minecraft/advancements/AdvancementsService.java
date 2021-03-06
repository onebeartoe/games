
package org.onebeartoe.minecraft.advancements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class provides method to parse and query Minecraft Advancements data 
 * files.
 */
public class AdvancementsService
{
    public List<String> getNonBreedMobs()
    {
        return null;
    }

    public List<Advancement> loadUserAdvancements() throws IOException, ParseException 
    {
        JSONParser parser = new JSONParser();
        
        String statsPath = "/home/roberto/.minecraft/saves/Dragon Fart 2020 - 1_15_2/advancements/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";

        File inile = new File(statsPath);

        String s = Files.readString( inile.toPath() );
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        
        List<Advancement> notDoneAdvancements = new ArrayList();
        
        System.out.println("Advancements Not Done");
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
                    
                    System.out.println(advanementName.toString() + "");
                    
                    System.out.println("\tFor " + advanementName + ", you have the following criteria");
                    criteria.forEach((name, date) ->
                    {
                        System.out.println("\t\t" + name);
                        
                        Advancement adv = new Advancement();
                        adv.name = name.toString();
                        
                        notDoneAdvancements.add(adv);
                    });                    
                }
            }
        });
        
        return notDoneAdvancements;
    }

    public List<Advancement> incompleteUserAdvancements() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
