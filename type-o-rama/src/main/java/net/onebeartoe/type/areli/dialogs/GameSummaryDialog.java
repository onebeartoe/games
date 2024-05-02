
package net.onebeartoe.type.areli.dialogs;

// The next (temporarily) commented import was from JavaFX Script
//TODO: delete once the application runs properly
//import javafx.scene.CustomNode;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.scene.control.ListView;

/**
 */
public abstract class GameSummaryDialog extends Group
{
    public Integer width = 400;

    public Integer height;

    public String title = "Game Summary";

    public String message = "Some yes or no question?";

    public Integer messageX = 30;

    double screenWidth;
    
    double screenHeight;
    
    double sceneWidth;

    double sceneHeight;
    
    double listViewWidth;
    
    double listViewHeight;
    
    double listViewY;
    
    double listViewX;
    
    public GameSummaryDialog()
    {
        screenWidth = Screen.getPrimary().getVisualBounds().getWidth();

        screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

        sceneWidth = screenWidth - (screenWidth * 0.3);

        sceneHeight = screenHeight - (screenHeight * 0.5);

        listViewWidth = width * 0.8;
        
        listViewHeight = height * 0.5;
        
        listViewY = sceneHeight - (sceneHeight * 0.75);
        
        listViewX = width * 0.1;
        
        listView = new ListView();
        listView.setPrefWidth(listViewWidth);
        listView.setPrefHeight(listViewHeight);
        listView.setLayoutX(listViewX);
        listView.setLayoutY(listViewY);
    
        dismissButton.textProperty()
                        .bind(buttonText);
    }

    Rectangle[] rect;

    public StringProperty buttonText = new SimpleStringProperty("Next Round");
    
    public ListView listView;

    public Button dismissButton = new Button();
}
