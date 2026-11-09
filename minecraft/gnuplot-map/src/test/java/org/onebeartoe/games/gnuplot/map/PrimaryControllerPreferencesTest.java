package org.onebeartoe.games.gnuplot.map;

import java.io.File;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.onebeartoe.games.gnuplot.map.App.INPUT_FILES_KEY;

public class PrimaryControllerPreferencesTest extends ApplicationTest
{
    private PrimaryController controller;
    
    private final File testFile1 = new File("src/test/resources/one-map-marker.data");
    private final File testFile2 = new File("src/test/resources/three-map-markers.data");

    @Override
    public void start(Stage stage) throws Exception
    {
        App.preferences.remove(INPUT_FILES_KEY);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    @Before
    @After
    public void cleanUpPreferences()
    {
        App.preferences.remove(INPUT_FILES_KEY);
    }

    
/**
 * TODO: NOT SURE IF I LIKE THIS ONE....
 */    
    @Test
    public void inputFilesKeyConstant()
    {
        assertThat(App.INPUT_FILES_KEY).isNotNull();
        assertThat(App.INPUT_FILES_KEY).isEqualTo("org.onebeartoe.minecraft.gnuplot.preferences.INPUT_FILES");
    }

    @Test
    public void addFile_savesPreferenceWhenEmpty()
    {
        interact(() -> controller.addFile(testFile1));

        String saved = App.preferences.get(INPUT_FILES_KEY, "");
        assertThat(saved).isEqualTo(testFile1.getPath());
    }

    @Test
    public void addFile_concatenatesWhenPreferencesExist()
    {
        interact(() -> {
            controller.addFile(testFile1);
            controller.addFile(testFile2);
        });

        String saved = App.preferences.get(INPUT_FILES_KEY, "");
        String expected = testFile1.getPath() + "," + testFile2.getPath();
        assertThat(saved).isEqualTo(expected);
    }

    @Test
    public void addFile_doesNotDuplicateExistingPreference()
    {
        interact(() -> {
            controller.addFile(testFile1);
            controller.addFile(testFile1);
        });

        String saved = App.preferences.get(INPUT_FILES_KEY, "");
        assertThat(saved).isEqualTo(testFile1.getPath());
    }

    @Test
    public void initialize_restoresSavedInputFiles()
    {
        String savedPaths = testFile1.getPath() + "," + testFile2.getPath();
        App.preferences.put(INPUT_FILES_KEY, savedPaths);

        interact(() -> {
            controller.inputFilesListView.getItems().clear();
            controller.initialize();
        });

        assertThat(controller.inputFilesListView.getItems())
                .containsExactly(testFile1.getPath(), testFile2.getPath());
    }

    @Test
    public void initialize_noActionWhenNoPreferences()
    {
        App.preferences.remove(INPUT_FILES_KEY);

        interact(() -> {
            controller.inputFilesListView.getItems().clear();
            controller.initialize();
        });

        assertThat(controller.inputFilesListView.getItems()).isEmpty();
    }
}
