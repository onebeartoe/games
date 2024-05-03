/*
 * RealVerticalWordTarget.fx
 *
 * Created on Jul 30, 2010, 8:14:37 AM
 */

package net.onebeartoe.type.areli.targets;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

/**
 * @author lando (ha! I did this one the MacBook I won from Cameron Purdy!)
 */

public class RealVerticalWordTarget extends StaticWordTarget
{
    override  var animation = Timeline
    {
        autoReverse: true

        repeatCount: Timeline.INDEFINITE

        keyFrames:
        [

            KeyFrame
            {
                time: 5000ms,
                values:
                [
                        translateY => yMax
                ]
            }
            ,

        ]
    }
}
