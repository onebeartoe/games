package net.onebeartoe.type.areli.attacks;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class LineBeam extends Attack {
    private double halfLength = 200;
    private double startX = 0;
    private double startY = 0;
    private double endX = 50;
    private double endY = 50;
    private Line line;

    public LineBeam() {
        line = new Line();
        line.startXProperty().bind(this.layoutXProperty().add(startX)); // Simplified binding
        line.startYProperty().bind(this.layoutYProperty().add(startY));
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStrokeWidth(10);
        line.setStroke(Color.LIMEGREEN);
        
        Glow glow = new Glow();
        glow.setLevel(1);
        line.setEffect(glow);

        getChildren().add(line);

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
    public String[] getWords() {
        return new String[] {"HAPPY", "SMILE", "ANT"};
    }

    @Override
    public void onWackaWacka() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // Getters and Setters
    public double getStartX() { return startX; }
    public void setStartX(double startX) { this.startX = startX; line.setStartX(startX); }

    public double getStartY() { return startY; }
    public void setStartY(double startY) { this.startY = startY; line.setStartY(startY); }

    public double getEndX() { return endX; }
    public void setEndX(double endX) { this.endX = endX; line.setEndX(endX); }

    public double getEndY() { return endY; }
    public void setEndY(double endY) { this.endY = endY; line.setEndY(endY); }
}
