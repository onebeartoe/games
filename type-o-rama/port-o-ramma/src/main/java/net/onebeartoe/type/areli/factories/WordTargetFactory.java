package net.onebeartoe.type.areli.factories;

import net.onebeartoe.type.areli.targets.WordTarget;

public abstract class WordTargetFactory {
    protected double xRange = 900;
    protected double targetMaxX = 720;
    protected double targetMaxY = 280;
    protected double targetMinY = 50;
    protected double targetMinX = 45;

    public abstract WordTarget[] createTargets(String[] words);

    public void initializeTargets(WordTarget[] targets) {
        // Default implementation
    }

    // Getters and Setters
    public double getXRange() { return xRange; }
    public void setXRange(double xRange) { this.xRange = xRange; }

    public double getTargetMaxX() { return targetMaxX; }
    public void setTargetMaxX(double targetMaxX) { this.targetMaxX = targetMaxX; }

    public double getTargetMaxY() { return targetMaxY; }
    public void setTargetMaxY(double targetMaxY) { this.targetMaxY = targetMaxY; }

    public double getTargetMinY() { return targetMinY; }
    public void setTargetMinY(double targetMinY) { this.targetMinY = targetMinY; }

    public double getTargetMinX() { return targetMinX; }
    public void setTargetMinX(double targetMinX) { this.targetMinX = targetMinX; }
}
