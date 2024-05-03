
package net.onebeartoe.type.areli.factories;

import java.util.List;
import net.onebeartoe.type.areli.targets.WordTarget;

public abstract class  WordTargetFactory
{
    public Double xRange;

    public Number targetMaxX;

    public Integer targetMaxY;

    public Integer targetMinY;

    public abstract List<WordTarget> createTargets(String [] words) ;

    public void initializeTargets(WordTarget [] targets) 
    {
        
    }
}
