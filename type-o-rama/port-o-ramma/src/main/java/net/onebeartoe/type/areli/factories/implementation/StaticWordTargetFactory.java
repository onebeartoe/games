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

            double xSpan = Math.max(10, targetMaxX - targetMinX);
            double ySpan = Math.max(10, targetMaxY - targetMinY);

            double x = targetMinX + random.nextDouble() * xSpan;
            double y = targetMinY + random.nextDouble() * ySpan;

            t.setTranslateX(x);
            t.setTranslateY(y);
            t.setXMax(targetMaxX);
            t.setYMax(targetMaxY);

            targets[i] = t;
        }

        return targets;
    }
}
