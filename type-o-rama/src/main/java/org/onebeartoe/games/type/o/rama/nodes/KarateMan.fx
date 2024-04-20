
package net.onebeartoe.type.areli.nodes;

import javafx.scene.Node;
import javafx.scene.Group;
import java.lang.UnsupportedOperationException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class KarateMan extends Cannon
{
    public var halfLength: Number = 200;

    public var startX = 0;//50;
    public var startY = 0;//250;

    public var endX = 50;

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
        animation.play();

        Group
        {
            layoutX: bind layoutX;

            layoutY: bind layoutY;

            content:
            [

            ]
        }
    }

    override public function onWackaWacka () : Void
    {
        throw new UnsupportedOperationException('Not implemented yet');
    }

}

