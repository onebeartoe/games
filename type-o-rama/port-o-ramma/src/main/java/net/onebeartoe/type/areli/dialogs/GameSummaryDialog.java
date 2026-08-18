package net.onebeartoe.type.areli.dialogs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public abstract class GameSummaryDialog extends Group {
    protected int width = 440;
    protected int height = 400;
    protected String title = "Game Summary";
    protected String message = "Round Summary";
    protected int messageX = 30;

    public StringProperty buttonText = new SimpleStringProperty("Next Round");
    public ListView<String> listView = new ListView<>();
    public Button dismissButton = new Button();

    public GameSummaryDialog() {
        dismissButton.textProperty().bind(buttonText);
    }

    public int getWidthInt() { return width; }
    public void setWidthInt(int width) { this.width = width; }

    public int getHeightInt() { return height; }
    public void setHeightInt(int height) { this.height = height; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
