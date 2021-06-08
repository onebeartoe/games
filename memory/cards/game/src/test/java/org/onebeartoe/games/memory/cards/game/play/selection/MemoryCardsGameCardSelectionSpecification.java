package org.onebeartoe.games.memory.cards.game.play.selection;

import java.util.List;
import org.onebeartoe.games.memory.cards.MemoryCard;
import org.onebeartoe.games.memory.cards.MemoryCardsGame;
import org.onebeartoe.games.memory.cards.game.CardsCannedDate;
import org.onebeartoe.games.memory.cards.game.initialization.CardsAlreadyInitializedException;
import org.onebeartoe.games.memory.cards.game.initialization.InvalidPairsException;
import org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GUESS_ONE_ACCEPTED;
import org.onebeartoe.games.memory.cards.game.initialization.TooFewCardsException;
import org.onebeartoe.games.memory.cards.game.initialization.TooManyCardsException;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH;

/**
 *
 * @author Roberto Marquez
 */
public class MemoryCardsGameCardSelectionSpecification
{
    private CardsCannedDate cannedData;

    @BeforeTest
    public void setup()
    {
        cannedData = new CardsCannedDate();
    }

    /**
     * This class verifies criteria 7
     *
     * Go through 3 rounds verifying guess1 and guess2 are accepted
     */
    @Test
    public void selection() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {
        MemoryCardsGame implementation = new MemoryCardsGame();

        List<MemoryCard> cards = cannedData.validCardSetAllTheSame();

        implementation.setCards(cards);

        implementation.startGame();

        // pair 1
        MenuCardsGameResponse response1 = implementation.selectCard1();
        assertEquals(response1, GUESS_ONE_ACCEPTED);
        MenuCardsGameResponse response2 = implementation.selectCard2();
        assertEquals(response2, GUESS_TWO_ACCEPTED_MATCH);

        // pair 2
        MenuCardsGameResponse response3 = implementation.selectCard3();
        assertEquals(response3, GUESS_ONE_ACCEPTED);
        MenuCardsGameResponse response4 = implementation.selectCard4();
        assertEquals(response4, GUESS_TWO_ACCEPTED_MATCH);

        // pair 3
        MenuCardsGameResponse response5 = implementation.selectCard5();
        assertEquals(response5, GUESS_ONE_ACCEPTED);
        MenuCardsGameResponse response6 = implementation.selectCard6();
        assertEquals(response6, GUESS_TWO_ACCEPTED_MATCH);
    }

    /**
     * This class verifies criteria 8
     */
    @Test(expectedExceptions = {IllegalStateException.class})
    public void selection_failsGuess1() throws TooFewCardsException, InvalidPairsException, TooManyCardsException, CardsAlreadyInitializedException
    {
        // get past round 1.  in round 2 for guess 1 select a revealed card and expect rejected request
        MemoryCardsGame implementation = new MemoryCardsGame();

        List<MemoryCard> cards = cannedData.validCardSetAllTheSame();

        implementation.setCards(cards);

        implementation.startGame();

        // get pass round 1
        MenuCardsGameResponse response1 = implementation.selectCard1();
        assertEquals(response1, GUESS_ONE_ACCEPTED);
        MenuCardsGameResponse response2 = implementation.selectCard2();
        assertEquals(response2, GUESS_TWO_ACCEPTED_MATCH);

        MenuCardsGameResponse response3 = implementation.selectCard1();  // again
    }


//TODO: implement this test
//def test_selection_failsMismatch(self):
//    print("hellowwowowoow")
//    implementation = MemoryCardsGame();
//    cards = self.cannedData.validCardSetCountOf2();
//    implementation.setCards(cards);
//    implementation.startGame();
//
//    # setup a mismatch
//    response1 = implementation.selectCard1();
//    self.assertEqual(response1, MemoryCardsGameResponse.GUESS_ONE_ACCEPTED);
//    response2 = implementation.selectCard7();
//    self.assertEqual(response2, MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MISMATCH)
//
//    # verify guess one from the frist round is still selectable
//    response3 = implementation.selectCard1();
//    self.assertEqual(response3, MemoryCardsGameResponse.GUESS_ONE_ACCEPTED);
//
//    response4 = implementation.selectCard6(); # a match for response3
//    self.assertEqual(response4, MemoryCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH);
//
//    # verify guess two from the frist round is still selectable
//    response5 = implementation.selectCard7()
//    self.assertEqual(response5, MemoryCardsGameResponse.GUESS_ONE_ACCEPTED);


    /**
     * This class verifies criteria 8
     */
    @Test(expectedExceptions = {IllegalStateException.class})
    public void selection_failsGuess2() throws TooFewCardsException, InvalidPairsException, TooManyCardsException, CardsAlreadyInitializedException {

        // get past round 1 and on guess 2 of round 2 select a revealed card and expect rejected request
        MemoryCardsGame implementation = new MemoryCardsGame();

        List<MemoryCard> cards = cannedData.validCardSetAllTheSame();

        implementation.setCards(cards);

        implementation.startGame();

        // pair 1
        MenuCardsGameResponse response1 = implementation.selectCard1();
        assertEquals(response1, GUESS_ONE_ACCEPTED);
        MenuCardsGameResponse response2 = implementation.selectCard2();
        assertEquals(response2, GUESS_TWO_ACCEPTED_MATCH);

        // pair 2
        MenuCardsGameResponse response3 = implementation.selectCard3();
        assertEquals(response3, GUESS_ONE_ACCEPTED);
        MenuCardsGameResponse response4 = implementation.selectCard2();   // again
    }
}
