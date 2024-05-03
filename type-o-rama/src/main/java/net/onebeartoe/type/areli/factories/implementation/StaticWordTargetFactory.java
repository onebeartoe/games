
package net.onebeartoe.type.areli.factories.implementation;

import java.util.ArrayList;
import java.util.List;
import net.onebeartoe.type.areli.factories.WordTargetFactory;
import net.onebeartoe.type.areli.targets.WordTarget;
import java.util.Random;
import javafx.beans.property.SimpleStringProperty;
import net.onebeartoe.type.areli.targets.StaticWordTarget;

public class StaticWordTargetFactory extends WordTargetFactory
{
    Random random = new Random();

    @Override 
    public List<WordTarget> createTargets (String[] words) 
    {
        List<WordTarget> targets = new ArrayList();

        for(String word : words)
        {
            WordTarget t = new StaticWordTarget();
            
            t.labelText = new SimpleStringProperty(word);
            
            int xMax = (int) (xRange*0.8);
            var x = random.nextInt(xMax);
            t.translateXProperty().setValue(x);

            t.translateYProperty().setValue(random.nextInt(targetMaxY) - targetMinY);
            
            t.xMax = targetMaxX;
            
            t.yMax = targetMaxY;
            
            targets.add(t);
        }

        return targets;
    }
}
