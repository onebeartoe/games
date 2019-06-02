
package org.onebeartoe.games.memory.cards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.onebeartoe.games.memory.cards.MemoryCardStates.REVEALED;
import org.onebeartoe.games.memory.cards.game.initialization.CardsAlreadyInitializedException;

import org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.CARDS_INITIALIZED;
import org.onebeartoe.games.memory.cards.game.initialization.TooFewCardsException;
import org.onebeartoe.games.memory.cards.game.initialization.TooManyCardsException;
import org.onebeartoe.games.memory.cards.game.initialization.InvalidPairsException;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GAME_IN_PROGRESS;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GUESS_ONE_ACCEPTED;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GUESS_TWO_ACCEPTED_MISMATCH;
import static org.onebeartoe.games.memory.cards.game.initialization.MenuCardsGameResponse.GUESS_TWO_ACCEPTED_MATCH_END_OF_GAME_WIN;

/**
 * This class abstracts a memory cards game.
 * 
 * @author Roberto Marquez
 */
public class MemoryCardsGame
{
    public static final int MAX_SIZE = 12;
    
    private boolean inProgress;
    
    private MemoryCard guess1;
    
    private MemoryCard guess2;
    
    private List<MemoryCard> cards;
    
    private int round;
    
    private int mismatchCount;
    
    public MemoryCardsGame()
    {
        inProgress = false;
        
        round = 1;
    }

    public MenuCardsGameResponse setCards(List<MemoryCard> cards) throws TooFewCardsException,
            TooManyCardsException, InvalidPairsException, CardsAlreadyInitializedException
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

        if( !hasValidPairs(cards) )
        {
            throw new InvalidPairsException();
        }

        this.cards = cards;
        
        MenuCardsGameResponse response = CARDS_INITIALIZED;
        
        return response;
    }

    public MenuCardsGameResponse startGame()
    {
        if(cards == null)
        {
            throw new IllegalStateException("the cards have not been set");
        }
        
        inProgress = true;
        
        return GAME_IN_PROGRESS;
    }

    private boolean hasValidPairs(List<MemoryCard> cards)
    {
        List<MemoryCard> copy = new ArrayList();
        copy.addAll(cards);
        copy.sort( Comparator.comparingInt(MemoryCard::getValue) ) ;
        
        int half = MAX_SIZE / 2;
        
        boolean validPairs = true;
        
        for(int x = 0; x < half; x++)
        {
            int i = x * 2;
            
            MemoryCard first = copy.get(i);
            MemoryCard second = copy.get(i+1);

            if(first.getValue() != second.getValue())
            {
                validPairs = false;
                
                break;
            }
        }
        
        return validPairs;
    }    

    private MenuCardsGameResponse selectCard(MemoryCard card) 
    {
        if(!inProgress)
        {
            throw new IllegalStateException("selecting cards while the game is not in progress is not allowed");
        }
        
        if(card.getState() == MemoryCardStates.REVEALED)
        {
            throw new IllegalStateException("the card is revealed already");
        }
        
        MenuCardsGameResponse response;
        
        card.setState(REVEALED);
        
        if(guess1 == null)
        {
            guess1 = card;
            response = GUESS_ONE_ACCEPTED;
        }
        else
        {
            guess2 = card;
            
            if(guess1.getValue() == guess2.getValue())
            {
                boolean allPairsFound = allPairsFound();
                if(allPairsFound)
                {
                    response = GUESS_TWO_ACCEPTED_MATCH_END_OF_GAME_WIN;
                }
                else
                {
                    response = GUESS_TWO_ACCEPTED_MATCH;
                }
            }
            else
            {
                mismatchCount++;
                
                if(mismatchCount == 2)
                {
                    response = MenuCardsGameResponse.GUESS_TWO_ACCEPTED_MISMATCH_END_OF_GAME_LOSE;
                }
                else
                {                
                    response = GUESS_TWO_ACCEPTED_MISMATCH;
                }
            }
            
            round++;
            
            resetGuesses();
        }

        return response;        
    }
    
    public MenuCardsGameResponse selectCard1() 
    {
        MemoryCard card1 = cards.get(0);
        
        return selectCard(card1);
    }

    public MenuCardsGameResponse selectCard2() 
    {
        MemoryCard card = cards.get(1);
        
        return selectCard(card);
    }

    public MenuCardsGameResponse selectCard3() {
        MemoryCard card = cards.get(2);
        
        return selectCard(card);
    }

    public MenuCardsGameResponse selectCard4() 
    {
        MemoryCard card = cards.get(3);
        
        return selectCard(card);
    }

    public MenuCardsGameResponse selectCard5() 
    {
        MemoryCard card = cards.get(4);
        
        return selectCard(card);
    }

    public MenuCardsGameResponse selectCard6() 
    {
        MemoryCard card = cards.get(5);
        
        return selectCard(card);
    }
    
    public MenuCardsGameResponse selectCard7() 
    {                
        MemoryCard card = cards.get(6);
   
        return selectCard(card);
    }    


    public MenuCardsGameResponse selectCard8() 
    {
        MemoryCard card = cards.get(7);
   
        return selectCard(card);        
    }    
    
    private void resetGuesses() 
    {
        guess1 = null;
        guess2 = null;
    }

    public int getRound() 
    {
        return round;
    }

    public MemoryCardStates getCardStatus1() 
    {
        int i = 0;
        
        MemoryCard card = cards.get(i);
     
        return card.getState();
    }

    public MemoryCardStates getCardStatus2() 
    {
        int i = 1;
        
        MemoryCard card = cards.get(i);
     
        return card.getState();
    }

    public MemoryCardStates getCardStatus3() 
    {
        int i = 2;
        
        MemoryCard card = cards.get(i);
     
        return card.getState();
    }

    public MemoryCardStates getCardStatus4() 
    {
        int i = 3;
        
        MemoryCard card = cards.get(i);
     
        return card.getState();
    }

    public MemoryCardStates getCardStatus5() 
    {
        int i = 4;
        
        MemoryCard card = cards.get(i);
     
        return card.getState();
    }

    public MemoryCardStates getCardStatus6() 
    {
        int i = 5;
        
        MemoryCard card = cards.get(i);
     
        return card.getState();
    }

    public int getMismatchCount() 
    {
        return mismatchCount;
    }

    public MenuCardsGameResponse selectCard9() 
    {
        MemoryCard card = cards.get(8);
        
        return selectCard(card);        
    }

    public MenuCardsGameResponse selectCard10() 
    {
        MemoryCard card = cards.get(9);
        
        return selectCard(card);
    }

    public MenuCardsGameResponse selectCard11() 
    {
        MemoryCard card = cards.get(10);
        
        return selectCard(card);
    }

    public MenuCardsGameResponse selectCard12() 
    {
        MemoryCard card = cards.get(11);
        
        return selectCard(card);
    }

    private boolean allPairsFound() 
    {
        boolean allFound = true;
        
        for(MemoryCard card : cards)
        {
            if(card.getState() != REVEALED)
            {
                allFound = false;
                
                break;
            }
        }
        
        return allFound;
    }
}
