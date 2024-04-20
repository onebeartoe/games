
package net.onebeartoe.type.areli.targets;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class VerticalWordTarget extends HorizontalWordTarget
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
                        y => xMax
                ]
            }
            ,

        ]
    }
}
