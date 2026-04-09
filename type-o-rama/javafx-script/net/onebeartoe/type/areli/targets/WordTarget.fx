
package net.onebeartoe.type.areli.targets;

import javafx.scene.CustomNode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;

public abstract class WordTarget extends CustomNode
{
    public var labelX: Number = 20;
    
    public var labelY: Number = -20;

    public var label: Label;

    public var removeFrame: KeyFrame;

    public var animation: Timeline;

    public var xMax: Number;

    public var yMax: Number;

    public var labelText: String;

    public var background: Node;

    public abstract function getWordssssss(): String [];

    public abstract function onWackaWacka(): Void;
}
