
package org.onebeartoe.minecraft.statistics;

import java.util.List;
import org.json.simple.JSONObject;

/**
 * This domain class represents a single user's report on its Minecraft statistics.
 */
public class StatisticsReport 
{
//TODO: convert these JSONObject members to data types    
    @Deprecated
    JSONObject minecraft_mined;        
    
    public List<String> missingHostileMobKills;
    

//TODO: convert these JSONObject members to data types    
    @Deprecated
    JSONObject used ;        
    

//TODO: convert these JSONObject members to data types    
    @Deprecated
    JSONObject custom;
}
