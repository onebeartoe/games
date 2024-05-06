
package net.onebeartoe.type.areli.services.implementation;

public class TestingWordService extends net.onebeartoe.type.areli.services.WordsService
{

//    function getWords(): String []
//  override function getWords(): String []
//    public override function getWords(count : Integer) : String []
//    {
//        var words : String [] =
//        [
//            "ANT", "CAR", "BAT"
//        ];
//
//        return words;
//    }

    
    
//TODO: get rid of this silly method once the app runs    
    @Override
    public String [] getWords(Integer i)
    {
        String [] words =
        {
            "ANT", "CAR", "BAT"
        };

        return words;
    }    
    
}

