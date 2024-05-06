
package net.onebeartoe.type.areli.targets;

//import javafx.scene.CustomNode;

import javafx.scene.Node;
import javafx.scene.Group;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HorizontalWordTarget extends Node
//public class HorizontalWordTarget extends CustomNode
{
    public String labelText ;

    public IntegerProperty x;
    public Integer xMax ;

    public Integer yMax;
    
    public IntegerProperty y;

    int labelX = 20;
    int labelY = -20;








    public HorizontalWordTarget()
//    override public function create () : Node
    {
        Rectangle background = new Rectangle(140, 90);
        background.setFill(Color.GREEN);
        background.setArcHeight(5);
        background.setArcWidth(5);
        background.setStroke(Color.ALICEBLUE);
        background.setStrokeWidth(5);
            
        
        
    var label = new Label();
    label.layoutXProperty().bind( new SimpleIntegerProperty(labelX) );
    label.layoutYProperty().bind( new SimpleIntegerProperty(labelY) );
    label.setFont( Font.font("Trebuchet MS",20) );
    label.textProperty().bind( new SimpleStringProperty(labelText) );
    label.setWrapText(true);
    label.setTextAlignment(TextAlignment.JUSTIFY);

//TODO: use teh scene.width in the binding    
    var widthProperty = new SimpleDoubleProperty(900 * 0.2);
    label.setPrefWidth(900 * 0.2);
//      width: bind scene.width * 0.2    
    
    label.setPrefHeight(200);

    
    
    
    Duration duration = Duration.seconds(5);
    KeyValue keyValue = new KeyValue(x, xMax);
    KeyFrame keyFrame = new 
                        KeyFrame(duration, keyValue);
            {
                
//                values:
//                [
//                    x => xMax
//                ]
            }
    
    var animation = new Timeline(keyFrame);
    animation.setAutoReverse(true);
    animation.setCycleCount(Timeline.INDEFINITE);
   
    
    
    
        
        
        animation.play();

        var group = new Group();
        
        group.layoutXProperty().bind( x);
        
        group.layoutYProperty().bind( new SimpleObjectProperty(y));

        group.getChildren().addAll(background, label);
//TODO: is the 'bind' needed for the layoutXY?        
//        {
//            layoutX: bind x;
//
//            layoutY: bind y;
//
//            content:
//            [
//                background,
//                label
//            ]
//        }
        
        getChildren()
                .add(group);
    }

}
