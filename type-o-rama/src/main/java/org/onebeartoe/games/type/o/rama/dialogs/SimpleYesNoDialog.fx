
package net.onebeartoe.type.areli.dialogs;

import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.lang.UnsupportedOperationException;
import javafx.scene.shape.Line;
import javafx.scene.effect.Glow;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.lang.Integer;

/**
 * @author lando
 */
public class SimpleYesNoDialog extends YesNoDialog
{
    override public function yesAction () : Void {
        throw new UnsupportedOperationException('Not implemented yet');
    }

    public var halfLength: Number = 200;

    public var startX = 0;//50;
    public var startY = 0;//250;    

    public var endX = 50;
    public var endY = 50;

    override public var width = 440;

    override public var height = 190;

    override public var yesButtonX = bind new Integer(width * 0.2);

    override public var yesButtonY = bind new Integer(height * 0.8);

    override public var noButtonX = bind new Integer(width * 0.8);

    override public var noButtonY = bind yesButtonY;

    override public var yesButton = Text
    {
            fill: Color.LIGHTBLUE
	font : Font
        {
		size: 24
	}
//	x: 10, y: 30
        translateX: bind yesButtonX
        translateY: bind yesButtonY
	content: "Yes"
    }



    override public var noButton = Text
    {
            fill: Color.LIGHTBLUE
	font : Font
        {
		size: 24
	}
//	x: 10, y: 30
        translateX: bind noButtonX
        translateY: bind noButtonY
	content: "No"
    }

    
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

    var cos = Timeline
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
        ]
    }

   override public function create () : Node
   {
        cos.play();

        Group
        {
            content:
            [
                Rectangle
                {
    //                   x: 10, y: 10
                    width: width,
                    height: height
                    fill: Color.GREEN
                }
                ,
                Text
                {
                   font : Font
                   {
                      size: 24
                   }
                   x: 10,
                   y: 60
                   content: bind title
                }
                ,
                Text
                {
                    font : Font
                   {
                    size: 24
                   }
                   x: bind messageX,
                   y: height / 2
                   content: bind message
                }
                ,
                yesButton
                ,
                noButton
            ]
        }
    }

        public override function getWords(): String []
//    override function getWords(): String []
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

//    override public function yesAction () : Void
  //  {
    //    throw new UnsupportedOperationException('Not implemented yet');
   // }

}
