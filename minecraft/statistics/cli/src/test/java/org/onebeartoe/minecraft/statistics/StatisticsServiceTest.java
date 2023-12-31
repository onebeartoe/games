
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
//import static org.onebeartoe.minecraft.advancements.PlayerAdvancementsService.savesPath;
import static org.onebeartoe.minecraft.statistics.StatisticsService.jsonToStatistics;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * This verifies the specification for StatisticsService class.
 */
public class StatisticsServiceTest 
{    
//TODO: Remove all implementation/production code from this test class
    
    UserStatisticsService implementation = new UserStatisticsService();
    
    
//    static final String statsPath = savesPath + "stats/b8da6a01-2a0d-4df1-a86a-94a3e3da6389.json";

//TODO: !!!!!!!!Re-enable THIS!!!!!!!!!!!    
    /**
     * This test ensures the implementation uses the output path specified by the 
     * caller. 
     */
    @Test(enabled = false)    
//    @Test
    public void parse_pass_oneDotSeventeen() throws IOException, ParseException
    {
String statsPath = null;        
        File infile = new File(statsPath);
        
        StatisticsReport report = implementation.parse(infile);
   
        reportMined(report.minecraft_mined);
        parseMissingHostileMobKills(report);        
        reportUsed(report.used);        
        reportCustom(report.custom);        
    }
  
    private void parseMissingHostileMobKills(StatisticsReport report) throws IOException 
    {   
        List<String> missingMobKills = report.missingHostileMobKills;
        
        assertTrue( missingMobKills.contains("blaze") );
        assertTrue( missingMobKills.contains("chicken_jockey") );
        assertTrue( missingMobKills.contains("creeper") );
        assertTrue( missingMobKills.contains("drowned") );
        assertTrue( missingMobKills.contains("elder_guardian") );
        assertTrue( missingMobKills.contains("endermite") );
        assertTrue( missingMobKills.contains("evoker") );
        assertTrue( missingMobKills.contains("ghast") );
        assertTrue( missingMobKills.contains("guardian") );
        assertTrue( missingMobKills.contains("hoglin") );
        assertTrue( missingMobKills.contains("husk") );
        assertTrue( missingMobKills.contains("magma_cube") );
        assertTrue( missingMobKills.contains("phantom") );
        assertTrue( missingMobKills.contains("piglin_brute") );
        assertTrue( missingMobKills.contains("pillager") );
        assertTrue( missingMobKills.contains("ravager") );
        assertTrue( missingMobKills.contains("ravager_jockey") );
        assertTrue( missingMobKills.contains("shulker") );
        assertTrue( missingMobKills.contains("silverfish") );
        assertTrue( missingMobKills.contains("skeleton") );
        assertTrue( missingMobKills.contains("skeleton_horseman") );
        assertTrue( missingMobKills.contains("husk") );
        assertTrue( missingMobKills.contains("zombie_villager") );
        assertTrue( missingMobKills.contains("slime") );
        assertTrue( missingMobKills.contains("spider_jockey") );
        assertTrue( missingMobKills.contains("stray") );
        assertTrue( missingMobKills.contains("vex") );
        assertTrue( missingMobKills.contains("vindicator") );
        assertTrue( missingMobKills.contains("witch") );
        assertTrue( missingMobKills.contains("wither_skeleton") );
        assertTrue( missingMobKills.contains("zoglin") );
        assertTrue( missingMobKills.contains("zombie") );
        assertTrue( missingMobKills.contains("zombie_villager") );
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
