
package net.onebeartoe.type.areli.dialogs;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StartGameDialog extends Group
{
    public Button playButton = new Button("Play!");
    
   public StartGameDialog()
   {
        int width = 440;

        int height = 400;
       
       
        playButton.setTranslateX( width * 0.48);
        playButton.setTranslateY(height * 0.8);

        
        var rectangle = new Rectangle(width, height);
        rectangle.setFill(Color.GREEN);
                
        var text = new Text();
        var font = new Font(24);
        text.setFont(font);
        text.setTranslateX(10);
        text.setTranslateY(height * 0.1);
        
        String title = "Ready to Play?";
        
        text.textProperty().bind( new SimpleStringProperty(title));
        
        getChildren().addAll(rectangle,
                text,
                playButton
        );
    }
}
