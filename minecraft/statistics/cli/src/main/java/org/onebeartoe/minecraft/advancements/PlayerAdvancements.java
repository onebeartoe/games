
package org.onebeartoe.minecraft.advancements;

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
