
package org.onebeartoe.games.gnuplot.map.ui;

import java.io.File;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;


import org.onebeartoe.games.gnuplot.map.App;
import org.testfx.api.FxRobotInterface;

import org.mockito.Mockito;
import org.mockito.ArgumentMatchers;
import org.onebeartoe.games.gnuplot.map.PrimaryController;

/**
 *
 */
public class InputFilesPanelTest extends ApplicationTest
{
    protected Stage stage;
    
    protected Scene scene;
    
    BorderPane rootBorderPane;
    
    ListView inputFilesListView;
        
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

        PrimaryController controller = (PrimaryController) fxmlLoader.getController();
                
        File resourcesDir = new File("src/test/resources");

        Platform.runLater(() -> 
        {
            var directoryChooser = Mockito.mock(DirectoryChooser.class);
            Mockito.when( directoryChooser.showDialog(
                            ArgumentMatchers.any(Stage.class)))
                    .thenReturn(resourcesDir);
        
            File inputDirectory = directoryChooser.showDialog(stage);
            System.out.println("chooser sure: " + inputDirectory.getAbsoluteFile() );
        
            controller.setDirectoryC(directoryChooser, resourcesDir);
        });
        
        Parent parent = (Parent) root;
                 
        scene = new Scene(parent, 640, 480);

//scene.getco        
        
//        tabs = (TabPane) scene.getRoot();
//        
//        netherTab = tabs.getTabs().get(0);

        rootBorderPane = (BorderPane) scene.getRoot();

        
        var listViewId = "#inputFilesListView";
        
        inputFilesListView = from(rootBorderPane)
                                        .lookup(listViewId)                
                                        .query();        
                
//rootBorderPane.getv        
        
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test( )
    public void directotryChooser()
    {        
//        var buttonLabel = "Input Directory";
//        
//        FxRobotInterface clickOn = clickOn(buttonLabel);
        
        
        System.out.println("0 - ploopo");        
        
        inputFilesListView.getItems().forEach(System.out::println);
        
        var actualSize = inputFilesListView.getItems().size();        
        var expectedSize = 1;
        assertThat(actualSize).isEqualTo(expectedSize);
        
        var actualPath = inputFilesListView.getItems().get(0);
        var expectedPath = "src/test/resources/three-map-markers.data";        
        assertThat(actualPath).isEqualTo(expectedPath);
    }      


    @Test()
    public void clearButton()
    {
        // this shows how to mock the file chooser
        //      https://github.com/TestFX/TestFX/issues/497
        
        directotryChooser();

        // find and click the Clear button
        var buttonId = "#clearInputFilesListButton";
        Button clearInputFilesListButton = from(rootBorderPane)
                                .lookup(buttonId)                
                                .query();        
        
        clickOn(clearInputFilesListButton);      
        
        // assert the input files ListView is cleared/empty
        var actualSize = inputFilesListView.getItems().size();        
        var expectedSize = 0;        
        assertThat(actualSize).isEqualTo(expectedSize);
    }
}
