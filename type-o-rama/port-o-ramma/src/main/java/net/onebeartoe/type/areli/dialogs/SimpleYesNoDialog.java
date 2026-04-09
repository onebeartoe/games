package net.onebeartoe.type.areli.dialogs;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SimpleYesNoDialog extends YesNoDialog {
    
    public SimpleYesNoDialog() {
        this.width = 440;
        this.height = 190;
        this.yesButtonX = (int)(width * 0.2);
        this.yesButtonY = (int)(height * 0.8);
        this.noButtonX = (int)(width * 0.8);
        this.noButtonY = yesButtonY;

        Rectangle background = new Rectangle(width, height);
        background.setFill(Color.GREEN);

        Text titleText = new Text();
        titleText.setFont(Font.font(24));
        titleText.setX(10);
        titleText.setY(60);
        titleText.setText(title);

        Text messageText = new Text();
        messageText.setFont(Font.font(24));
        messageText.setX(messageX);
        messageText.setY(height / 2.0);
        messageText.setText(message);

        Text yesText = new Text("Yes");
        yesText.setFill(Color.LIGHTBLUE);
        yesText.setFont(Font.font(24));
        yesText.setTranslateX(yesButtonX);
        yesText.setTranslateY(yesButtonY);
        this.yesButton = yesText;

        Text noText = new Text("No");
        noText.setFill(Color.LIGHTBLUE);
        noText.setFont(Font.font(24));
        noText.setTranslateX(noButtonX);
        noText.setTranslateY(noButtonY);
        this.noButton = noText;

        getChildren().addAll(background, titleText, messageText, yesButton, noButton);

        // Simple click handlers for JavaFX
        yesButton.setOnMouseClicked(e -> yesAction());
        noButton.setOnMouseClicked(e -> onWackaWacka());
    }

    @Override
    public void yesAction() {
        System.out.println("Yes clicked");
    }

    @Override
    public String[] getWords() {
        return new String[] {"HAPPY", "SMILE", "ANT"};
    }

    @Override
    public void onWackaWacka() {
        System.out.println("No clicked / WackaWacka");
    }
}
