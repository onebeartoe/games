
package net.onebeartoe.type.areli.nodes;

import javafx.scene.Node;
import javafx.scene.Group;
import java.lang.UnsupportedOperationException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.lang.Integer;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;

public class RobotChicken extends Cannon
{
    public RobotChicken()
    {
        cannonTipX = 20;

        cannonTipY = 30;
    }
    
    public Number halfLength  = 200;

    public int startX = 0;
    public int startY = 0;

    public int endX = 50;

    public ImageView image;

    override public var animation = Timeline
    {
        autoReverse: true

        repeatCount: Timeline.INDEFINITE

        keyFrames:
        [
            KeyFrame
            {
                time: 2500ms,
                values:
                [

                ]
            }
            ,
            KeyFrame
            {
                time: 5s,
                values:
                [

                ]
            }
        ]
    }

    override public function create () : Node
    {
        Group
        {
            layoutX: bind layoutX;

            layoutY: bind layoutY;

            content:
            [
                image = ImageView
                {
                    image:Image { url: "{__DIR__}robot-chicken-b.png"  }
                    scaleX: 0.7
                    scaleY: 0.7
                }
                ,
                Ellipse
                {
                    centerX: bind translateX + (image.image.width * image.scaleX + 30)
                    centerY: cannonTipY + 0.11 * image.image.height

                    radiusX: 4
                    radiusY: 8

                    rotate: -40;

                    fill: Color.GREEN
                }
            ]
        }
    }
}
