
package net.onebeartoe.type.areli.targets;

import javafx.scene.Node;
import javafx.scene.Group;
import java.lang.UnsupportedOperationException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class StaticWordTarget extends WordTarget
{
    public Integer halfLength = 200;

    public Integer startX = 0;//50;

    public Integer startY = 0;//250;    

    public Integer endX = 50;
//    public var endY = 50;

    public StaticWordTarget()
    {
        int width = 140;
        
        int height = 90;
        
        background = new Rectangle(width, height);
        
        background.setFill(Color.GREEN);
                
        background.setArcWidth(5);

        background.setArcHeight(5);
        
    
        
        label = new Label();
        
        label.layoutXProperty().bind(labelX);

        label.layoutYProperty().bind(labelY);

        var font = Font.font("Trebuchet MS",20);

        label.setFont(font);
        
        label.textProperty().bind(labelText);
                
        label.setWrapText(true);
                
        label.setTextAlignment(TextAlignment.JUSTIFY);
        
//TODO: bind this to the Scene's width        
        DoubleProperty widthProperty = new SimpleDoubleProperty(600 * 0.2);
        label.widthProperty().bind(widthProperty);
//TODO: bind this to the Scene's width
//        width: bind scene.width * 0.2
        
        label.setHeight(200);
        
        
        
        



        animation = new Timeline();
        
        animation.setAutoReverse(true);
    
        animation.setCycleCount(Timeline.INDEFINITE);
// I guess repeatCount became cycleCount                
//        repeatCount: Timeline.INDEFINITE



KeyFrame keyFrame1 = new KeyFrame(Duration.millis(2500) );

KeyFrame keyFrame2 = new KeyFrame( Duration.seconds(5) );

KeyFrame keyFrame3 = new KeyFrame(Duration.millis(850) );

        animation.getKeyFrames()
                    .addAll(keyFrame1, 
                            keyFrame2,
                            keyFrame3);
    

//        keyFrames:
//        [
//            KeyFrame
//            {
//                time: 2500ms,
//                values:
//                [
//                   
//                ]
//            }
//            ,
//            KeyFrame
//            {
//                time: 5s,
//                values: 
//                [
//                    
//                ]
//            },
//            removeFrame = KeyFrame
//            {
//                time: 850ms,
//                values:
//                [
//                    
//                ]
//
//            }
//        ]


getChildren().
        addAll( create() );

    }
        
    
//TODO: is this really overriden like in the .fx code?????
//    @Override 
    public Node create()
    {
        animation.play();

        Group group = new Group();
        

// Origianal .fx code        
//        Group
//        {

//!!!!!!!!TODO: what about these bind() calls?  where is the layoutX and layoutY on the right side?????
//            layoutX: bind layoutX;
//
//            layoutY: bind layoutY;



        group.getChildren()
                .addAll(background,
                        label);
//
//            content:
//            [
//                background,
//                label
//            ]
//        }

        return group;
    }

    
    
    
    
    
    

//TODO: get rid of this silly method once the app runs
    @Override
    public String []  getWordssssss()
    {
        String [] words =
        {
            "HAPPY", "SMILE", "ANT"
        };

        return words;
    }

//TODO: get rid of this silly method once the app runs    
    @Override 
    public void onWackaWacka () 
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
