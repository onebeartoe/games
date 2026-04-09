package net.onebeartoe.type.areli.nodes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class RobotChicken extends Cannon {
    private ImageView imageView;
    private Ellipse muzzleFlash;

    public RobotChicken() {
        this.cannonTipX = 20;
        this.cannonTipY = 30;

        Image image = new Image(getClass().getResourceAsStream("/net/onebeartoe/type/areli/nodes/robot-chicken-b.png"));
        imageView = new ImageView(image);
        imageView.setScaleX(0.7);
        imageView.setScaleY(0.7);

        muzzleFlash = new Ellipse();
        muzzleFlash.setRadiusX(4);
        muzzleFlash.setRadiusY(8);
        muzzleFlash.setRotate(-40);
        muzzleFlash.setFill(Color.GREEN);

        // Binding equivalents in Java
        muzzleFlash.centerXProperty().bind(this.translateXProperty().add(image.getWidth() * imageView.getScaleX() + 30));
        muzzleFlash.setCenterY(cannonTipY + 0.11 * image.getHeight());

        getChildren().addAll(imageView, muzzleFlash);

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
