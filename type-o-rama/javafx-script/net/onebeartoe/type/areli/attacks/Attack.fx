
package net.onebeartoe.type.areli.attacks;

import javafx.scene.CustomNode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public abstract class Attack extends CustomNode
{

    public var removeFrame: KeyFrame;

    public var animation: Timeline;

    public abstract function getWords(): String [];

    public abstract function onWackaWacka(): Void;

}
