
package net.onebeartoe.type.areli.targets;

import javafx.scene.CustomNode;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HorizontalWordTarget extends CustomNode
{
    public var labelText: String;

    public var x: Integer;
    public var xMax: Integer;

    public var yMax: Integer;
    
    public var y: Integer;

    var labelX: Number = 20;
    var labelY: Number = -20;

    var background = Rectangle
    {
        width: 140
        height: 90
        fill: Color.GREEN
        arcHeight: 5
        arcWidth: 5
        stroke: Color.ALICEBLUE
        strokeWidth: 5
    }

    var label = Label
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


    public var animation = Timeline
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
                    x => xMax
                ]
            }
            ,

        ]
    }

    override public function create () : Node
    {
        animation.play();

        Group
        {
            layoutX: bind x;

            layoutY: bind y;

            content:
            [
                background,
                label
            ]
        }
    }

}
