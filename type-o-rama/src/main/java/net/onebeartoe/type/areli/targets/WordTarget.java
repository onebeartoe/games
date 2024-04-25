
package net.onebeartoe.type.areli.targets;

import javafx.scene.Node;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;

public abstract class WordTarget extends Node
{
    public Number labelX  = 20;
    
    public Number labelY  = -20;

    public Label label;

    public KeyFrame removeFrame ;

    public Timeline animation ;

    public Number xMax;

    public Number yMax;

    public String labelText;

    public Node background;

    public abstract String [] getWordssssss();

    public abstract void onWackaWacka();
}
