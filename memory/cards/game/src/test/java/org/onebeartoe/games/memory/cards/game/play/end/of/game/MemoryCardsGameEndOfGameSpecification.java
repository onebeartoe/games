
package org.onebeartoe.games.memory.cards.game.play.end.of.game;

import org.onebeartoe.games.memory.cards.MemoryCardsGame;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Roberto Marquez
 */
public class MemoryCardsGameEndOfGameSpecification
{
    MemoryCardsGame implementation;
    
    @BeforeTest
    public void setup()
    {
        implementation = new MemoryCardsGame();
    }    
  

    /**
     * This class verifies criteria 3
     */
    @Test
    public void endGame_loss()
    {
        //TODO: implement
        
        /*
            guess 1 covered card
            guess 2 mis-match
            
            assert round 2
        
            guess 1 covered card
            guess 2 mis-match
        
            assert end of game loss response
        */
    }

    /**
     * This class verifies criteria 4
     */
    @Test
    public void endGame_win()
    {
        //TODO: implement
        
        /*
            select all matches
        
            assert end of game win response
        */
    }
    
}
