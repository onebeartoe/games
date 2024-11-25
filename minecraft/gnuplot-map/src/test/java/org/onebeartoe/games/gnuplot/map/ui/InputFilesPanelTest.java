
package org.onebeartoe.games.gnuplot.map.ui;

import java.io.File;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

//import org.junit.jupiter.api.Test;
import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;


import org.onebeartoe.games.gnuplot.map.App;
import org.testfx.api.FxRobotInterface;

import org.mockito.Mockito;
import org.mockito.ArgumentMatchers;

/**
 *
 */
public class InputFilesPanelTest extends ApplicationTest
{
    protected Stage stage;
    protected Scene scene;
    
    protected TabPane tabs;
    
    protected Tab netherTab;
    
    protected Tab adventureTab;

    protected Tab husbandryTab;    
    
    public InputFilesPanelTest() 
    {
        
    }

    @FXML
    @Override
    public void start(Stage stage) throws Exception 
    {
        this.stage = stage;
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary" + ".fxml"));        

        Object root = fxmlLoader.load();        

        Parent parent = (Parent) root;
                 
        scene = new Scene(parent, 640, 480);

//        tabs = (TabPane) scene.getRoot();
//        
//        netherTab = tabs.getTabs().get(0);

        
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test( )
    public void adventure_monstersHunted_font()
    {
        File someFile = new File("~/myfile.txt");

        Platform.runLater(() -> {

DirectoryChooser directoryChooser = Mockito.mock(DirectoryChooser.class);
Mockito.when( 
    directoryChooser.showDialog(
//            stage
                            ArgumentMatchers.any(Stage.class)
                                        ))
        .thenReturn(someFile);

        });
        


//FileChooser fileChooser = Mockito.mock(FileChooser.class);
//Mockito.when(
//    fileChooser.showDialog(
//                ArgumentMatchers.any(Window.class)
//                
//            )
//            .thenReturn(someFile));
        
        
        
        var s = "Input Directory";
        
        FxRobotInterface clickOn = clickOn(s);
        
        
        System.out.println("0 - ploopo");
//        BorderPane borderPane = (BorderPane) scene.getRoot();
//
//        TabPane rootNode = (TabPane) borderPane.getCenter();
//        
//        Tab adventureTab = rootNode.getTabs().get(1);
//        
//        String title = adventureTab.getText();
//        
//        assertEquals("Adventure", title);
//        
//        SplitPane splitPane = (SplitPane) adventureTab.getContent();
//
//        ScrollPane scrollPane = (ScrollPane) splitPane.getItems().get(1);
//        
//        SplitPane textAreaSplitPane = (SplitPane) scrollPane.getContent();
//        
//        TextArea haveNotsTextArea = (TextArea) textAreaSplitPane.getItems().get(1);
//        
//        Font font = haveNotsTextArea.getFont();
//        
//        var expected = "minecraft";
//        
//        var actual = font.getName();
//        
//        assertEquals(expected, actual);
    }      


    @Test
    public void testTheCLearButton()
    {
        // this shows how to mock the file chooser
        //      https://github.com/TestFX/TestFX/issues/497
        
//        assertTrue(3 == 7);

    }
}
