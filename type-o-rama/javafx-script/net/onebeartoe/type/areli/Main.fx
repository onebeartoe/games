
package net.onebeartoe.type.areli;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import java.lang.Void;

import java.lang.Boolean;
import javafx.scene.layout.Container;

import javafx.scene.Node;
import java.util.Random;
import net.onebeartoe.type.areli.targets.VerticalWordTarget;
import javafx.util.Math;

import net.onebeartoe.type.areli.services.WordsService;
import net.onebeartoe.type.areli.attacks.LineBeam;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.effect.Glow;
import net.onebeartoe.type.areli.attacks.Attack;
import net.onebeartoe.type.areli.targets.WordTarget;
import net.onebeartoe.type.areli.pojos.Round;
import net.onebeartoe.type.areli.dialogs.GameSummaryDialog;
import net.onebeartoe.type.areli.dialogs.ListViewGameSummaryDialog;
import net.onebeartoe.type.areli.factories.WordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.VerticalWordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.DiagnalWordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.StaticWordTargetFactory;
import net.onebeartoe.type.areli.nodes.RobotChicken;

var lineBeamSound = Media
{
    source: "{__DIR__}sounds/line-beam-b.au"
}

var removeTargetSound = Media
{
    source: "{__DIR__}sounds/line-beam-remove.au"
}

var levelIntroSound = Media
{
    source: "{__DIR__}sounds/audio.1280453989123.au"
}

function playIntro()
{
    var introSoundPlayer = MediaPlayer
    {
        autoPlay:  false
        media: levelIntroSound
        repeatCount: 0
    }
    introSoundPlayer.play();
}


//var wordsService: WordsService = net.onebeartoe.type.areli.services.implementation.TestingWordService{}
var wordsService: WordsService = net.onebeartoe.type.areli.services.implementation.SimpleWordService{};

var input: String = "";
//var input: String = "Have Fun!";

var elTexto: Text;

var elTextoX: Number = 200;

var rx : Number;

var width = 900;

var height: Integer = 600;

var attacks: Attack[];

var wordTargets: WordTarget[];

var cannonTipX: Integer = 141;
var cannonTipY: Integer = 421;

var robotChicken: RobotChicken = RobotChicken
{
    translateX: -35
    translateY: Integer.valueOf(height * 0.61);
}

var slacko = bind robotChicken.cannonTipX;

var random : Random = Random{};

var ct: VerticalWordTarget = VerticalWordTarget
{
    labelText: "CWT"
    xMax: Math.max(width, random.nextInt(width*0.8) )
};
//insert ct into targets;

var targetMinX = width * 0.05;
var targetMinY = height * 0.05;

var targetMaxX = width * 0.95;
var targetMaxY = cannonTipY * 0.95;

var encouragmentTextWidth = 380;
var encouragmentText = Text
{
    wrappingWidth: encouragmentTextWidth
    font : Font
    {
            size: 24
    }
    x: width * 0.5 - (encouragmentTextWidth / 2),
    y: 30
    content: bind "Only {sizeof wordTargets} more to go, in Round {currentRound}!"
};



var dialog: Node = encouragmentText;

/*
var startOfGameText = Text
{
    wrappingWidth: encouragmentTextWidth
    font : Font
    {
            size: 24
    }
    x: width * 0.5 - (encouragmentTextWidth / 2),
    y: 30 + 35
    content: bind "Start typing!"
};
*/

var currentRound = 1;

var totalRounds = 5;

var misses = 0;
var roundMisses = 0;

var gameSummaries : Round[];

var wordsPerRoundFactor = 3;
//var wordsInARound: Integer;

var words : String [];

//var gameSums : String [];

var nextRoundButtonText = "Next Round";
var nextRoundDialog : GameSummaryDialog = ListViewGameSummaryDialog
{
    message: "Do you want to play the next round?";
};
nextRoundDialog.translateX = width / 2 - (nextRoundDialog.width / 2);
nextRoundDialog.translateY = height / 2 - (nextRoundDialog.height /2);
nextRoundDialog.dismissButton.action = function()
{
    if(currentRound > totalRounds)
    {
        currentRound = 1;
        delete gameSummaries;
        nextRoundDialog.buttonText = nextRoundButtonText
    }

    println("New round.");
    dialog = encouragmentText;
    loadTargets();
    elTexto.requestFocus();

    playIntro()
};
nextRoundDialog.buttonText = nextRoundButtonText;

var wordTargetFactoryA: WordTargetFactory = StaticWordTargetFactory
{
    xRange: width

    targetMaxX: targetMaxX

    targetMaxY: targetMaxY
    targetMinY: targetMinY
};

var wordTargetFactoryB: WordTargetFactory = VerticalWordTargetFactory
{
    xRange: width

    targetMaxX: targetMaxX

    targetMaxY: targetMaxY
    targetMinY: targetMinY
};

var wordTargetFactoryC: WordTargetFactory = DiagnalWordTargetFactory
{
    xRange: width

    targetMaxX: targetMaxX

    targetMaxY: targetMaxY
    targetMinY: targetMinY
};

var wordTargetServicePool: WordTargetFactory [] = [wordTargetFactoryA, wordTargetFactoryB, wordTargetFactoryC];

var wordTargetFactory: WordTargetFactory;// = wordTargetServicePool[0];

function updateGameSummaries()
{
    delete nextRoundDialog.listView.items;

    var indexRange = [0 .. sizeof gameSummaries-1];

    for(i in indexRange)
    {
        var summary = "Words: {gameSummaries[i].words} \t   Misses: {gameSummaries[i].misses}\t\t    Hit Ratio {(gameSummaries[i].words-gameSummaries[i].misses)/(gameSummaries[i].words*1.0)*100}";
        
        insert "{summary} " into nextRoundDialog.listView.items
    }
}

function loadTargets()
{
    words = wordsService.getWords(currentRound * wordsPerRoundFactor);

    // switch out the word target factory
    wordTargetFactory = wordTargetServicePool[0];
    delete wordTargetServicePool[0];
    insert wordTargetFactory into wordTargetServicePool;

    var targets = wordTargetFactory.createTargets(words);

    for(target in targets)
    {
        insert target into wordTargets;
    }
};

loadTargets();

Stage
{
    title: "onebeartoe.net - Type O Rama"
    scene: Scene
    {
        width: width
        height: height
        content:
        [
            robotChicken
            ,
            elTexto = Text
            {
                font : Font
                {
                    size : 36
                }
                x: bind (width * 0.3)
                y: bind (height * 0.9)
                content: bind input

                override var onKeyPressed = function (ke:KeyEvent):Void
                {
                    print( ke.code);
                    input += ke.text;
                    print(input);

                    if(ke.code == KeyCode.VK_SPACE)
                    {
                        input = "";
                        roundMisses++;
                        misses++;
                    }

                    var targetAchieved: Boolean = false;
                    var matchingTarget: WordTarget;
                    for(target in wordTargets)
                    {
                        if( target.labelText.equals(input) )
                        {
                            input = "";
                            
                            target.animation.stop();
                            
                            var lineBeamSoundPlayer = MediaPlayer
                            {
                                autoPlay:false
                                media:lineBeamSound
                            }
                            lineBeamSoundPlayer.play();
                            
                            targetAchieved = true;
                            matchingTarget = target;
                        
                            var beam : Attack = LineBeam
                            {
                                startX: cannonTipX
                                startY: cannonTipY

                                endX : target.translateX
                                endY : target.translateY
                            }
                            beam.removeFrame.action = function()
                            {
                                var removeTargetSoundPlayer = MediaPlayer
                                {
                                    autoPlay:  false
                                    media: removeTargetSound
                                    repeatCount: 0
                                }
                                removeTargetSoundPlayer.play();

                                beam.animation.stop();

                                delete target from wordTargets;
                                delete beam from attacks;

                                if(sizeof wordTargets == 0)
                                {
                                    var r = Round
                                    {
                                        misses: misses;
                                        words: currentRound * wordsPerRoundFactor
                                    }
                                    insert r into gameSummaries;

                                    roundMisses = 0;
                                    misses = 0;

                                    currentRound++;
                                    
                                    println("\nEnd of Round.");

                                    updateGameSummaries();

                                    if(currentRound > totalRounds)
                                    {
//                                        currentRound = 1;
                                        nextRoundDialog.buttonText = "Play Again!";
                                        nextRoundDialog.dismissButton.width = 100;
                                    }

                                    dialog = nextRoundDialog;
                                }
                                else
                                {
                                    dialog = encouragmentText
                                }
                            }

                            insert beam into attacks;

                            target.effect = Glow
                            {
                                    level: 1
                            }

                            break;
                        }
                    }
                }
            },
            Container
            {
                content: bind wordTargets
            },
            Container
            {
                content: bind attacks
            },
            Container
            {
                content: bind dialog
            }
        ]
    }
}

elTexto.requestFocus();

playIntro();
