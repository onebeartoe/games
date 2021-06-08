
package org.onebeartoe.minecraft.statistics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author roberto
 */
public class LoadStatisticsTest 
{    
    @Test
    public void loadStatisticsTest() throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        
        String statsPath = "/home/roberto/.minecraft/saves/Dragon Fart 2020 - 1_15_2/stats/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
        
        File inile = new File(statsPath);

        String s = Files.readString(inile.toPath() );
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        JSONObject stats = (JSONObject) base.get("stats");
        
        JSONObject minecraft_mined = (JSONObject) stats.get("minecraft:mined");
        reportMined(minecraft_mined);
        
        JSONObject broken = (JSONObject) stats.get("minecraft:broken");
        JSONObject crafted = (JSONObject) stats.get("minecraft:crafted");
        JSONObject dropped = (JSONObject) stats.get("minecraft:dropped");
        
        JSONObject killed = (JSONObject) stats.get("minecraft:killed");
        reportKilled(killed);
        
        JSONObject killedBy = (JSONObject) stats.get("minecraft:killed_by");
        
        
        JSONObject pickedUp = (JSONObject) stats.get("minecraft:picked_up");
        
        JSONObject used = (JSONObject) stats.get("minecraft:used");
        reportUsed(used);
        
//TODO: !!!!do something with CUSTOM!!!!        
        JSONObject custom = (JSONObject) stats.get("minecraft:custom");
        reportCustom(custom);        
    }

    private List<String> loadHostileMobs() throws IOException 
    {
        String mobsPath = "src/test/resources/hostile-mobs.text";
        
        File mobsFile = new File(mobsPath);
        
        List<String> allLines = Files.readAllLines(mobsFile.toPath() );
        
        return allLines.stream()
                .map(m -> m.trim()
                        .toLowerCase()
                        .replace(" ", "_") )
                .collect( Collectors.toList() );    
    }

    private List<Statistic> jsonToStatistics(JSONObject killed) 
    {
        List<Statistic> stats = new ArrayList();
        
//        System.out.println("killed:");
        killed.forEach((t, u) -> 
        {
            String name = t.toString().replace("minecraft:", "");
            Integer value = Integer.valueOf( u.toString() );
            
            Statistic stat = new Statistic(name, value);
            
            stats.add(stat);
            
//            System.out.println(t + " - " + u);
        });
//        System.out.println();

        return stats;
    }
    
    private class Statistic
    {
        String name;
        
        int value;
        
        public Statistic(String name, int value)
        {
            this.name = name;
            
            this.value = value;
        }
        
        public int getValue()
        {
            return value;
        }
    }

    private void reportKilled(JSONObject killed) throws IOException 
    {               
        List<String> hostileMobs = loadHostileMobs();
        
        List<Statistic> killedMobs = jsonToStatistics(killed);        
        
        System.out.println();
        System.out.println("Missing Hostile Mob Kills");
        hostileMobs.forEach(m ->
        {
            boolean found = false;
            for(Statistic stat : killedMobs)
            {
                if( stat.name.equals(m) )
                {
                    found = true;                    
                    
                    break;
                }
            }
            
            if(!found)
            {
                // print the missing item
                System.out.println(m);
            }
        });

//TODO: herhehrerheheheh        
//        killedMobs.
    }

    private void reportMined(JSONObject minecraft_mined) 
    {
        long minecraft_lily_of_the_valley = (long) minecraft_mined.get("minecraft:lily_of_the_valley");
        long minecraft_soul_fire = (long) minecraft_mined.get("minecraft:soul_fire");
//        System.out.println(base);
//        System.out.println(stats);
//        System.out.println(minecraft_mined);
        
//        System.out.println("lily: " + minecraft_lily_of_the_valley);        

//        System.out.println("soul-fire: " + minecraft_soul_fire);                
    }

    private void reportCustom(JSONObject custom) 
    {
//        System.out.println("custom:");
        custom.forEach( (t, u) -> 
        {
//            System.out.println(t + " - " + u);
        });        
    }

    private void reportUsed(JSONObject used) throws IOException 
    {
        List<String> edibleItems = loadEdibleItems();
        
        List<String> itemsEaten = loadItemsEaten(used);
        
        System.out.println();
        System.out.println("Missing Edible Items:");
        edibleItems.forEach(i ->
        {
            if( !itemsEaten.contains(i) )
            {
                System.out.println(i);
            }
        });
        
        
        // get top 10 items used
        final List<Statistic> allItems = jsonToStatistics(used);
                
//                = new ArrayList();
//        
//        used.forEach((t, u) ->
//        {
//            String name = t.toString();
//            
//            Integer value = Integer.valueOf( u.toString() );
//            
//            Statistic stat = new Statistic(name, value);
//            
//            allItems.add(stat);
//        });
        
        Comparator<Statistic> comparingInt = Comparator.comparingInt(t -> t.getValue() );
        List<Statistic> topTen = allItems.stream()
                .sorted( comparingInt.reversed() )                
                .limit(10)
                .collect( Collectors.toList() );
        
        System.out.println();
        System.out.println("Top Ten Items Used");
        topTen.forEach(stat ->
        {
            System.out.println(stat.name + " - " + stat.value);
        });
        
// remove this print out        
//        System.out.println("used:");        
        used.forEach( (t, u) -> 
        {
//            System.out.println(t + " - " + u);
        });        
    }

    private List<String> loadEdibleItems() throws IOException 
    {
        String ediblesPath = "src/test/resources/edibles.text";
        
        File ediblesFile = new File(ediblesPath);
        
        List<String> allLines = Files.readAllLines(ediblesFile.toPath() );
        
        List<String> edibleItems = allLines.stream()
                .map(m -> m.trim()
                        .toLowerCase()
                        .replace(" ", "_") )
                .collect( Collectors.toList() );

        return edibleItems;
    }

    private List<String> loadItemsEaten(JSONObject used) 
    {
        List<String> usedNames = new ArrayList();
        
//        System.out.println("used:");
        used.forEach((t, u) -> 
        {
            String name = t.toString().replace("minecraft:", "");
            
            usedNames.add(name);
            
  //          System.out.println(t + " - " + u);
        });

        return usedNames;
    }
}
