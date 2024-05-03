
package net.onebeartoe.type.areli.targets;

import javafx.scene.Node;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;

public abstract class WordTarget extends Node
{
    public IntegerProperty labelX = new SimpleIntegerProperty(20);
    
    public IntegerProperty labelY  = new SimpleIntegerProperty(-20);

    public Label label;

    public KeyFrame removeFrame ;

    public Timeline animation ;

    public Number xMax;

    public Number yMax;

    public StringProperty labelText;

    public Node background;

    public abstract String [] getWordssssss();

    public abstract void onWackaWacka();
}
