
package net.onebeartoe.type.areli.targets;

import javafx.scene.Node;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public abstract class WordTarget extends Group
{
    public IntegerProperty labelX = new SimpleIntegerProperty(20);
    
    public IntegerProperty labelY  = new SimpleIntegerProperty(-20);

    public Label label;

    public KeyFrame removeFrame ;

    public Timeline animation ;

    public Number xMax;

    public Number yMax;

    public StringProperty labelText;

    public Rectangle background;
//    public Node background;

//TODO: remove this    
    public abstract String [] getWordssssss();

//TODO: remove this
    public abstract void onWackaWacka();
}
