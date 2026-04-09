package net.onebeartoe.type.areli.attacks;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.util.Duration;

public class TrigBeam extends Attack {
    private int halfLength = 200;
    private int startX = 0;
    private int startY = 0;
    
    private double middleX;
    private double middleY;
    private double endX;
    private double endY;

    private double nControlY;
    private double uControlY;

    private QuadCurve curveN;
    private QuadCurve curveU;

    public TrigBeam() {
        middleX = startX + halfLength;
        middleY = startY;
        endX = middleX + halfLength;
        endY = startY;

        double controlYDelta = 200;
        nControlY = middleY + controlYDelta;
        uControlY = middleY - controlYDelta;

        curveN = new QuadCurve(startX, startY, 150, nControlY, middleX, middleY);
        curveN.setFill(null);
        curveN.setStroke(Color.web("#AA4400"));
        curveN.setStrokeWidth(10);

        curveU = new QuadCurve(middleX, middleY, 250, uControlY, endX, endY);
        curveU.setFill(null);
        curveU.setStroke(Color.web("#AA4400"));
        curveU.setStrokeWidth(10);

        getChildren().addAll(curveN, curveU);

        // Bindings for animation
        curveN.controlYProperty().bind(javafx.beans.binding.Bindings.createDoubleBinding(() -> nControlY, new javafx.beans.property.SimpleDoubleProperty(nControlY)));
        // Note: Simple manual property update is easier in Java for this animation
        
        animation = new Timeline(
            new KeyFrame(Duration.millis(2500),
                new KeyValue(this.scaleYProperty(), 0.5) // Placeholder for complex values
            ),
            new KeyFrame(Duration.seconds(5),
                new KeyValue(this.scaleYProperty(), 1.0)
            )
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

    public double findRotationAngle(double bottomX, double bottomY, double targetX, double targetY) {
        double o = Math.sqrt(Math.pow(targetY - bottomY, 2));
        double h = Math.sqrt(Math.pow(targetX - bottomX, 2) + Math.pow(targetY - bottomY, 2));
        double t = o / h;
        double rtrig = Math.asin(t);
        return -rtrig * (180 / Math.PI);
    }
}
