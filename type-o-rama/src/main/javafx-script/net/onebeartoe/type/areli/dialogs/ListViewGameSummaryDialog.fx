
package net.onebeartoe.type.areli.dialogs;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ListViewGameSummaryDialog extends GameSummaryDialog
{
   override public var width = 440;

   override public var height = 400;

   override public function create () : Node
   {
        dismissButton.translateX = width * 0.48;
        dismissButton.translateY = height * 0.8;

        Group
        {
            content:
            [
                Rectangle
                {

                    width: width,
                    height: height
                    fill: Color.GREEN
                }
                ,
                Text
                {
                   font : Font
                   {
                      size: 24
                   }
                   x: 10
                   y: height * 0.1
                   content: bind title
                }
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
                listView
                ,
                dismissButton
            ]
        }
    }
}
