
package net.onebeartoe.type.areli.nodes;

//import javafx.scene.CustomNode;

import javafx.animation.Timeline;
import javafx.scene.Group;

public abstract class Cannon
        extends Group
//        extends CustomNode
{
    public int cannonTipX = 20;

    public int cannonTipY  = 30;
        
    public Timeline animation;

    public Number xMax ;

    public Number yMax ;

    public abstract void onWackaWacka();
}
