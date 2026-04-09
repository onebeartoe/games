package net.onebeartoe.type.areli.dialogs;

import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Node;

public abstract class YesNoDialog extends Group {
    protected int width;
    protected int height;
    protected int yesButtonX;
    protected int yesButtonY;
    protected int noButtonX;
    protected int noButtonY;
    protected KeyFrame removeFrame;
    protected Node yesButton;
    protected Node noButton;
    protected String title = "Congrats!";
    protected String message = "Some yes or no question?";
    protected int messageX = 30;

    public abstract String[] getWords();
    public abstract void onWackaWacka();
    public abstract void yesAction();

    // Getters and Setters
    public int getWidthInt() { return width; } // Avoid conflict with Group.getWidth()
    public void setWidthInt(int width) { this.width = width; }

    public int getHeightInt() { return height; }
    public void setHeightInt(int height) { this.height = height; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
