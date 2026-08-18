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

            double xSpan = Math.max(10, targetMaxX - targetMinX);
            double x = targetMinX + random.nextDouble() * xSpan;
            double startY = targetMinY + random.nextDouble() * 50;
            double endY = Math.min(targetMaxY, startY + 100 + random.nextDouble() * 100);

            t.setTranslateX(x);
            t.setTranslateY(startY);
            t.setXMax(targetMaxX);
            t.setYMax(endY);

            targets[i] = t;
        }

        return targets;
    }
}
