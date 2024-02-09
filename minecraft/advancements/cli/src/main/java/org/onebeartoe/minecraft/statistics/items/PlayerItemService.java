
package org.onebeartoe.minecraft.statistics.items;

import java.io.File;
import java.io.IOException;
import static java.nio.file.StandardCopyOption.values;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.advancements.PlayerAdvancementsService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 */
public class PlayerItemService
{
    PlayerAdvancementsService playerAdvancementsService;

    public PlayerItemService() throws IOException, ParseException
    {
        playerAdvancementsService = new PlayerAdvancementsService();
    }            

    public PlayerItems load(File infile) throws IOException, ParseException
    {
        var advancementsPath = infile.getAbsolutePath();

        JSONObject json = playerAdvancementsService.loadBase(advancementsPath);

        Collection<String> collection = json.values();
        
        List<String> trims = new ArrayList();
        
        Stream.of(collection.toArray() )
                .forEach(value -> 
                {
                    String s = value.toString();
                    
                    if(s.contains("armor_trim"))
                    {
                        trims.add(s);
                    }
                });
        
        System.out.println("trims size = " + trims.size() );
        
        System.out.println("trims = " + trims);
        
        System.out.println("values size = " + collection.size() );
        
        System.out.println("values = " + collection);
        
        var entrySet = json.entrySet();
        
        System.out.println("entrySet size = " + entrySet.size() );
        
        System.out.println("entrySet = " + entrySet);
        
        PlayerItems playerItems = new PlayerItems();
        
        playerItems.aquiredArmorTrims(trims);

        return playerItems;
    }
}
