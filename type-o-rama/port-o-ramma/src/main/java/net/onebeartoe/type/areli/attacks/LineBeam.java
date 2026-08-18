package net.onebeartoe.type.areli.attacks;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class LineBeam extends Attack {
    private double startX = 0;
    private double startY = 0;
    private double endX = 50;
    private double endY = 50;
    private Line line;

    public LineBeam() {
        initLine();
    }

    public LineBeam(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        initLine();
    }

    private void initLine() {
        line = new Line(startX, startY, endX, endY);
        line.setStrokeWidth(8);
        line.setStroke(Color.LIMEGREEN);

        Glow glow = new Glow(1.0);
        Bloom bloom = new Bloom(0.3);
        glow.setInput(bloom);
        line.setEffect(glow);

        getChildren().add(line);

        removeFrame = new KeyFrame(Duration.millis(400));
        animation = new Timeline(
            new KeyFrame(Duration.ZERO,
                new KeyValue(line.opacityProperty(), 1.0),
                new KeyValue(line.strokeWidthProperty(), 8)
            ),
            new KeyFrame(Duration.millis(250),
                new KeyValue(line.opacityProperty(), 0.9),
                new KeyValue(line.strokeWidthProperty(), 12)
            ),
            new KeyFrame(Duration.millis(400),
                new KeyValue(line.opacityProperty(), 0.0),
                new KeyValue(line.strokeWidthProperty(), 2)
            )
        );
        animation.setCycleCount(1);
    }

    public void fire(EventHandler<ActionEvent> onFinished) {
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        if (onFinished != null) {
            animation.setOnFinished(onFinished);
        }
        animation.playFromStart();
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
    public void setStartX(double startX) { 
        this.startX = startX; 
        if (line != null) line.setStartX(startX); 
    }

    public double getStartY() { return startY; }
    public void setStartY(double startY) { 
        this.startY = startY; 
        if (line != null) line.setStartY(startY); 
    }

    public double getEndX() { return endX; }
    public void setEndX(double endX) { 
        this.endX = endX; 
        if (line != null) line.setEndX(endX); 
    }

    public double getEndY() { return endY; }
    public void setEndY(double endY) { 
        this.endY = endY; 
        if (line != null) line.setEndY(endY); 
    }
}
