package net.onebeartoe.type.areli.factories.implementation;

import net.onebeartoe.type.areli.factories.WordTargetFactory;
import net.onebeartoe.type.areli.targets.WordTarget;
import net.onebeartoe.type.areli.targets.StaticWordTarget;
import java.util.Random;

public class StaticWordTargetFactory extends WordTargetFactory {
    private Random random = new Random();

    @Override
    public WordTarget[] createTargets(String[] words) {
        WordTarget[] targets = new WordTarget[words.length];

        for (int i = 0; i < words.length; i++) {
            StaticWordTarget t = new StaticWordTarget();
            t.setLabelText(words[i]);

            double x = random.nextInt((int)(xRange * 0.8));
            t.setTranslateX(x);
            t.setTranslateY(random.nextInt((int)targetMaxY) - targetMinY);
            t.setXMax(targetMaxX);
            t.setYMax(targetMaxY);
            
            targets[i] = t;
        }

        return targets;
    }
}
