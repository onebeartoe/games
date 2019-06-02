
package org.onebeartoe.games.memory.cards;

/**
 *
 * @author Roberto Marquez
 */
public class MemoryCard
{
    private int value;
    
    private MemoryCardStates state;
    
    public MemoryCard()
    {
        state = MemoryCardStates.COVERED;
    }

    public MemoryCardStates getState() {
        return state;
    }

    public void setState(MemoryCardStates state) {
        this.state = state;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int i)
    {
        this.value = i;
    }


    
}
