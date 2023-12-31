
package org.onebeartoe.minecraft.statistics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static org.onebeartoe.minecraft.statistics.StatisticsService.jsonToStatistics;

/**
 *
 */
public class UserStatisticsService 
{
    public StatisticsReport parse(File infile) throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();



        String s = Files.readString(infile.toPath() );
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        JSONObject stats = (JSONObject) base.get("stats");
        
        JSONObject minecraft_mined = (JSONObject) stats.get("minecraft:mined");
        
        JSONObject broken = (JSONObject) stats.get("minecraft:broken");
        JSONObject crafted = (JSONObject) stats.get("minecraft:crafted");
        JSONObject dropped = (JSONObject) stats.get("minecraft:dropped");
        
        StatisticsReport report = new StatisticsReport();
        
        JSONObject jsonKilled = (JSONObject) stats.get("minecraft:killed");
        List<Statistic> killedMobs = jsonToStatistics(jsonKilled);
        report.missingHostileMobKills = parseMissingHostileMobKills(killedMobs);
        

//TODO: get the top ten off of killed_by        
        JSONObject killedBy = (JSONObject) stats.get("minecraft:killed_by");
        
        
        JSONObject pickedUp = (JSONObject) stats.get("minecraft:picked_up");
        
        JSONObject used = (JSONObject) stats.get("minecraft:used");
        
        
//TODO: !!!!do something with CUSTOM!!!!        
        JSONObject custom = (JSONObject) stats.get("minecraft:custom");        
        
        
        
  
        
        
        report.minecraft_mined = minecraft_mined;
//        report.killed = jsonKilled;        
        report.used = used;        
        report.custom = custom; 
        
        return report;
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

//    public List<String> missingHostilModKills()
//    {
//        
//        
//        return List.copyOf(coll)
//    }

    private List<String> parseMissingHostileMobKills(List<Statistic> killedMobs) throws IOException
    {            
        List<String> allHostileMobs = loadHostileMobs();
        
        System.out.println();
        System.out.println("Missing Hostile Mob Kills");
        
        List<String> missingMobKills = new ArrayList();
        
        allHostileMobs.forEach(m ->
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
                
                missingMobKills.add(m);
            }
        });
        
        return missingMobKills;
    }
}
