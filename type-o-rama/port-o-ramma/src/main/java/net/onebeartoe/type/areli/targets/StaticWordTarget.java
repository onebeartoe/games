package net.onebeartoe.type.areli.targets;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class StaticWordTarget extends WordTarget {

    public StaticWordTarget() {
        Rectangle rect = new Rectangle(140, 90);
        rect.setFill(Color.GREEN);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setStroke(Color.DARKGREEN);
        rect.setStrokeWidth(2);
        this.background = rect;

        label = new Label();
        label.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
        label.setTextFill(Color.WHITE);
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(130);

        getChildren().addAll(background, label);

        removeFrame = new KeyFrame(Duration.millis(850));

        animation = new Timeline(
            new KeyFrame(Duration.millis(2500)),
            new KeyFrame(Duration.seconds(5)),
            removeFrame
        );
        animation.setAutoReverse(true);
        animation.setCycleCount(Timeline.INDEFINITE);
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
