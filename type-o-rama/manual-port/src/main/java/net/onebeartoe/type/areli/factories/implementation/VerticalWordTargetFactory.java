
package net.onebeartoe.type.areli.factories.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.onebeartoe.type.areli.factories.WordTargetFactory;
import net.onebeartoe.type.areli.targets.RealVerticalWordTarget;
import net.onebeartoe.type.areli.targets.WordTarget;

public class VerticalWordTargetFactory extends WordTargetFactory
{
    

    @Override
    public List<WordTarget> createTargets (String[] words)
    {
        var targets = new ArrayList<WordTarget>();

        for(String word : words)
        {
            WordTarget t = new RealVerticalWordTarget();
            
            t.labelText.setValue(word);
            
            var xMax = (int) (xRange*0.8);
            
            var x = random.nextInt(xMax);
            
            t.setTranslateX(x);
            
            t.setTranslateY( random.nextInt(targetMaxY) - targetMinY );
            
            t.xMax = targetMaxX;
                    
            t.yMax = targetMaxY;
                        
            targets.add(t);
        }

        return targets;
    }
}
