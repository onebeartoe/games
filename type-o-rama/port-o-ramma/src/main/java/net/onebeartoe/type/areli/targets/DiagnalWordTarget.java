package net.onebeartoe.type.areli.targets;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class DiagnalWordTarget extends WordTarget {

    public DiagnalWordTarget() {
        Rectangle rect = new Rectangle(140, 90);
        rect.setFill(Color.LIGHTGREEN);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setStroke(Color.GREEN);
        rect.setStrokeWidth(4);
        this.background = rect;

        label = new Label();
        label.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
        label.setTextFill(Color.DARKGREEN);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(130);

        getChildren().addAll(background, label);
    }

    @Override
    public void setXMax(double xMax) {
        super.setXMax(xMax);
        updateAnimation();
    }

    @Override
    public void setYMax(double yMax) {
        super.setYMax(yMax);
        updateAnimation();
    }

    public void updateAnimation() {
        if (animation != null) {
            animation.stop();
        }

        animation = new Timeline(
            new KeyFrame(Duration.seconds(5),
                new KeyValue(this.translateXProperty(), xMax),
                new KeyValue(this.translateYProperty(), yMax)
            ),
            new KeyFrame(Duration.millis(850))
        );
        animation.setAutoReverse(true);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    @Override
    public String[] getWordssssss() {
        return new String[] {"HAPPY", "SMILE", "ANT"};
    }

    @Override
    public void onWackaWacka() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
