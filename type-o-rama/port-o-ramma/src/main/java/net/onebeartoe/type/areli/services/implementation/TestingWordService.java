package net.onebeartoe.type.areli.services.implementation;

import net.onebeartoe.type.areli.services.WordsService;

public class TestingWordService extends WordsService {
    @Override
    public String[] getWords(int count) {
        return new String[]{"ANT", "CAR", "BAT"};
    }
}
