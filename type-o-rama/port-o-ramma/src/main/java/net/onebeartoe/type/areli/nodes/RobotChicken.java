package net.onebeartoe.type.areli.nodes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

import java.io.InputStream;

public class RobotChicken extends Cannon {
    private ImageView imageView;
    private Ellipse muzzleFlash;

    public RobotChicken() {
        this.cannonTipX = 141;
        this.cannonTipY = 421;

        InputStream is = getClass().getResourceAsStream("/net/onebeartoe/type/areli/nodes/robot-chicken-b.png");
        if (is != null) {
            Image image = new Image(is);
            imageView = new ImageView(image);
            imageView.setScaleX(0.7);
            imageView.setScaleY(0.7);

            muzzleFlash = new Ellipse();
            muzzleFlash.setRadiusX(4);
            muzzleFlash.setRadiusY(8);
            muzzleFlash.setRotate(-40);
            muzzleFlash.setFill(Color.GREEN);

            muzzleFlash.setCenterX(176);
            muzzleFlash.setCenterY(55);

            getChildren().addAll(imageView, muzzleFlash);
        }

        animation = new Timeline(
            new KeyFrame(Duration.millis(2500)),
            new KeyFrame(Duration.seconds(5))
        );
        animation.setAutoReverse(true);
        animation.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public void onWackaWacka() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
