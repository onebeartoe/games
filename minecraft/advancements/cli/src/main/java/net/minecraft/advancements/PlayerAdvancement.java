
package net.minecraft.advancements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class PlayerAdvancement
{
    String name;
    
    String description;
    
    public Map<String, Boolean> criteria;

    public PlayerAdvancement()
    {
        criteria = new HashMap();
    }

    public List<String> haves() 
    {
        var haves = new <String> ArrayList();
        
        criteria.forEach((desc, achieved) -> 
        {
            if(achieved)
            {
                haves.add(desc);
            }
        });
        
        return haves;
    }

    public List<String> haveNots() 
    {
        var haveNots = new <String> ArrayList();
        
        criteria.forEach((desc, achieved) -> 
        {
            if(!achieved)
            {
                haveNots.add(desc);
            }
        });
        
        return haveNots;
    }
}
