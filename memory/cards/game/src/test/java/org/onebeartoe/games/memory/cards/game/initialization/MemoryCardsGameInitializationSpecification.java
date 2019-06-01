
package org.onebeartoe.games.memory.cards.game.initialization;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.onebeartoe.games.memory.cards.MemoryCard;
import org.onebeartoe.games.memory.cards.MemoryCardsGame;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.CardsInitialized;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This class has unit tests for the MemoryCardsGame class
 * 
 * @author Roberto Marquez
 */
public class MemoryCardsGameInitializationSpecification
{
    MemoryCardsGame implementation;
    
    @BeforeTest
    public void setup()
    {
        implementation = new MemoryCardsGame();
    }
    
    /**
     * This class verifies criteria 1
     * 
     * All cards are the same
     */
    @Test
    public void initialization_allTheSameCard()
    {
        //TODO: implement
        
        // set all game cards to the same card
        
        
        
        List<MemoryCard> cards = new ArrayList();
        int sameValue = 5;
        IntStream.rangeClosed(1, 12)
                 .forEach(i -> 
        {
            MemoryCard card = new MemoryCard();
            
            card.setValue(sameValue);

            cards.add(card);
        });
        
        MenuCardsGameResponse response = implementation.setCards(cards);
        
        assert response == CardsInitialized;
    }
  

    /**
     * This class verifies criteria 1
     * 
     * There are pairs in the card set.
     */
    @Test
    public void initialization_withDifferentPairs()
    {
        //TODO: implement
        
        List<MemoryCard> cards = new ArrayList();
        
        int sameValue1 = 5;
        IntStream.rangeClosed(1, 6)
                 .forEach(i -> 
        {
            MemoryCard card = new MemoryCard();
            
            card.setValue(sameValue1);

            cards.add(card);
        });
        
        int sameValue2 = 10;
        IntStream.rangeClosed(7, 12)
                 .forEach(i -> 
        {
            MemoryCard card = new MemoryCard();
            
            card.setValue(sameValue2);

            cards.add(card);
        });        
        
        MenuCardsGameResponse response = implementation.setCards(cards);
        
        assert response == CardsInitialized;
        
    }
    
    /**
     * This class verifies criteria 1
     */
    @Test
    public void initialization_fails_missingCards()
    {
        //TODO: implement        
    }
    
    /**
     * This class verifies criteria 1
     */
    @Test
    public void initialization_fails_tooManyCards()
    {
        //TODO: implement        
    }
    
    
    /**
     * This class verifies criteria 1
     */
    @Test
    public void initialization_fails_missingPairs()
    {
        //TODO: implement        
    }


    /**
     * This class verifies criteria 2
     */
    @Test
    public void initialization_fails_resetingOfCardsAfterGameStart()
    {
        //TODO: implement

        /*
            initialize game 
        
            set game cards
        
            assert game status is pregame
        
            start game
        
            assert game statte is in-progress
        
            set game cards (again)
        
            expect CardsAlreadyInitializedException
        */
    }
    
    
    
}
