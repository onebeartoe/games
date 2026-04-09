package net.onebeartoe.type.areli.factories.implementation;

import net.onebeartoe.type.areli.factories.WordTargetFactory;
import net.onebeartoe.type.areli.targets.WordTarget;
import net.onebeartoe.type.areli.targets.RealVerticalWordTarget;
import java.util.Random;

public class VerticalWordTargetFactory extends WordTargetFactory {
    private Random random = new Random();

    @Override
    public WordTarget[] createTargets(String[] words) {
        WordTarget[] targets = new WordTarget[words.length];

        for (int i = 0; i < words.length; i++) {
            RealVerticalWordTarget t = new RealVerticalWordTarget();
            t.setLabelText(words[i]);

            double x = random.nextInt((int)(xRange * 0.8));
            t.setTranslateX(x);
            t.setTranslateY(random.nextInt((int)targetMaxY) - (int)targetMinY);
            t.setXMax(targetMaxX);
            t.setYMax(targetMaxY);
            
            targets[i] = t;
        }

        return targets;
    }
}
