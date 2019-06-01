
package org.onebeartoe.games.memory.cards.game.play.end.of.round;

import org.onebeartoe.games.memory.cards.MemoryCardsGame;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Roberto Marquez
 */
public class MemoryCardsGameEndOfRoundSpecification
{
    MemoryCardsGame implementation;
    
    @BeforeTest
    public void setup()
    {
        implementation = new MemoryCardsGame();
    }    
  

    /**
     * This class verifies criteria 6
     */
    @Test
    public void endOfRound()
    {
        //TODO: impement
        
        /*
            note round 1
            select a valid guess 1
            select a valid guess 2
            assert round is now 2
            select a valid guess 1
            select a valid guess 2
            assert round is now 3
        
        
        */
    }

    /**
     * This class verifies criteria 9
     */
    @Test
    public void endOfRound_negative()
    {
        //TODO: impement
        
        /*
            note round is 1
            select a valid guess 1       
            select an invalid guess 2
            assert round is still 1
            select a valid guess 2
            note round is now 2
            select a valid guess 1       
            select an invalid guess 2
            assert round is still 2
        */
    }
    
    /**
     * This class verifies criteria 10
     */
    @Test
    public void endOfRound_guessCardsAreMarkedAsRevealed()
    {
        //TODO: impement
        
        /*
            guess two matching cards
        
            assert a match result
            
            assert guess 1 is marked as revealed
        
            assert guess 2 is marked as revealed
        */
    }

    /**
     * This class verifies criteria 11
     */
    @Test
    public void endOfRound_mismatchIsRecored()
    {
        //TODO: impement
        
        /*
            assert mis-match count is 0
        
            guess 1 any covered card
        
            guess 2 a mismatch
        
            assert mis-match count is 1
        */
    }


    
}
