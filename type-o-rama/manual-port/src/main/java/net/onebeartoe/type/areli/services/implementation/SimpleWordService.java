
package net.onebeartoe.type.areli.services.implementation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class SimpleWordService extends net.onebeartoe.type.areli.services.WordsService
{
    @Override
    public String [] getWords(Integer count) 
    {
        var rand = new Random();

        String [] stockWords =
        {
            "HAPPY", "SMILE", "ANT", "CAR", "MOUSE", "GIRL", "GREEN", "CHEESE", "HAMBURGER", "SNEEZE",
            "GOAT", "FARM", "LOVE", "CAKE", "MOVER", "POOL", "GRASS", "PIZZA", "MONKEY", "PINKY",
            "RED", "BLUE", "ORANGE", "PURPLE", "TAN", "YELLOW", "BLACK", "COMPUTER", "DIAPER", "SIMPLE"
        };
        
//        String [] words;
        List<String> words = new ArrayList();

        if(count >  stockWords.length)
        {
            words  = Arrays.asList(stockWords);
//            words = stockWords;
        }
        else
        {
            for(int i = 0; i<count; i++)
//            var indexRange = [0 .. count-1];
//            for(i in indexRange)
            {
                var w = rand.nextInt( stockWords.length);
                var word = stockWords[w];

                words.add(word);
//                insert word into words
            }
        }

        return words.toArray( new String[0] );
    }

}

