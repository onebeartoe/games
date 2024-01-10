
package org.onebeartoe.desktop;

import static java.lang.ProcessBuilder.Redirect.from;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

/**
 *
 */
public class AdvancementsControllerTest extends ApplicationTest
{
    private AdvancementsController implementation = new AdvancementsController();
    
    @Test
    public void someTest()
    {
        System.out.println("this is a test - 12345431 - kjljl;kjlkjl;j");
    }

//  @Override
//  public void start(Stage stage) throws Exception {
//    stage.setScene(helloWorld.getScene());
//    stage.show();
//    stage.toFront();
//  }
//  @Test
//  public void hasHelloWorldButton() {
//    StackPane rootNode = helloWorld.getScene().getRoot();
//    Button button = from(rootNode).lookup(".button").query();
//    assertEquals("Hello World", button.getText());
//  }
}

//public class HelloWorldTest  {
//  private final HelloWorld helloWorld = new HelloWorld();
//
//}