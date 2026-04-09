package net.onebeartoe.type.areli.factories;

import net.onebeartoe.type.areli.targets.WordTarget;

public abstract class WordTargetFactory {
    protected double xRange;
    protected double targetMaxX;
    protected double targetMaxY;
    protected double targetMinY;

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
}
