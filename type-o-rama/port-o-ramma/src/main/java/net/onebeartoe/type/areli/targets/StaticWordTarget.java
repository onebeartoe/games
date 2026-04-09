package net.onebeartoe.type.areli.targets;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class StaticWordTarget extends WordTarget {
    
    public StaticWordTarget() {
        Rectangle rect = new Rectangle(140, 90);
        rect.setFill(Color.GREEN);
        rect.setArcHeight(5);
        rect.setArcWidth(5);
        this.background = rect;

        label = new Label();
        label.setLayoutX(labelX);
        label.setLayoutY(labelY);
        label.setFont(Font.font("Trebuchet MS", 20));
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.JUSTIFY);
        // In modern JavaFX, we don't bind to scene.width as easily in the constructor
        // We'll set a default or use a listener later if needed.
        label.setPrefWidth(200); 

        getChildren().addAll(background, label);

        removeFrame = new KeyFrame(Duration.millis(850));
        
        animation = new Timeline(
            new KeyFrame(Duration.millis(2500)),
            new KeyFrame(Duration.seconds(5)),
            removeFrame
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
