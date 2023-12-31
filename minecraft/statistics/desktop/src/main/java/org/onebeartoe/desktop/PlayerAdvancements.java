
package org.onebeartoe.desktop;

import org.onebeartoe.minecraft.advancements.Advancement;

/**
 *
 */
public class PlayerAdvancements
{
    AdvancementsCategory nether;
    
    AdvancementsCategory husbandry;
    
    public PlayerAdvancements()
    {
        nether = new AdvancementsCategory();

        Advancement hotTouristDestinations = new Advancement();

        nether.advancements.add(hotTouristDestinations);
    }
}
