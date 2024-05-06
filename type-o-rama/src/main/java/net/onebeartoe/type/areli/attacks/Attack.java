
package net.onebeartoe.type.areli.attacks;

//import javafx.scene.CustomNode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;

public abstract class Attack extends Node
//public abstract class Attack extends CustomNode
{
    public KeyFrame removeFrame;

    public Timeline animation;

    public abstract String [] getWords();

    public abstract void onWackaWacka();
}
