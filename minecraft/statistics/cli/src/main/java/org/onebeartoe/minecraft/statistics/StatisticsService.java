
package org.onebeartoe.minecraft.statistics;

import java.nio.file.Path;

/**
 *
 */
public class StatisticsService
{

    public StatisticsReport prepare(Path path) 
    {
        return new StatisticsReport();
    }
    
}
