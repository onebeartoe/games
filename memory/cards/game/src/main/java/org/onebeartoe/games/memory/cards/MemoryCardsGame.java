
package org.onebeartoe.games.memory.cards;

import java.util.Comparator;
import java.util.List;
import org.onebeartoe.games.memory.cards.game.initialization.CardsAlreadyInitializedException;

import org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.CARDS_INITIALIZED;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.IN_PROGRESS;
import org.onebeartoe.games.memory.cards.game.initialization.TooFewCardsException;
import org.onebeartoe.games.memory.cards.game.initialization.TooManyCardsException;
import org.onebeartoe.games.memory.cards.game.initialization.InvalidPairsException;
/**
 * This class abstracts a memory cards game.
 * 
 * @author Roberto Marquez
 */
public class MemoryCardsGame
{
    public static final int MAX_SIZE = 12;
    
    private boolean inProgress;
    
    public MemoryCardsGame()
    {
        inProgress = false;
    }

    public MenuCardsGameResponse setCards(List<MemoryCard> cards) throws TooFewCardsException, TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
    {
        if(inProgress)
        {
            throw new CardsAlreadyInitializedException();
        }
        
        if(cards == null || cards.size() < MAX_SIZE)
        {
            throw new TooFewCardsException();
        }
        
        if(cards.size() > MAX_SIZE)
        {
            throw new TooManyCardsException();
        }
        
        MenuCardsGameResponse response;
        
        if( !hasValidPairs(cards) )
        {
            throw new InvalidPairsException();
        }
        
        return CARDS_INITIALIZED;
    }

    public MenuCardsGameResponse startGame()
    {
        inProgress = true;
        
        return IN_PROGRESS;
    }

    private boolean hasValidPairs(List<MemoryCard> cards)
    {
        cards.sort( Comparator.comparingInt(MemoryCard::getValue) ) ;
        
        int half = MAX_SIZE / 2;
        
        boolean validPairs = true;
        
        for(int x = 0; x < half; x++)
        {
            int i = x * 2;
            
            MemoryCard first = cards.get(i);
            MemoryCard second = cards.get(i+1);

            if(first.getValue() != second.getValue())
            {
                validPairs = false;
                
                break;
            }
        }
        
        return validPairs;
    }    
}
