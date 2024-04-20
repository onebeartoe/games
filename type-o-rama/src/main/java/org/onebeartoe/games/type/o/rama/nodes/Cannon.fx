
package net.onebeartoe.type.areli.nodes;

import javafx.scene.CustomNode;
import javafx.animation.Timeline;

public abstract class Cannon extends CustomNode
{
    public var cannonTipX: Integer = 20;

    public var cannonTipY: Integer = 30;
        
    public var animation: Timeline;

    public var xMax: Number;

    public var yMax: Number;

    public abstract function onWackaWacka(): Void;
    
}
