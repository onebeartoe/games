
package org.onebeartoe.games.type.o.rama;

import java.lang.Number;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.scene.layout.Container;

import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.effect.Glow;
import net.onebeartoe.type.areli.targets.VerticalWordTarget;
//import javafx.util.Math;

import net.onebeartoe.type.areli.services.WordsService;
import net.onebeartoe.type.areli.attacks.LineBeam;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import net.onebeartoe.type.areli.attacks.Attack;
import net.onebeartoe.type.areli.attacks.LineBeam;
import net.onebeartoe.type.areli.targets.WordTarget;
import net.onebeartoe.type.areli.pojos.Round;
import net.onebeartoe.type.areli.dialogs.GameSummaryDialog;
import net.onebeartoe.type.areli.dialogs.ListViewGameSummaryDialog;
import net.onebeartoe.type.areli.factories.WordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.DiagnalWordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.StaticWordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.VerticalWordTargetFactory;
import net.onebeartoe.type.areli.nodes.RobotChicken;
import net.onebeartoe.type.areli.pojos.Round;
import net.onebeartoe.type.areli.targets.VerticalWordTarget;

/**
 */
public class App extends Application
{
    private static Scene scene;
    
    int currentRound = 1;

    Media lineBeamSound = new Media("/net/onebeartoe/type/areli/sounds/line-beam-b.au");


    Media removeTargetSound = new Media
(
         "{__DIR__}sounds/line-beam-remove.au"
);

    Media levelIntroSound = new Media( "{__DIR__}sounds/audio.1280453989123.au");
    
    Text elTexto;

    Number elTextoX = new Integer(200);
    
    Node dialog;
//    Text dialog;

    GameSummaryDialog nextRoundDialog;
    
    List<Round> gameSummaries;
    
    RobotChicken robotChicken;

    int wordsPerRoundFactor = 3;
    //var wordsInARound: Integer;

    String [] words ;
    
    int roundMisses = 0;
    
    int misses = 0;
    
    int totalRounds = 5;
    
    Text encouragmentText;

    public App()
    {

        robotChicken = new RobotChicken();

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

        encouragmentText = new Text();
        
        encouragmentText.setWrappingWidth(encouragmentTextWidth);

        encouragmentText.setFont( new Font(24) );

        encouragmentText.setX( width * 0.5 - (encouragmentTextWidth / 2) );
        encouragmentText.setY( 30 );

        StringBinding textBinding = Bindings.createStringBinding(
                () -> "Only " + wordTargets.size() + " more to go, in Round " + currentRound + "}!"
        );

        encouragmentText.textProperty().bind(textBinding);
        
        
        dialog = encouragmentText;

        

        
        

// in JavaFX Script gameSummaries was Round[]        
        gameSummaries = new ArrayList();
//        Round[] gameSummaries;



        //var gameSums : String [];

        var nextRoundButtonText = "Next Round";

        nextRoundDialog  = new ListViewGameSummaryDialog();

        nextRoundDialog.message = "Do you want to play the next round?";
        
        nextRoundDialog.setTranslateX(width / 2 - (nextRoundDialog.width / 2) );
        
        nextRoundDialog.setTranslateY(height / 2 - (nextRoundDialog.height /2) );
        
        nextRoundDialog.dismissButton.setOnAction(t -> 
        {
            if(currentRound > totalRounds)
            {
                currentRound = 1;

//TODO:  Does 'delete' clear the whole list or just the 1st element?
                gameSummaries.clear();
//                delete gameSummaries;

                nextRoundDialog.buttonText.setValue(nextRoundButtonText);
            }

            System.out.println("New round.");

            dialog = encouragmentText;

            loadTargets();

            elTexto.requestFocus();

            playIntro();
        });

        nextRoundDialog.buttonText.setValue( nextRoundButtonText);

        
         wordTargetFactoryA = new StaticWordTargetFactory();

        wordTargetFactoryA.xRange = (double) width;
        
        wordTargetFactoryA.targetMaxX = targetMaxX;
        
        wordTargetFactoryA.targetMaxY = (int) targetMaxY;
                
        wordTargetFactoryA.targetMinY = (int) targetMinY;

        
        
        
         wordTargetFactoryB = new VerticalWordTargetFactory();
        wordTargetFactoryB.xRange = (double) width;
        wordTargetFactoryB.targetMaxX = targetMaxX;
        wordTargetFactoryB.targetMaxY = (int) targetMaxY;
        wordTargetFactoryB.targetMinY = (int) targetMinY;

        
        
        

         wordTargetFactoryC  = new DiagnalWordTargetFactory();
        wordTargetFactoryC.xRange = (double) width;
        wordTargetFactoryC.targetMaxX = targetMaxX;
        wordTargetFactoryC.targetMaxY = (int) targetMaxY;
        wordTargetFactoryC.targetMinY = (int) targetMinY;


        

//TODO: is this needed still?
//        var wordTargetFactory: WordTargetFactory;// = wordTargetServicePool[0];  
    }
    WordTargetFactory wordTargetFactoryA;
    WordTargetFactory wordTargetFactoryB;
    WordTargetFactory wordTargetFactoryC;
    
    WordTargetFactory [] wordTargetServicePool = {wordTargetFactoryA, wordTargetFactoryB, wordTargetFactoryC};
    
    WordTargetFactory wordTargetFactory;
    
    public void playIntro()
    {
        MediaPlayer introSoundPlayer = new MediaPlayer(levelIntroSound);

        introSoundPlayer.setAutoPlay(false);

        introSoundPlayer.setCycleCount(0);
        
        introSoundPlayer.play();
    }    

//var wordsService: WordsService = net.onebeartoe.type.areli.services.implementation.TestingWordService{}
WordsService wordsService = new net.onebeartoe.type.areli.services.implementation.SimpleWordService();

StringProperty input = new SimpleStringProperty("");
// The type of input used to be String
//String input = "";

Number rx  ;

int width = 900;

int height = 600;

List<Attack> attacks;

List<WordTarget> wordTargets;

Integer cannonTipX = 141;

Integer cannonTipY = 421;


private void updateGameSummaries()
{
    nextRoundDialog.listView.getItems().clear();

    for(int i=0; i<gameSummaries.size(); i++)
    {       
        var summary = "Words: {gameSummaries[i].words} \t   Misses: {gameSummaries[i].misses}\t\t    Hit Ratio {(gameSummaries[i].words-gameSummaries[i].misses)/(gameSummaries[i].words*1.0)*100}";
        
        nextRoundDialog.listView.getItems().add("{summary} ");
    }

//    delete nextRoundDialog.listView.items;
//
//    var indexRange = [0 .. sizeof gameSummaries-1];
//
//    for(i in indexRange)
//    {
//        var summary = "Words: {gameSummaries[i].words} \t   Misses: {gameSummaries[i].misses}\t\t    Hit Ratio {(gameSummaries[i].words-gameSummaries[i].misses)/(gameSummaries[i].words*1.0)*100}";
//        
//        insert "{summary} " into nextRoundDialog.listView.items
//    }    
}

    @Override
    public void start(Stage stage) throws IOException
    {        
        loadTargets();

        Main main = new Main();
        
        setupElTexto();

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
        
        ObservableList<Node> children = group.getChildren();
        children.addAll(robotChicken,
                        elTexto
                );

//TODO: Is a alternative to a 'binded' Container needed?
        for( WordTarget target : wordTargets)
        {
            children.add(target);
        }
        
        for(Attack attack : attacks)
        {
            children.add(attack);
        }

        children.add(dialog);
//TODO: Is a alternative to a 'binded' Container needed?
//        [
//            Container
//            {
//                content: bind wordTargets
//            },
//            Container
//            {
//                content: bind attacks
//            },
//            Container
//            {
//                content: bind dialog
//            }
//        ]
    
        return group;
    }



    static void setRoot(String fxml) throws IOException
    {
        scene.setRoot(loadFXML(fxml));
    }
    
    int poolIndex = 0;
    
    private void loadTargets()
    {
        words = wordsService.getWords(currentRound * wordsPerRoundFactor);

        // switch out the word target factory
        wordTargetFactory = wordTargetServicePool[poolIndex];

        poolIndex++;
        if(poolIndex == wordTargetServicePool.length)
        {
            poolIndex = 0;
        }
                
                

        var targets = wordTargetFactory.createTargets(words);

        List<WordTarget> list = new ArrayList();
        for(WordTarget target : targets)
        {
            list.add(target);
        }

        wordTargets = list;
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

    private void setupElTexto() 
    {
        elTexto = new Text();
        
        var font = new Font(36);
        
//TODO: Is the bind still needed on the line below?
//x: bind (width * 0.3)
        elTexto.setX(width * 0.3);

//TODO: Is the bind still needed on the line below?
//y: bind (height * 0.9)        
        elTexto.setY(height * 0.9);
        
        elTexto.textProperty().bind(input);
        
        elTexto.setOnKeyPressed(ke -> 
        {
            
        


//        override var onKeyPressed = function (ke:KeyEvent):Void
//        {
            System.out.println(ke.getCode() );
            
            String updatedValue = input.getValue() + ke.getText();
            
            input.setValue(updatedValue);
            
            System.out.println(input);

            if(ke.getCode() == KeyCode.SPACE)
            {
                input.setValue("");
                
                roundMisses++;
                misses++;
            }

            Boolean targetAchieved = false;
            
            WordTarget matchingTarget;
            
            for(WordTarget target : wordTargets)
            {
                if( target.labelText.equals(input) )
                {
                    input.setValue("");

                    target.animation.stop();

                    var lineBeamSoundPlayer = new MediaPlayer(lineBeamSound);
                    lineBeamSoundPlayer.setAutoPlay(false);
                    lineBeamSoundPlayer.play();

                    targetAchieved = true;
                    matchingTarget = target;

                    LineBeam beam = new LineBeam();
                    beam.startX = cannonTipX;
                    beam.startY = cannonTipY;
                    
                    beam.endX = (int) target.getTranslateX();
                    beam.endY = (int) target.getTranslateY();
                    



                    
               
//TODO: Does beam.removeFrame need initialization
                    var duration = Duration.millis(5);
                    var str = "some-ploop";
                    EventHandler<ActionEvent> handler = (event) -> 
                    {
//TODO: this next line is hte original JavaFX Script code                    
//                        beam.removeFrame.action = function()
                    
                        var removeTargetSoundPlayer = new MediaPlayer(removeTargetSound);
                        removeTargetSoundPlayer.setAutoPlay(false);
                        removeTargetSoundPlayer.setCycleCount(1);
                        removeTargetSoundPlayer.play();

                        beam.animation.stop();

                        wordTargets.remove(target);
//                        delete target from wordTargets;


                        attacks.remove(beam);
//                        delete beam from attacks;

                        
                        if( wordTargets.size() == 0)
                        {
                            var r = new Round();
                            r.misses = misses;
                            r.words = currentRound * wordsPerRoundFactor;
                            
                            gameSummaries.add(r);

                            roundMisses = 0;
                            misses = 0;

                            currentRound++;

                            System.out.println("\nEnd of Round.");

                            updateGameSummaries();

                            if(currentRound > totalRounds)
                            {
//                                        currentRound = 1;
                                nextRoundDialog.buttonText.setValue( "Play Again!");
                                
//TODO: is this the correct way to set the width like int the JavaFX Script version????                                
                                nextRoundDialog.dismissButton.setPrefWidth(100);
//                                nextRoundDialog.dismissButton.width = 100;
                            }

                            dialog = nextRoundDialog;
                        }
                        else
                        {
                            dialog = encouragmentText;
                        }
                    
                    };

KeyFrame keyFrame = new KeyFrame(duration, str, handler);

beam.removeFrame = keyFrame;



                    attacks.add(beam);
//                    insert beam into attacks;
                    
                    

                    target.setEffect( new Glow(1));
//                    target.effect = Glow
//                    {
//                            level: 1
//                    }

                    break;
                }
            }
            
        });
    }
}
