
package net.onebeartoe.type.areli.targets;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;

public class VerticalWordTarget extends HorizontalWordTarget
{
    public VerticalWordTarget()            
//    override  var animation = Timeline
    {
        y = new SimpleIntegerProperty(2);
        
        Duration duration = Duration.seconds(5);
        KeyValue keyValue = new KeyValue(y, yMax);
        KeyFrame keyFrame = new KeyFrame(duration, keyValue);
                
        var animation = new Timeline(keyFrame);
        
        animation.setAutoReverse(true);
        animation.setCycleCount(Timeline.INDEFINITE);


//        keyFrames:
//        [
//
//            KeyFrame
//            {
//                time: 5000ms,
//                values:
//                [
//                        y => xMax
//                ]
//            }            
//        ]


//TODO: is a call to animation.play() needed?????
    }
}
