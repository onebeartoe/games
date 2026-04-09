
package net.onebeartoe.type.areli.nodes;

import java.io.IOException;
import java.io.InputStream;
import javafx.scene.Node;
import javafx.scene.Group;
import java.lang.UnsupportedOperationException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.lang.Integer;
import java.net.URL;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class RobotChicken extends Cannon
{

    
    public Number halfLength  = 200;

    public int startX = 0;
    public int startY = 0;

    public int endX = 50;

    public ImageView image;





    public RobotChicken() throws IOException
//    override public function create () : Node
    {
        cannonTipX = 20;

        cannonTipY = 30;
        
        

//TODO: Who is the layoutX property bount the these layoutXY values????????        
//            layoutX: bind layoutX;
//
//            layoutY: bind layoutY;        



        var keyFrame1 = new KeyFrame(Duration.millis(2500));
        var keyFrame2 = new KeyFrame(Duration.seconds(5) );
        animation = new Timeline(keyFrame1, keyFrame2);
//    override public var animation = Timeline            
        animation.setAutoReverse(true);
        animation.setCycleCount(Timeline.INDEFINITE);

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
//            }
//        ]
    

        
        
        
        
        
                String imagePath = "net/onebeartoe/type/areli/nodes/robot-chicken-b.png";
                URL resource = ClassLoader.getSystemClassLoader().getResource(imagePath);
                InputStream instream = resource.openStream();
                
                image = new ImageView();
                image.setImage( new Image(instream ));
                image.setScaleX(0.7);
                image.setScaleY(0.7);
                
                
                
                
                
                
                
                
                
//                SimpleIntegerProperty xProperty = centerX: bind translateX + (image.image.width * image.scaleX + 30)
                
                int radiusX = 4;
                int radiusY = 8;
                var ellipse = new Ellipse(radiusX, radiusY);
                ellipse.setRotate(-40);
                ellipse.setFill(Color.GREEN);
                ellipse.centerXProperty().bind(
                        ellipse.translateXProperty().add(
                                (image.getImage().widthProperty().multiply( image.scaleXProperty().add( 
                                        new SimpleIntegerProperty(30)  )
                        )
                                        )
                ));  //yikes!!!!  but  I think that is correct and it looks like centerY is a little less complex
//                Ellipse
//                {
//                    centerX: bind translateX + (image.image.width * image.scaleX + 30)
//                    centerY: cannonTipY + 0.11 * image.image.height
//                }
                ellipse.centerYProperty().bind(
                        new SimpleIntegerProperty(cannonTipY)
                                .add(
                                        image.getImage().heightProperty().multiply(
                                                            new SimpleDoubleProperty(0.11)
                                        )
                                
                                )
                );
                        
                        
                        
                        
                        
                        
        getChildren()
                .addAll(image, ellipse);

    }

//TODO: remove this silly method once the application runs    
    @Override
    public void onWackaWacka() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
