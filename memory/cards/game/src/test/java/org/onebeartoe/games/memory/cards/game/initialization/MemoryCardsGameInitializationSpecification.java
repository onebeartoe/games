
package org.onebeartoe.games.memory.cards.game.initialization;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.onebeartoe.games.memory.cards.MemoryCard;
import org.onebeartoe.games.memory.cards.MemoryCardsGame;
import org.onebeartoe.games.memory.cards.game.CardsCannedDate;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.CARDS_INITIALIZED;
import static org.testng.Assert.assertTrue;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GAME_IN_PROGRESS;

/**
 * This class has unit tests for the MemoryCardsGame class
 * 
 * @author Roberto Marquez
 */
public class MemoryCardsGameInitializationSpecification
{
//    private MemoryCardsGame implementation;
    
    private CardsCannedDate cannedData;
    
    @BeforeTest
    public void setup()
    {
//        MemoryCardsGame implementation = new MemoryCardsGame();
        
        cannedData = new CardsCannedDate();
    }
    
    /**
     * This class verifies criteria 1
     * 
     * All cards are the same
     */
    @Test
    public void initialization_allTheSameCard() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    { 
        MemoryCardsGame implementation = new MemoryCardsGame();
        
        List<MemoryCard> cards = cannedData.validCardSetAllTheSame();
        
        MenuCardsGameResponse actual = implementation.setCards(cards);
        
        MenuCardsGameResponse expected = CARDS_INITIALIZED;
        
        assertEquals(actual, expected);
    }

    /**
     * This class verifies criteria 1
     * 
     * There are pairs in the card set.
     */
    @Test
    public void initialization_withDifferentPairs() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {        
        MemoryCardsGame implementation = new MemoryCardsGame();

        List<MemoryCard> cards = cannedData.validCardSetCountOf2();

        MenuCardsGameResponse response = implementation.setCards(cards);
        
        assert response == CARDS_INITIALIZED;        
    }
    
    /**
     * This class verifies criteria 1
     */
    @Test(expectedExceptions = {TooFewCardsException.class})
    public void initialization_fails_tooFewCards() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {        
        MemoryCardsGame implementation = new MemoryCardsGame();
        
        List<MemoryCard> cards = new ArrayList();
        
        IntStream.rangeClosed(1, 8)
                 .forEach(i -> 
        {
            MemoryCard card = new MemoryCard();
            
            card.setValue(2);

            cards.add(card);
        });        
        
        MenuCardsGameResponse response = implementation.setCards(cards);
    }
    
    /**
     * This class verifies criteria 1
     */
    @Test(expectedExceptions = {TooManyCardsException.class})
    public void initialization_fails_tooManyCards() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {
        MemoryCardsGame implementation = new MemoryCardsGame();
        
        
        List<MemoryCard> cards = new ArrayList();
        
        IntStream.rangeClosed(1, 25)  // way too many
                 .forEach(i -> 
        {
            MemoryCard card = new MemoryCard();
            
            card.setValue(2);

            cards.add(card);
        });        
        
        MenuCardsGameResponse response = implementation.setCards(cards);        
    }
    
    
    /**
     * This class verifies criteria 1
     */
    @Test(expectedExceptions = {InvalidPairsException.class})
    public void initialization_fails_missingPairs() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {
        MemoryCardsGame implementation = new MemoryCardsGame();
        
        
        List<MemoryCard> cards = new ArrayList();
        
        int sameValue = 5;
        IntStream.rangeClosed(1, 11)
                 .forEach(i -> 
        {
            MemoryCard card = new MemoryCard();            
            card.setValue(sameValue);
            cards.add(card);
        });
        
        int uniqueValue = 10;
        MemoryCard card = new MemoryCard();
        card.setValue(uniqueValue);
        cards.add(card);        
        
        MenuCardsGameResponse response = implementation.setCards(cards);        
    }


    /**
     * This class verifies criteria 2
     */
    @Test(expectedExceptions = {CardsAlreadyInitializedException.class})
    public void initialization_fails_resetingOfCardsAfterGameStart() throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {        
        MemoryCardsGame implementation = new MemoryCardsGame();
        
        List<MemoryCard> cards = cannedData.validCardSetCountOf2();
        
        MenuCardsGameResponse setCardsResponse = implementation.setCards(cards);
        
        assertEquals(setCardsResponse, CARDS_INITIALIZED);
        
        MenuCardsGameResponse startGameResponse = implementation.startGame();
        
        assertTrue(startGameResponse == GAME_IN_PROGRESS);
        
        // set game cards (again)
        List<MemoryCard> secondCards = cannedData.validCardSetAllTheSame();
        implementation.setCards(secondCards);
    }
}
