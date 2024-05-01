
package org.onebeartoe.minecraft.statistics;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 */
public class StatisticsService
{
    private StatisticsService()
    {
        
    }

//TODO: remove the static modifier once the implementation code is removed from LoadStatisticsTest.java    
    public static List<Statistic> jsonToStatistics(JSONObject killed) 
    {
        List<Statistic> stats = new ArrayList();

        killed.forEach((t, u) -> 
        {
            String name = t.toString();
            
            Integer value = Integer.valueOf( u.toString() );
            
            Statistic stat = new Statistic(name, value);
            
            stats.add(stat);
        });

        return stats;
    }    
}
