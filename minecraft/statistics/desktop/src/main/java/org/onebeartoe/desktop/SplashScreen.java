package org.onebeartoe.desktop;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * I tried to use a splash screen preloader but it keep resizing the
 * main application window (not sure why).
 */
public class SplashScreen extends Preloader 
{
    // Create the splash screen layout
    private final StackPane parent = new StackPane();

    private Stage preloaderStage;

    @Override
    public void init() throws Exception 
    {
        var imagePath = "file:///home/roberto/Versioning/owner/beto-land-owner/Imaging/felix-the-cat/felix.gif";
        imagePath = "file:///home/roberto/Versioning/owner/github/games/minecraft/statistics/desktop/src/main/resources/org/onebeartoe/desktop/dirt.jpg";

        // Load the image to be displayed on the splash screen

        Image image = new Image(imagePath);

        
        // Create an ImageView to display the image
        ImageView imageView = new ImageView(image);

        // Preserve the image's aspect ratio
        imageView.setPreserveRatio(true);

        // Set the width of the ImageView
        imageView.setFitWidth(640);
        
        imageView.setFitHeight(480);

        // Add the ImageView to the parent StackPane
        this.parent.getChildren().add(imageView);                
    }

    @Override
    public void start(Stage stage) throws Exception 
    {
        this.preloaderStage = stage;
        
        stage.setWidth(640);
        stage.setHeight(480);
        

        // Create a scene with the StackPane as the root
        Scene scene = new Scene(parent, 640, 480);

        // Make the scene background transparent
        scene.setFill(Color.TRANSPARENT);

        // Set the scene for the stage
        stage.setScene(scene);

        // Remove window decorations
        stage.initStyle(StageStyle.TRANSPARENT);

        // Center the SplashScreen on the screen
        stage.centerOnScreen();

        // Display the SplashScreen
        stage.show();
                
//        ScheduledExecutorService executor =  Executors.newSingleThreadScheduledExecutor();
        
        // assuming the stage-setup is perfomed already:
//        executor.submit(() -> Platform.runLater(stage::show));

//        Callable callable = () ->
//        {
//            try 
//            {
//                System.out.println("jdflkajldfjalfjal");
//                
//                Thread.sleep(Duration.ofSeconds(3));
//            } 
//            catch (InterruptedException ex) 
//            {
//                ex.printStackTrace();
//            }
//
//            return null;
//        };
//
//        executor.schedule(
//            callable
//            , 5
//            , TimeUnit.SECONDS
//        );
//        
//        
//        // assuming the stage-setup is perfomed already:
//        executor.submit(() -> Platform.runLater(stage::show));       
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) 
    {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {

            // Close the splash screen when the application is ready to start
            this.preloaderStage.close();
        }
    }
}