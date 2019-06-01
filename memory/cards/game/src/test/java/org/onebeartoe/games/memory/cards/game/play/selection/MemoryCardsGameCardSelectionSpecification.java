
package org.onebeartoe.games.memory.cards.game.play.selection;

import org.onebeartoe.games.memory.cards.MemoryCardsGame;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Roberto Marquez
 */
public class MemoryCardsGameCardSelectionSpecification
{
    MemoryCardsGame implementation;
    
    @BeforeTest
    public void setup()
    {
        implementation = new MemoryCardsGame();
    }    
  

    /**
     * This class verifies criteria 7
     */
    @Test
    public void selection()
    {
        //TODO: 
    }

    
    /**
     * This class verifies criteria 8
     */
    @Test
    public void selection_fails()
    {
        //TODO: implement
        //TODO: guess 1 select a revealed card expect rejected request
        //TODO: guess 1 select covered card expect guess 2 state
        //TODO: guess 2 select a revealed card expect rejected request        
    }
}
