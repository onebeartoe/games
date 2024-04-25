
package net.onebeartoe.type.areli.factories;

import net.onebeartoe.type.areli.targets.WordTarget;

public abstract class  WordTargetFactory
{
    public Number xRange;

    public Number targetMaxX;

    public Number targetMaxY;
    public Number targetMinY;

    public abstract WordTarget [] createTargets( String [] words) ;

    public void initializeTargets(WordTarget [] targets) 
    {
        
    }


}
