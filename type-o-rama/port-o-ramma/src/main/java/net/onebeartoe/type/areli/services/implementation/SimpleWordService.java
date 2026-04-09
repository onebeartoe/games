package net.onebeartoe.type.areli.services.implementation;

import net.onebeartoe.type.areli.services.WordsService;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class SimpleWordService extends WordsService {
    @Override
    public String[] getWords(int count) {
        Random rand = new Random();

        String[] stockWords = {
            "HAPPY", "SMILE", "ANT", "CAR", "MOUSE", "GIRL", "GREEN", "CHEESE", "HAMBURGER", "SNEEZE",
            "GOAT", "FARM", "LOVE", "CAKE", "MOVER", "POOL", "GRASS", "PIZZA", "MONKEY", "PINKY",
            "RED", "BLUE", "ORANGE", "PURPLE", "TAN", "YELLOW", "BLACK", "COMPUTER", "DIAPER", "SIMPLE"
        };

        if (count >= stockWords.length) {
            return stockWords;
        } else {
            String[] words = new String[count];
            for (int i = 0; i < count; i++) {
                int w = rand.nextInt(stockWords.length);
                words[i] = stockWords[w];
            }
            return words;
        }
    }
}
