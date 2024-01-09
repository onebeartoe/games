
package org.onebeartoe.minecraft.statistics;

/**
 *
 */
public class Statistic
{
    String name;

    int value;

    public Statistic(String name, int value)
    {
        this.name = name;

        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
