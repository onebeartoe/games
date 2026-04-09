package net.onebeartoe.type.areli.attacks;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;

public abstract class Attack extends Group {
    protected KeyFrame removeFrame;
    protected Timeline animation;

    public abstract String[] getWords();
    public abstract void onWackaWacka();

    public KeyFrame getRemoveFrame() { return removeFrame; }
    public void setRemoveFrame(KeyFrame removeFrame) { this.removeFrame = removeFrame; }

    public Timeline getAnimation() { return animation; }
    public void setAnimation(Timeline animation) { this.animation = animation; }
}
