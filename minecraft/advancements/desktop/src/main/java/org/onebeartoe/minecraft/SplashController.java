
package org.onebeartoe.minecraft;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;

/**
 * The background "splash" animation is actually setup/started in the 
 * JavaFX application's main() class in the @Override start() method.
 */
public class SplashController 
{
    @FXML
    public MediaView mediaView;
    
    @FXML
    public VBox vBox;
    
    @FXML
    public Button splashButton;

    @FXML
    public void initialize()
    {
        Scene scene = splashButton.getScene();
        
        System.out.println("splashButton: " + splashButton);
        
        System.out.println("scene: " + scene);
        
        splashButton.setTranslateX(680/ 2.0);
        splashButton.setTranslateY(420/ 2.0);
        
//        splashButton.translateXProperty()
//            .bind(scene.widthProperty().subtract(splashButton.widthProperty())
//                    .divide(2));
//
//        splashButton.translateYProperty()
//                .bind(scene.heightProperty().subtract(splashButton.heightProperty())
//                        .divide(2));           
        
        
        
//        start(stage);
    }
    

    
    @FXML
    private void switchToPrimary() throws IOException 
    {
        var root = "launcher";
        
        System.out.println("switching to: " + root);

        App.setRoot(root);
    }
}
