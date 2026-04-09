
package net.onebeartoe.type.areli.attacks;

import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.lang.UnsupportedOperationException;
import javafx.util.Math;

/**
 * @author lando
 *
 *  // sample use
 *  var beam : TrigBeam = TrigBeam
 *  {
 *      halfLength: xDistance / 2
 *      rotate: rotation
 *  }
 *
 */
public class TrigBeam extends Attack
{
    public var halfLength: Integer = 200;

    var startX: Integer = 0;//50;
    var startY = 0;//250;

    var middleXInit: Integer  = bind startX + halfLength; // (endX - startX) / 2;
    var middleYInit = startY;

    var middleX: Integer = middleXInit;
    var middleY = middleYInit;

    var endX: Integer = bind middleXInit + halfLength; // 400;
    var endY = startY;

//    public var extendo: Number;



    var controlYDelta = 200;
    var controlYMax = middleY + controlYDelta;
    var controlYMin = middleY - controlYDelta;

    var nControlY = controlYMax;
    var uControlY = controlYMin;
    
    var curveN = QuadCurve
    {
      startX: startX
      startY: startY

      controlX: 150
      controlY: bind nControlY

      endX: middleX
      endY: middleY
      
      fill: null
      stroke: Color.web("#AA4400")
      strokeWidth: 10
    }

    var curveU = QuadCurve
    {
      startX: middleX
      startY: middleY

      controlX: 250
      controlY: bind uControlY

      endX: endX
      endY: endY

      fill: null
      stroke: Color.web("#AA4400")
      strokeWidth: 10
    }

//    override public var removeFrame: KeyFr;

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
                    middleY => endX - ( (endX - middleXInit) / 2),
                    nControlY => middleYInit,
                    uControlY => middleYInit
                ]
            }
            ,
            KeyFrame
            {
                time: 5s,
                values: 
                [
                    middleY => startX + ( (middleXInit - startX) / 2),
                    nControlY => controlYMin,
                    uControlY => controlYMax,
                ]
            },
            removeFrame = KeyFrame
            {
                time: 5s + 500ms,
                values:
                [
                    middleY => startX + ( (middleXInit - startX) / 2),
                    nControlY => controlYMin,
                    uControlY => controlYMax,
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
               curveN,
               curveU
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

    public function findRotationAngle(bottomX: Number, bottomY: Number, targetX: Number, targetY: Number) : Number
    {
        var rotation : Double;
        var o;
        var h;

        var rtrig;


        // target is to the left (or directly above) of the cannon
        o = Math.sqrt ( Math.pow(targetY - bottomY, 2) );
        h = Math.sqrt( Math.pow(targetX-bottomX, 2) + Math.pow(targetY-bottomY,2) );
        var t = (o/h) ;//* ( Math.PI / 180 );


        // convert to degrees
        rtrig = Math.asin(t);
        rotation = -rtrig * (180 / Math.PI);

        return rotation;
    }


}

