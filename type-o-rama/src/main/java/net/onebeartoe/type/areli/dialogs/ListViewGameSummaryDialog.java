
package net.onebeartoe.type.areli.dialogs;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ListViewGameSummaryDialog extends GameSummaryDialog
{

   public ListViewGameSummaryDialog()
//   override public function create () : Node
   {
        width = 440;

        height = 400;
       
       
        dismissButton.setTranslateX( width * 0.48);
        dismissButton.setTranslateY(height * 0.8);

        
        var rectangle = new Rectangle(width, height);
        rectangle.setFill(Color.GREEN);
                
        var text = new Text();
        var font = new Font(24);
        text.setFont(font);
        text.setTranslateX(10);
        text.setTranslateY(height * 0.1);
        text.textProperty().bind( new SimpleStringProperty(title));
                
                
                
            
                
               


                
                 
        
        getChildren().addAll(rectangle,
                text,
                listView
                ,
                dismissButton
        );

//        Group
//        {
//            content:
//            [
//                Rectangle
//                {
//
//                    width: width,
//                    height: height
//                    fill: Color.GREEN
//                }
//                ,
//                Text
//                {
//                   font : Font
//                   {
//                      size: 24
//                   }
//                   x: 10
//                   y: height * 0.1
//                   content: bind title
//                }
/*
                ,
                Text
                {
                    font : Font
                   {
                    size: 24
                   }
                   x: bind messageX,
                   y: height / 2
                   content: bind message
                }
                ,
*/
//                listView
//                ,
//                dismissButton
//            ]
//        }
    }
}
