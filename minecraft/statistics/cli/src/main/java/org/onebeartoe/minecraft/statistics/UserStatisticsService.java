
package org.onebeartoe.minecraft.statistics;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 */
public class UserStatisticsService 
{
    public StatisticsReport parse(File infile) throws IOException, ParseException
    {
//userStatisticsFile
        JSONParser parser = new JSONParser();

//        File inile = new File(statsPath);

        String s = Files.readString(infile.toPath() );
        
        Object obj = parser.parse(s);

        JSONObject base = (JSONObject) obj;
        JSONObject stats = (JSONObject) base.get("stats");
        
        JSONObject minecraft_mined = (JSONObject) stats.get("minecraft:mined");
        
        JSONObject broken = (JSONObject) stats.get("minecraft:broken");
        JSONObject crafted = (JSONObject) stats.get("minecraft:crafted");
        JSONObject dropped = (JSONObject) stats.get("minecraft:dropped");
        
        JSONObject killed = (JSONObject) stats.get("minecraft:killed");
        
        JSONObject killedBy = (JSONObject) stats.get("minecraft:killed_by");
        
        
        JSONObject pickedUp = (JSONObject) stats.get("minecraft:picked_up");
        
        JSONObject used = (JSONObject) stats.get("minecraft:used");
        
        
//TODO: !!!!do something with CUSTOM!!!!        
        JSONObject custom = (JSONObject) stats.get("minecraft:custom");        
        
        
        StatisticsReport report = new StatisticsReport();
        
        report.minecraft_mined = minecraft_mined;
        report.killed = killed;        
        report.used = used;        
        report.custom = custom; 
        
        return report;
    }
}
