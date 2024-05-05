
package net.onebeartoe.type.areli.targets;

import javafx.scene.Node;
import javafx.scene.Group;
import java.lang.UnsupportedOperationException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class DiagnalWordTarget extends WordTarget
{
    public Integer halfLength = 200;

    public Integer startX = 0;
    public Integer startY = 0;

    public Integer endX = 50;
    public Integer endY = 50;

    public DiagnalWordTarget()
    {
        int width = 140;

        int height = 90;

        background = new Rectangle(width, height);

        background.setFill(Color.LIGHTGREEN);
                
        background.setArcWidth(5);

        background.setArcHeight(5);
        
        background.setStroke(Color.GREEN);
        
        background.setStrokeWidth(5);
                
        
                
                
        initLabel();
        
        initiAnimation();
        
        
        
        getChildren().
            addAll( create() );
    }

    private void initLabel()
    {
        label = new Label();

        label.layoutXProperty().bind(labelX);

        label.layoutYProperty().bind(labelY);

        var font = Font.font("Trebuchet MS",20);
        
        label.setFont(font);
        
        label.textProperty().bind(labelText);
        
        label.setWrapText(true);

        label.setTextAlignment(TextAlignment.JUSTIFY);

        

//TODO: bind this to the Scene's width!!!!!!!!!!!!!!!!        
        DoubleProperty widthProperty = new SimpleDoubleProperty(600 * 0.2);
        label.widthProperty().bind(widthProperty);
//TODO: bind this to the Scene's width!!!!!!!!!!1
        //        width: bind scene.width * 0.2

        label.setHeight(200);
    }


    private void initiAnimation()
    {
        animation = new Timeline();
                
        animation.setAutoReverse(true);

        animation.setCycleCount(Timeline.INDEFINITE);
// I guess repeatCount became cycleCount                
//        repeatCount: Timeline.INDEFINITE


        Duration duration1 = Duration.seconds(5);
        KeyValue xKeyValue = new KeyValue(translateXProperty(), xMax);
        KeyValue yKeyValue = new KeyValue(translateYProperty(), yMax);
        KeyFrame keyFrame1 = new KeyFrame(duration1, xKeyValue, yKeyValue);
        
        
        Duration duration2 = Duration.millis(850);
        KeyFrame removeKeyFrame = new KeyFrame(duration2);
                
                
                
//        keyFrames:
//        [
//            KeyFrame
//            {
//                time: 5s,
//                values:
//                [
//                   translateX => xMax,
//                   translateY => yMax
//                ]
//            }
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

        animation.getKeyFrames()
                    .addAll(keyFrame1, 
                            removeKeyFrame);

    }

    
    
//TODO: is this really overriden like in the .fx code?????
//    @Override     
    public Node create ()
    {
        animation.play();

        var group = new Group();
        
        
        
        
//!!!!!!!!TODO: what about these bind() calls?  where is the layoutX and layoutY on the right side?????
//            layoutX: bind layoutX;
//
//            layoutY: bind layoutY;

        group.getChildren()
                .addAll(background,
                        label);
        
        return group;
    }

    
//TODO: get rid of this silly method once the app runs    
    @Override
    public String [] getWordssssss()
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

