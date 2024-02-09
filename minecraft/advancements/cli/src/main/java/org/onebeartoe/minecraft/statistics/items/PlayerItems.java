
package org.onebeartoe.minecraft.statistics.items;

import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 */
public class PlayerItems
{
    List<String> armorTrims;

    public List<String> aquiredArmorTrims() throws IOException, ParseException
    {
        return armorTrims;
    }
    
    public void aquiredArmorTrims(List<String> armorTrims)
    {
        this.armorTrims = armorTrims;
    }
}
