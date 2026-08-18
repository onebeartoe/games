package net.onebeartoe.type.areli.dialogs;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ListViewGameSummaryDialog extends GameSummaryDialog {

    public ListViewGameSummaryDialog() {
        this.width = 440;
        this.height = 400;

        Rectangle background = new Rectangle(width, height);
        background.setFill(Color.web("#1e293b"));
        background.setStroke(Color.web("#10b981"));
        background.setStrokeWidth(3);
        background.setArcWidth(15);
        background.setArcHeight(15);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Trebuchet MS", 22));
        titleLabel.setTextFill(Color.web("#10b981"));

        listView.setPrefWidth(width * 0.85);
        listView.setPrefHeight(height * 0.55);

        dismissButton.setPrefWidth(120);

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPrefSize(width, height);
        contentBox.getChildren().addAll(titleLabel, listView, dismissButton);

        getChildren().addAll(background, contentBox);
    }
}
