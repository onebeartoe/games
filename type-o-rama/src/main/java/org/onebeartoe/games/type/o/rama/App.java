
package org.onebeartoe.games.type.o.rama;

import net.onebeartoe.type.areli.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import java.lang.Void;

import java.lang.Boolean;
import java.util.Random;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
//import javafx.scene.layout.Container;

import javafx.scene.Node;
import javafx.scene.Group;
import net.onebeartoe.type.areli.targets.VerticalWordTarget;
//import javafx.util.Math;

import net.onebeartoe.type.areli.services.WordsService;
import net.onebeartoe.type.areli.attacks.LineBeam;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.effect.Glow;
import javafx.scene.text.Text;
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
import net.onebeartoe.type.areli.targets.VerticalWordTarget;

/**
 */
public class App extends Application
{
    private static Scene scene;
    
    int currentRound = 1;

    Media lineBeamSound = new Media("{__DIR__}sounds/line-beam-b.au");


    Media removeTargetSound = new Media
(
         "{__DIR__}sounds/line-beam-remove.au"
);

    Media levelIntroSound = new Media( "{__DIR__}sounds/audio.1280453989123.au");
    
    Text elTexto;

    Number elTextoX = new Number(200);   

    public App()
    {

        var robotChicken = new RobotChicken();

        robotChicken.translateXProperty().set(-35);

        robotChicken.translateYProperty().set( height * 0.61 );
        

        var slacko = robotChicken.cannonTipX;

        var random  = new Random();

        var ct = new VerticalWordTarget();
        
        ct.labelText = "CWT";

        double upperLimit = (int) width * 0.8;
        
        int xMax = random.nextInt( (int) upperLimit);
        
        ct.xMax = Math.max(width, xMax);

        var targetMinX = width * 0.05;
        var targetMinY = height * 0.05;

        var targetMaxX = width * 0.95;
        var targetMaxY = cannonTipY * 0.95;

        var encouragmentTextWidth = 380;

        var encouragmentText = new Text();
        {
            encouragmentText.setWrappingWidth(encouragmentTextWidth);
                    
            encouragmentText.setFont( new Font(24) );
            
            encouragmentText.setX( width * 0.5 - (encouragmentTextWidth / 2) );
            encouragmentText.setY( 30 );
            
            StringBinding textBinding = Bindings.createStringBinding(
                    () -> "Only " + wordTargets.length + " more to go, in Round " + currentRound + "}!"
            );
            
            encouragmentText.textProperty().bind(textBinding);
        };

        
        
        
        
        
        
        Node dialog = encouragmentText;

        

        var totalRounds = 5;

        var misses = 0;
        var roundMisses = 0;

        Round[] gameSummaries  ;

        var wordsPerRoundFactor = 3;
        //var wordsInARound: Integer;

        String [] words ;

        //var gameSums : String [];

        var nextRoundButtonText = "Next Round";
        GameSummaryDialog nextRoundDialog  = ListViewGameSummaryDialog();
        nextRoundDialog.message: "Do you want to play the next round?";
        
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


        WordTargetFactory [] wordTargetServicePool = {wordTargetFactoryA, wordTargetFactoryB, wordTargetFactoryC};

        var wordTargetFactory: WordTargetFactory;// = wordTargetServicePool[0];  
    }

    public void playIntro()
    {
        MediaPlayer introSoundPlayer = new MediaPlayer(levelIntroSound);
introSoundPlayer.setAutoPlay(false);
introSoundPlayer.setRepeatCount(0);
        
        introSoundPlayer.play();
    }    








//var wordsService: WordsService = net.onebeartoe.type.areli.services.implementation.TestingWordService{}
WordsService wordsService = new 
net.onebeartoe.type.areli.services.implementation.SimpleWordService();

String input = "";



Number rx  ;

int width = 900;

int height = 600;

Attack[] attacks;

WordTarget[] wordTargets;

Integer cannonTipX = 141;
Integer cannonTipY = 421;


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

    @Override
    public void start(Stage stage) throws IOException
    {        
        loadTargets();

        Main main = new Main();

        elTexto.requestFocus();

        playIntro();

        Parent parent = loadFXML("primary");

parent = loadGroup();
        
        scene = new Scene(parent, 640, 480);

        var title = "onebeartoe.net - Type O Rama";

        stage.setTitle(title);

        stage.setScene(scene);

        stage.show();
    }


    public Group loadGroup()
    {
        Group group = new Group();
        
        group.getChildren()
                .add(robotChicken);

Stage
{
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

        return null;
    }



    static void setRoot(String fxml) throws IOException
    {
        scene.setRoot(loadFXML(fxml));
    }
    
    private void loadTargets()
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

    private static Parent loadFXML(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
