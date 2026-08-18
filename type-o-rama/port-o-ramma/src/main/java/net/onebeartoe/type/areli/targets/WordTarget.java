package net.onebeartoe.type.areli.targets;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public abstract class WordTarget extends StackPane {
    protected double labelX = 20;
    protected double labelY = -20;
    protected Label label;
    protected KeyFrame removeFrame;
    protected Timeline animation;
    protected double xMax;
    protected double yMax;
    protected String labelText = "";
    protected Node background;

    public WordTarget() {
        setAlignment(Pos.CENTER);
    }

    public abstract String[] getWordssssss();
    public abstract void onWackaWacka();

    public void stopAnimation() {
        if (animation != null) {
            animation.stop();
        }
    }

    // Getters and Setters
    public double getLabelX() { return labelX; }
    public void setLabelX(double labelX) { this.labelX = labelX; }

    public double getLabelY() { return labelY; }
    public void setLabelY(double labelY) { this.labelY = labelY; }

    public Label getLabel() { return label; }
    public void setLabel(Label label) { this.label = label; }

    public Timeline getAnimation() { return animation; }
    public void setAnimation(Timeline animation) { this.animation = animation; }

    public double getXMax() { return xMax; }
    public void setXMax(double xMax) { this.xMax = xMax; }

    public double getYMax() { return yMax; }
    public void setYMax(double yMax) { this.yMax = yMax; }

    public String getLabelText() { return labelText; }
    public void setLabelText(String labelText) {
        this.labelText = labelText != null ? labelText : "";
        if (label != null) {
            label.setText(this.labelText);
        }
    }

    public Node getBackgroundNode() { return background; }
    public void setBackgroundNode(Node background) { this.background = background; }
}
