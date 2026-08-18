package net.onebeartoe.type.areli.factories.implementation;

import net.onebeartoe.type.areli.factories.WordTargetFactory;
import net.onebeartoe.type.areli.targets.WordTarget;
import net.onebeartoe.type.areli.targets.DiagnalWordTarget;
import java.util.Random;

public class DiagnalWordTargetFactory extends WordTargetFactory {
    private Random random = new Random();

    @Override
    public WordTarget[] createTargets(String[] words) {
        WordTarget[] targets = new WordTarget[words.length];

        for (int i = 0; i < words.length; i++) {
            DiagnalWordTarget t = new DiagnalWordTarget();
            t.setLabelText(words[i]);

            double startX = targetMinX + random.nextDouble() * ((targetMaxX - targetMinX) * 0.4);
            double endX = startX + 150 + random.nextDouble() * 200;
            endX = Math.min(targetMaxX, endX);

            double startY = targetMinY + random.nextDouble() * 50;
            double endY = Math.min(targetMaxY, startY + 100 + random.nextDouble() * 100);

            t.setTranslateX(startX);
            t.setTranslateY(startY);
            t.setXMax(endX);
            t.setYMax(endY);

            targets[i] = t;
        }

        return targets;
    }
}
