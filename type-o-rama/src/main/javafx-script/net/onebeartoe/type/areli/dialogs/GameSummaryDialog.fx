
package net.onebeartoe.type.areli.dialogs;

import javafx.scene.CustomNode;
import javafx.scene.control.ListView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.scene.control.Button;

/**
 * @author lando
 */
public abstract class GameSummaryDialog extends CustomNode
{
    public var width: Integer = 400;

    public var height: Integer;

    public var title: String = "Game Summary";

    public var message: String = "Some yes or no question?";

    public var messageX: Integer = 30;

    def screenWidth = Screen.primary.visualBounds.width;

    def screenHeight = Screen.primary.visualBounds.height;

    var rect: Rectangle[];

    var sceneWidth = screenWidth - (screenWidth * 0.3);

    var sceneHeight = screenHeight - (screenHeight * 0.5);

    var listViewWidth = width * 0.8;

    var listViewHeight = height * 0.5;

    var listViewY = sceneHeight - (sceneHeight * 0.75);

    var listViewX = width * 0.1;

    public var buttonText = "Next Round";

    public var listView : ListView = ListView
    {
        width: listViewWidth
        height: listViewHeight

        layoutX: listViewX
        layoutY: listViewY
    }

    public var dismissButton: Button = Button
    {
        text: bind buttonText;
    }

}
