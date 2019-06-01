
package org.onebeartoe.games.memory.cards.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.onebeartoe.games.memory.cards.MemoryCard;
import org.onebeartoe.games.memory.cards.MemoryCardsGame;

/**
 *
 * @author Roberto Marquez
 */
public class CardsCannedDate
{
    public List<MemoryCard> validCardSetCountOf2()
    {
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
        IntStream.rangeClosed(7, MemoryCardsGame.MAX_SIZE)
                 .forEach(i -> 
        {
            MemoryCard card = new MemoryCard();
            
            card.setValue(sameValue2);

            cards.add(card);
        });
        
        return cards;
    }    

    public List<MemoryCard> validCardSetAllTheSame()
    {
        // set all game cards to the same card        
        List<MemoryCard> cards = new ArrayList();
        int sameValue = 5;
        IntStream.rangeClosed(1, MemoryCardsGame.MAX_SIZE)
                 .forEach(i -> 
        {
            MemoryCard card = new MemoryCard();
            
            card.setValue(sameValue);

            cards.add(card);
        });
        
        return cards;
    }
}
