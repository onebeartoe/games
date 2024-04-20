
package net.onebeartoe.type.areli.attacks;

import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import java.lang.UnsupportedOperationException;
import javafx.scene.shape.Line;
import javafx.scene.effect.Glow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class LineBeam extends Attack
{
    public var halfLength: Number = 200;

    public var startX = 0;//50;
    public var startY = 0;//250;    

    public var endX: Number = 50;
    public var endY: Number = 50;
    
    var line = Line
    {
	startX: bind startX
        startY: bind startY

	endX: bind endX
        endY: bind endY

	strokeWidth: 10
	stroke: Color.LIMEGREEN

        effect: Glow
        {
            level: 1
        }
    }

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
            },
            removeFrame = KeyFrame
            {
                time: 850ms,
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
            content:
            [
               line
            ]
        }
    }

    public override function getWords(): String []
    {
        var words : String [] =
        [
            "HAPPY", "SMILE", "ANT"
        ]

    }


    override public function onWackaWacka () : Void
    {
        throw new UnsupportedOperationException('Not implemented yet');
    }

}

