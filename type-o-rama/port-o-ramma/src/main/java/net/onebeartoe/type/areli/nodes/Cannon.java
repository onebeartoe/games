package net.onebeartoe.type.areli.nodes;

import javafx.animation.Timeline;
import javafx.scene.Group;

public abstract class Cannon extends Group {
    protected int cannonTipX = 20;
    protected int cannonTipY = 30;
    protected Timeline animation;
    protected double xMax;
    protected double yMax;

    public abstract void onWackaWacka();

    public int getCannonTipX() { return cannonTipX; }
    public void setCannonTipX(int cannonTipX) { this.cannonTipX = cannonTipX; }

    public int getCannonTipY() { return cannonTipY; }
    public void setCannonTipY(int cannonTipY) { this.cannonTipY = cannonTipY; }

    public Timeline getAnimation() { return animation; }
    public void setAnimation(Timeline animation) { this.animation = animation; }

    public double getXMax() { return xMax; }
    public void setXMax(double xMax) { this.xMax = xMax; }

    public double getYMax() { return yMax; }
    public void setYMax(double yMax) { this.yMax = yMax; }
}
