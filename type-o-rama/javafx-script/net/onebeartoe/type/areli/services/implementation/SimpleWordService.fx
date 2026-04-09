
package net.onebeartoe.type.areli.services.implementation;
import java.util.Random;

public class SimpleWordService extends net.onebeartoe.type.areli.services.WordsService
{



//    function getWords(): String []
//  override function getWords(): String []
    public override function getWords(count : Integer) : String []
    {
        var rand : Random = Random{};

        var stockWords : String [] =
        [
            "HAPPY", "SMILE", "ANT", "CAR", "MOUSE", "GIRL", "GREEN", "CHEESE", "HAMBURGER", "SNEEZE",
            "GOAT", "FARM", "LOVE", "CAKE", "MOVER", "POOL", "GRASS", "PIZZA", "MONKEY", "PINKY",
            "RED", "BLUE", "ORANGE", "PURPLE", "TAN", "YELLOW", "BLACK", "COMPUTER", "DIAPER", "SIMPLE"
        ];
        
        var words : String [];

        if(count > sizeof stockWords)
        {
            words = stockWords;
        }
        else
        {
            var indexRange = [0 .. count-1];

            for(i in indexRange)
            {
                var w = rand.nextInt(sizeof stockWords);
                var word = stockWords[w];

                insert word into words
            }
        }

        return words;
    }

}

