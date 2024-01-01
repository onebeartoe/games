
package org.onebeartoe.minecraft.advancements;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Advancement
{
    String name;
    
    String description;

    List<String> criteria;

    public Advancement()
    {
        criteria = new ArrayList();
    }
}
