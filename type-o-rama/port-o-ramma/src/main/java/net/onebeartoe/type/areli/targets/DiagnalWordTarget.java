package net.onebeartoe.type.areli.targets;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class DiagnalWordTarget extends WordTarget {
    
    public DiagnalWordTarget() {
        Rectangle rect = new Rectangle(140, 90);
        rect.setFill(Color.LIGHTGREEN);
        rect.setArcHeight(5);
        rect.setArcWidth(5);
        rect.setStroke(Color.GREEN);
        rect.setStrokeWidth(5);
        this.background = rect;

        label = new Label();
        label.setLayoutX(labelX);
        label.setLayoutY(labelY);
        label.setFont(Font.font("Trebuchet MS", 20));
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        label.setPrefWidth(200);

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

    private void updateAnimation() {
        if (animation != null) {
            animation.stop();
        }

        animation = new Timeline(
            new KeyFrame(Duration.seconds(5),
                new KeyValue(this.translateXProperty(), xMax),
                new KeyValue(this.translateYProperty(), yMax)
            ),
            new KeyFrame(Duration.millis(850)) // removeFrame logic was empty in original
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
