package net.onebeartoe.type.areli;

import net.onebeartoe.type.areli.pojos.Round;
import net.onebeartoe.type.areli.services.WordsService;
import net.onebeartoe.type.areli.services.implementation.SimpleWordService;
import net.onebeartoe.type.areli.services.implementation.TestingWordService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServicesAndModelTest {

    @Test
    public void testRoundPojo() {
        Round round = new Round(10, 2);
        assertEquals(10, round.getWords());
        assertEquals(2, round.getMisses());
        assertEquals(80.0, round.getHitRatio(), 0.001);
        assertTrue(round.getSummaryText().contains("Words: 10"));
        assertTrue(round.getSummaryText().contains("Misses: 2"));
        assertTrue(round.getSummaryText().contains("80.0%"));
    }

    @Test
    public void testRoundZeroWords() {
        Round round = new Round(0, 0);
        assertEquals(0.0, round.getHitRatio(), 0.001);
    }

    @Test
    public void testSimpleWordService() {
        WordsService service = new SimpleWordService();
        String[] words = service.getWords(5);
        assertNotNull(words);
        assertEquals(5, words.length);
        for (String word : words) {
            assertNotNull(word);
            assertFalse(word.isEmpty());
            assertEquals(word.toUpperCase(), word);
        }
    }

    @Test
    public void testTestingWordService() {
        WordsService service = new TestingWordService();
        String[] words = service.getWords(3);
        assertNotNull(words);
        assertEquals(3, words.length);
        assertEquals("ANT", words[0]);
        assertEquals("CAR", words[1]);
        assertEquals("BAT", words[2]);
    }
}
