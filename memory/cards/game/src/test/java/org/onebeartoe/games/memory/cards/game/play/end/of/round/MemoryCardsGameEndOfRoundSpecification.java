
package org.onebeartoe.games.memory.cards.game.play.end.of.round;

import java.util.List;
import org.onebeartoe.games.memory.cards.MemoryCard;
import static org.onebeartoe.games.memory.cards.MemoryCardStates.REVEALED;
import org.onebeartoe.games.memory.cards.MemoryCardsGame;
import org.onebeartoe.games.memory.cards.game.CardsCannedDate;
import org.onebeartoe.games.memory.cards.game.initialization.CardsAlreadyInitializedException;
import org.onebeartoe.games.memory.cards.game.initialization.InvalidPairsException;
import org.onebeartoe.games.memory.cards.game.initialization.TooFewCardsException;
import org.onebeartoe.games.memory.cards.game.initialization.TooManyCardsException;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Roberto Marquez
 */
public class MemoryCardsGameEndOfRoundSpecification
{
    private CardsCannedDate cannedData;

    @BeforeTest
    public void setup() 
    {
        cannedData = new CardsCannedDate();
    }

    /**
     * This class verifies criteria 6
     */
    @Test
    public void endOfRound() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {
        /*
            note round 1
            select a valid guess 1
            select a valid guess 2
            assert round is now 2
            select a valid guess 1
            select a valid guess 2
            assert round is now 3   
        */

        MemoryCardsGame implementation = new MemoryCardsGame();
        List<MemoryCard> cards = cannedData.validCardSetAllTheSame();
        implementation.setCards(cards);
        implementation.startGame();

        // assert round 1
        int roundNumber = implementation.getRound();
        assertEquals(roundNumber, 1);
        
        // assert round 2
        implementation.selectCard1();        
        implementation.selectCard2();
        roundNumber = implementation.getRound();
        assertEquals(roundNumber, 2);

        // assert round 3
        implementation.selectCard3();        
        implementation.selectCard4();
        roundNumber = implementation.getRound();
        assertEquals(roundNumber, 3);

        // assert round 4
        implementation.selectCard5();
        implementation.selectCard6();
        roundNumber = implementation.getRound();
        assertEquals(roundNumber, 4);        
    }

    /**
     * This class verifies criteria 9
     */
    @Test
    public void endOfRound_negative() throws TooFewCardsException, 
            TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {
        /*
            assert round is 1
            select a valid guess 1       
            select an invalid guess 2
            assert round is still 1
            select a valid guess 2
            assert round is now 2
            select a valid guess 1       
            select an invalid guess 2
            assert round is still 2
        */
        
        MemoryCardsGame implementation = new MemoryCardsGame();
        List<MemoryCard> cards = cannedData.validCardSetAllTheSame();
        implementation.setCards(cards);
        implementation.startGame();

        // assert round 1
        int roundNumber = implementation.getRound();
        assertEquals(roundNumber, 1);

        implementation.selectCard1();
        try
        {
            implementation.selectCard1(); // again
        }
        catch(IllegalStateException e)
        {
            // expected
        }
        assertEquals(implementation.getRound(), 1); // still round 1;
        
        implementation.selectCard2();
        assertEquals(implementation.getRound(), 2); //now round 2
        
        implementation.selectCard3();
        try
        {
            implementation.selectCard2(); // again
        }
        catch(IllegalStateException e)
        {
            // expected
        }
        assertEquals(implementation.getRound(), 2); // still round 2;
    }
    
    /**
     * This class verifies criteria 10
     */
    @Test
    public void endOfRound_guessCardsAreMarkedAsRevealed() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {
        /*
            start game
                            
            guess two matching cards
                                
            assert guess 1 card is marked as revealed
        
            assert guess 2 card is marked as revealed
        */
        
        MemoryCardsGame implementation = new MemoryCardsGame();
        List<MemoryCard> cards = cannedData.validCardSetAllTheSame();
        implementation.setCards(cards);
        implementation.startGame();        
        
        implementation.selectCard1();
        implementation.selectCard2();
        
        assertEquals(implementation.getCardStatus1(), REVEALED);
        assertEquals(implementation.getCardStatus2(), REVEALED);
        
        implementation.selectCard3();
        implementation.selectCard4();
        
        assertEquals(implementation.getCardStatus3(), REVEALED);
        assertEquals(implementation.getCardStatus4(), REVEALED);        
    }

    /**
     * This class verifies criteria 11
     */
    @Test
    public void endOfRound_mismatchIsRecored() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {        
        /*
            start game
        
            assert mis-match count is 0
        
            guess 1 any covered card
        
            guess 2 a mismatch
        
            assert mis-match count is 1
        */
        
        MemoryCardsGame implementation = new MemoryCardsGame();
        List<MemoryCard> cards = cannedData.validCardSetCountOf2();
        implementation.setCards(cards);
        implementation.startGame();        
        
        assertEquals(implementation.getMismatchCount(), 0);
        
        implementation.selectCard1();        
        try
        {
            implementation.selectCard7(); // the 7th card starts the other set of cards (different from card 1)
        }
        catch(IllegalStateException e)
        {
            // expected
        }
                
        assertEquals(implementation.getMismatchCount(), 1);
    }    
}
