package net.onebeartoe.type.areli;

import javafx.application.Platform;
import net.onebeartoe.type.areli.factories.WordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.DiagnalWordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.StaticWordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.VerticalWordTargetFactory;
import net.onebeartoe.type.areli.targets.WordTarget;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TargetFactoriesTest {

    @BeforeAll
    public static void initJavaFX() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // Already started
        }
    }

    @Test
    public void testStaticWordTargetFactory() {
        WordTargetFactory factory = new StaticWordTargetFactory();
        String[] words = {"CAT", "DOG"};
        WordTarget[] targets = factory.createTargets(words);

        assertEquals(2, targets.length);
        assertEquals("CAT", targets[0].getLabelText());
        assertEquals("DOG", targets[1].getLabelText());
    }

    @Test
    public void testVerticalWordTargetFactory() {
        WordTargetFactory factory = new VerticalWordTargetFactory();
        String[] words = {"JAVA", "KOTLIN"};
        WordTarget[] targets = factory.createTargets(words);

        assertEquals(2, targets.length);
        assertEquals("JAVA", targets[0].getLabelText());
        assertEquals("KOTLIN", targets[1].getLabelText());
    }

    @Test
    public void testDiagnalWordTargetFactory() {
        WordTargetFactory factory = new DiagnalWordTargetFactory();
        String[] words = {"SPEED", "POWER"};
        WordTarget[] targets = factory.createTargets(words);

        assertEquals(2, targets.length);
        assertEquals("SPEED", targets[0].getLabelText());
        assertEquals("POWER", targets[1].getLabelText());
    }
}
