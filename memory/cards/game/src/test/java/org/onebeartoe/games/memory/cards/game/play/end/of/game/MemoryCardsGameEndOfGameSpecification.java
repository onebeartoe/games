
package org.onebeartoe.games.memory.cards.game.play.end.of.game;

import java.util.List;
import org.onebeartoe.games.memory.cards.MemoryCard;
import org.onebeartoe.games.memory.cards.MemoryCardsGame;
import org.onebeartoe.games.memory.cards.game.CardsCannedDate;
import org.onebeartoe.games.memory.cards.game.initialization.CardsAlreadyInitializedException;
import org.onebeartoe.games.memory.cards.game.initialization.InvalidPairsException;
import org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH_END_OF_GAME_WIN;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GUESS_TWO_ACCEPTED_MISMATCH;
import org.onebeartoe.games.memory.cards.game.initialization.TooFewCardsException;
import org.onebeartoe.games.memory.cards.game.initialization.TooManyCardsException;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GUESS_TWO_ACCEPTED_MISMATCH_END_OF_GAME_LOSE;

/**
 *
 * @author Roberto Marquez
 */
public class MemoryCardsGameEndOfGameSpecification
{
    private CardsCannedDate cannedData;

    @BeforeTest
    public void setup() 
    {
        cannedData = new CardsCannedDate();
    }
  

    /**
     * This class verifies criteria 3
     */
    @Test
    public void endGame_loss() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {
        //TODO: implement
        
        /*
            guess 1 covered card
            guess 2 mis-match
            
            assert mismatch response
            assert round 2
            asser
        
            guess 1 covered card
            guess 2 mis-match
        
            assert end of game loss response
        */
        
        MemoryCardsGame implementation = new MemoryCardsGame();
        List<MemoryCard> cards = cannedData.validCardSetCountOf2();
        implementation.setCards(cards);
        implementation.startGame();

        implementation.selectCard1();
        MenuCardsGameResponse mismatch1 = implementation.selectCard7();  // not a match in this data set
        assertEquals(mismatch1, GUESS_TWO_ACCEPTED_MISMATCH);
        assertEquals(implementation.getRound(), 2);
        
        implementation.selectCard2();
        MenuCardsGameResponse mismatch2 = implementation.selectCard8();  // not a match in this data set
        assertEquals(mismatch2, GUESS_TWO_ACCEPTED_MISMATCH_END_OF_GAME_LOSE);
    }

    /**
     * This class verifies criteria 4
     */
    @Test
    public void endGame_win() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {
        //TODO: implement
        
        /*
            start game
        
            select all matches
        
            assert end of game win response
        */
        
        MemoryCardsGame implementation = new MemoryCardsGame();
        List<MemoryCard> cards = cannedData.validCardSetCountOf2();
        implementation.setCards(cards);
        implementation.startGame();        
        
        implementation.selectCard1();
        implementation.selectCard2();
        implementation.selectCard3();
        implementation.selectCard4();
        implementation.selectCard5();
        implementation.selectCard6();
        implementation.selectCard7();
        implementation.selectCard8();
        implementation.selectCard9();
        implementation.selectCard10();
        implementation.selectCard11();
        MenuCardsGameResponse response = implementation.selectCard12();
        
        assertEquals(response, GUESS_TWO_ACCEPTED_MATCH_END_OF_GAME_WIN);
    }    
}
