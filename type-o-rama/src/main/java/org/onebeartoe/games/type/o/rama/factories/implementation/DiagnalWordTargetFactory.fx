
package net.onebeartoe.type.areli.factories.implementation;

import net.onebeartoe.type.areli.factories.WordTargetFactory;
import net.onebeartoe.type.areli.targets.WordTarget;
import net.onebeartoe.type.areli.targets.DiagnalWordTarget;
import java.util.Random;

public class DiagnalWordTargetFactory extends WordTargetFactory
{
    var random : Random = Random{};

    override public function createTargets (words : String[]) : WordTarget[]
    {
        var targets: WordTarget [];

        for(word in words)
        {
            var t: WordTarget = DiagnalWordTarget
            {
                labelText: word

                var x = random.nextInt(xRange*0.8);                
                translateX: x
                translateY: random.nextInt(targetMaxY) - targetMinY;
                xMax: targetMaxX
                yMax: targetMaxY
            }
            insert t into targets
        }

        targets
    }


}
