
package net.onebeartoe.type.areli.factories;

import net.onebeartoe.type.areli.targets.WordTarget;

public abstract class  WordTargetFactory
{
    public var xRange: Number;

    public var targetMaxX: Number;

    public var targetMaxY: Number;
    public var targetMinY: Number;

    public abstract function createTargets(words: String []): WordTarget [];

    public function initializeTargets(targets : WordTarget []) : Void
    {
        
    }


}
