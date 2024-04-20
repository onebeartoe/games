
package net.onebeartoe.type.areli.targets;

import javafx.scene.Node;
import javafx.scene.Group;
import java.lang.UnsupportedOperationException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DiagnalWordTarget extends WordTarget
{
    public var halfLength: Number = 200;

    public var startX = 0;//50;
    public var startY = 0;//250;    

    public var endX = 50;
    public var endY = 50;

    override public var background = Rectangle
    {
        width: 140
        height: 90
        fill: Color.LIGHTGREEN
        arcHeight: 5
        arcWidth: 5

        stroke: Color.GREEN
        strokeWidth: 5
    }

    override public var label = Label
    {
        layoutX: bind labelX
        layoutY: bind labelY

        font: Font.font("Trebuchet MS",20);

        text: bind labelText
        textWrap: true
        textAlignment: TextAlignment.JUSTIFY

        width: bind scene.width * 0.2
        height: 200
    }

    override public var animation = Timeline
    {
        autoReverse: true

        repeatCount: Timeline.INDEFINITE

        keyFrames:
        [
            KeyFrame
            {
                time: 5s,
                values:
                [
                   translateX => xMax,
                   translateY => yMax
                ]
            }

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
            layoutX: bind layoutX;

            layoutY: bind layoutY;

            content:
            [
                background,
                label
            ]
        }
    }

    public override function getWordssssss(): String []
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

