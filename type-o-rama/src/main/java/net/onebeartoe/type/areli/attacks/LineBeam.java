
package net.onebeartoe.type.areli.attacks;

import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import java.lang.UnsupportedOperationException;
import javafx.animation.KeyFrame;
import javafx.scene.shape.Line;
import javafx.scene.effect.Glow;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class LineBeam extends Attack
{
    public int halfLength = 200;

    public int startX = 0;//50;
    public int startY = 0;//250;    

    public int endX = 50;
    public int endY = 50;
 
    public LineBeam()
    {
    var line = new Line();
    {
//	startX: bind startX
//        startY: bind startY
//
//	endX: bind endX
//        endY: bind endY
    
    line.setStrokeWidth(10);
    line.setStroke(Color.LIMEGREEN);
    
    Glow glow = new Glow(1);
    line.setEffect(glow);
    
    

    
    
    var keyFrame1 = new         KeyFrame(Duration.millis(2500) );
          
    var keyFrame2 = new             KeyFrame(Duration.seconds(5) );
    
    
    var    keyFrame3 = new KeyFrame( Duration.milis(850) );
        
    
animation = new Timeline(keyFrame1, keyFrame2, keyFrame3);
    
animation.setAutoReverse(true);
        animation.setCycleCount(Timeline.INDEFINITE);


        
        

        
    

        
        
        getChildren().
            addAll( create() );
    }
    


    

    @Override 
    public Node create ()
//    override public function create () : Node
    {
        animation.play();

        var group = new Group();        
        
        group.getChildren().add(line);
        
        return group;
    }

//TODO: get rid of this silly method once the app runs    
    @Override
    public String [] getWords()
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

