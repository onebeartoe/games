package net.onebeartoe.type.areli.targets;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class RealVerticalWordTarget extends StaticWordTarget {

    @Override
    public void setYMax(double yMax) {
        super.setYMax(yMax);
        updateAnimation();
    }

    private void updateAnimation() {
        if (animation != null) {
            animation.stop();
        }
        
        animation = new Timeline(
            new KeyFrame(Duration.millis(5000), 
                new KeyValue(this.translateYProperty(), yMax))
        );
        animation.setAutoReverse(true);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
}
