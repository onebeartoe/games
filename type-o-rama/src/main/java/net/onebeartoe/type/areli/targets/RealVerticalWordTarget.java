/*
 * RealVerticalWordTarget.fx
 *
 * Created on Jul 30, 2010, 8:14:37 AM
 */

package net.onebeartoe.type.areli.targets;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * @author lando (ha! I did this on the MacBook I won from Cameron Purdy!)
 */
//TODO: rename this class to remove 'Real' 
public class RealVerticalWordTarget extends StaticWordTarget
{
    public RealVerticalWordTarget()
    {
        animation = new Timeline();
        animation.autoReverseProperty().setValue(Boolean.TRUE);
        animation.setCycleCount(Timeline.INDEFINITE);
        
        Duration duration = Duration.millis(5000) ;

        KeyValue keyValue = new KeyValue(translateYProperty(), yMax);
        
        KeyFrame keyFrame = new KeyFrame(duration, keyValue);

        animation.getKeyFrames()
                    .add(keyFrame);
    }
}
