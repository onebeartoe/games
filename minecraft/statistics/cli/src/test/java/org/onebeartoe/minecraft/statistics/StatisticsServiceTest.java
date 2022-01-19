
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

import org.json.simple.parser.ParseException;
import static org.onebeartoe.minecraft.advancements.UserAdvancementsService.savesPath;
import static org.onebeartoe.minecraft.statistics.StatisticsService.jsonToStatistics;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * This verifies the specification for StatisticsService class.
 */
public class StatisticsServiceTest 
{    
//TODO: Remove all code from this test class
    
    UserStatisticsService implementation = new UserStatisticsService();
    
    static final String statsPath = savesPath + "stats/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";
    
    @Test
    public void loadStatisticsTest() throws IOException, ParseException
    {
        File infile = new File(statsPath);
        StatisticsReport report = implementation.parse(infile);
        

        reportMined(report.minecraft_mined);
        reportKilled(report.killed);        
        reportUsed(report.used);        
        reportCustom(report.custom);        
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

    private void reportKilled(JSONObject killed) throws IOException 
    {               
        List<String> hostileMobs = loadHostileMobs();
        
        List<Statistic> killedMobs = jsonToStatistics(killed);        
        
//TODO: move this to the implementation class
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

        assertEquals(38, minecraft_soul_fire);               
    }

    private void reportCustom(JSONObject custom) 
    {
//TODO move to implementation        
        System.out.println("custom:");
        custom.forEach( (t, u) -> 
        {
            System.out.println(t + " - " + u);
        });        
    }

    private void reportUsed(JSONObject used) throws IOException 
    {
        List<String> edibleItems = loadEdibleItems();
        
        List<String> itemsEaten = loadItemsEaten(used);
        
        List<String> missingItems = new ArrayList();
        
        System.out.println();
        System.out.println("Missing Edible Items:");
        edibleItems.forEach(i ->
        {
            if( !itemsEaten.contains(i) )
            {
                missingItems.add(i);
                
                System.out.println(i);
            }
        });
        
        // get top 10 items used
        final List<Statistic> allItems = jsonToStatistics(used);

        Comparator<Statistic> comparingInt = Comparator.comparingInt(t -> t.getValue() );
        Comparator<Statistic> statsComparator = comparingInt.reversed();
        List<Statistic> topTen = allItems.stream()
                .sorted( statsComparator )                
                .limit(10)
                .collect( Collectors.toList() );
        
//TODO: move this !@!@!@!@!@!2        
        System.out.println();
        System.out.println("Top Ten Items Used");
        topTen.forEach(stat ->
        {
            System.out.println(stat.name + " - " + stat.value);
        });
        
        Statistic ironPickaxe = topTen.get(0);
        assertTrue( ironPickaxe.name.equals("minecraft:iron_pickaxe") );     
        
        int count = ironPickaxe.value;
        assertTrue( count > 20850);
        
        Statistic beetroot = topTen.get(9);
        assertTrue( beetroot.name.equals("minecraft:potato") );
        int beetrootCount = beetroot.value;
        assertTrue( beetrootCount > 9910);
    }

    private List<String> loadEdibleItems() throws IOException 
    {
//TODO: DELETE THIS FILE and USE THE JSON FILE FROM THAT NINJA GUY        
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
//TODO: fix this replace()            
            String name = t.toString().replace("minecraft:", "");
            
            usedNames.add(name);
            
  //          System.out.println(t + " - " + u);
        });

        return usedNames;
    }
}
