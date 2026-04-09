package net.onebeartoe.type.areli;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.onebeartoe.type.areli.attacks.Attack;
import net.onebeartoe.type.areli.attacks.LineBeam;
import net.onebeartoe.type.areli.factories.WordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.DiagnalWordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.StaticWordTargetFactory;
import net.onebeartoe.type.areli.factories.implementation.VerticalWordTargetFactory;
import net.onebeartoe.type.areli.nodes.RobotChicken;
import net.onebeartoe.type.areli.pojos.Round;
import net.onebeartoe.type.areli.services.WordsService;
import net.onebeartoe.type.areli.services.implementation.SimpleWordService;
import net.onebeartoe.type.areli.targets.WordTarget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainApp extends Application {
    private int width = 900;
    private int height = 600;
    private int cannonTipX = 141;
    private int cannonTipY = 421;

    private String input = "";
    private Text elTexto;
    private Text encouragmentText;
    private RobotChicken robotChicken;
    
    private List<WordTarget> wordTargets = new ArrayList<>();
    private List<Attack> attacks = new ArrayList<>();
    private List<Round> gameSummaries = new ArrayList<>();

    private WordsService wordsService = new SimpleWordService();
    private List<WordTargetFactory> wordTargetServicePool = new ArrayList<>();
    
    private int currentRound = 1;
    private int totalRounds = 5;
    private int wordsPerRoundFactor = 3;
    private int misses = 0;

    private Pane root;
    private Media lineBeamSound;
    private Media removeTargetSound;
    private Media levelIntroSound;

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, width, height);

        loadSounds();
        setupFactories();
        
        robotChicken = new RobotChicken();
        robotChicken.setTranslateX(-35);
        robotChicken.setTranslateY(height * 0.61);

        elTexto = new Text();
        elTexto.setFont(Font.font(36));
        elTexto.setX(width * 0.3);
        elTexto.setY(height * 0.9);
        elTexto.setFill(Color.BLACK);

        encouragmentText = new Text();
        encouragmentText.setFont(Font.font(24));
        encouragmentText.setX(width * 0.5 - 190);
        encouragmentText.setY(30);

        root.getChildren().addAll(robotChicken, elTexto, encouragmentText);

        scene.setOnKeyPressed(ke -> {
            if (ke.getCode() == KeyCode.SPACE) {
                input = "";
                misses++;
            } else if (ke.getText() != null && !ke.getText().isEmpty()) {
                input += ke.getText().toUpperCase();
            }
            elTexto.setText(input);
            checkTargets();
        });

        loadTargets();
        updateEncouragmentText();

        primaryStage.setTitle("onebeartoe.net - Type O Rama");
        primaryStage.setScene(scene);
        primaryStage.show();

        playIntro();
    }

    private void loadSounds() {
        lineBeamSound =     new Media(getClass().getResource("/net/onebeartoe/type/areli/sounds/line-beam-b.wav").toExternalForm());
//        lineBeamSound =     new Media(getClass().getResource("/net/onebeartoe/type/areli/sounds/line-beam-b.au").toExternalForm());
        
        removeTargetSound = new Media(getClass().getResource("/net/onebeartoe/type/areli/sounds/line-beam-remove.wav").toExternalForm());
//        removeTargetSound = new Media(getClass().getResource("/net/onebeartoe/type/areli/sounds/line-beam-remove.au").toExternalForm());
        
        levelIntroSound =   new Media(getClass().getResource("/net/onebeartoe/type/areli/sounds/audio.1280453989123.wav").toExternalForm());
//        levelIntroSound =   new Media(getClass().getResource("/net/onebeartoe/type/areli/sounds/audio.1280453989123.au").toExternalForm());
    }

    private void setupFactories() {
        double targetMaxX = width * 0.95;
        double targetMaxY = cannonTipY * 0.95;
        double targetMinY = height * 0.05;

        WordTargetFactory factoryA = new StaticWordTargetFactory();
        factoryA.setXRange(width);
        factoryA.setTargetMaxX(targetMaxX);
        factoryA.setTargetMaxY(targetMaxY);
        factoryA.setTargetMinY(targetMinY);

        WordTargetFactory factoryB = new VerticalWordTargetFactory();
        factoryB.setXRange(width);
        factoryB.setTargetMaxX(targetMaxX);
        factoryB.setTargetMaxY(targetMaxY);
        factoryB.setTargetMinY(targetMinY);

        WordTargetFactory factoryC = new DiagnalWordTargetFactory();
        factoryC.setXRange(width);
        factoryC.setTargetMaxX(targetMaxX);
        factoryC.setTargetMaxY(targetMaxY);
        factoryC.setTargetMinY(targetMinY);

        Collections.addAll(wordTargetServicePool, factoryA, factoryB, factoryC);
    }

    private void loadTargets() {
        String[] words = wordsService.getWords(currentRound * wordsPerRoundFactor);
        
        // Rotate factory pool
        WordTargetFactory factory = wordTargetServicePool.remove(0);
        wordTargetServicePool.add(factory);

        WordTarget[] targets = factory.createTargets(words);
        for (WordTarget target : targets) {
            wordTargets.add(target);
            root.getChildren().add(target);
        }
    }

    private void updateEncouragmentText() {
        encouragmentText.setText("Only " + wordTargets.size() + " more to go, in Round " + currentRound + "!");
    }

    private void checkTargets() {
        WordTarget matchingTarget = null;
        for (WordTarget target : wordTargets) {
            if (target.getLabelText().equals(input)) {
                matchingTarget = target;
                break;
            }
        }

        if (matchingTarget != null) {
            input = "";
            elTexto.setText("");
            matchingTarget.getAnimation().stop();
            
            new MediaPlayer(lineBeamSound).play();

            LineBeam beam = new LineBeam();
            beam.setStartX(cannonTipX);
            beam.setStartY(cannonTipY);
            beam.setEndX(matchingTarget.getTranslateX());
            beam.setEndY(matchingTarget.getTranslateY());
            
            attacks.add(beam);
            root.getChildren().add(beam);

            final WordTarget targetToRemove = matchingTarget;
            // In JavaFX Script, removeFrame.action was used. 
            // Here we can use a setOnFinished on the beam animation if it has a fixed duration.
            // Or use a PauseTransition.
            beam.getAnimation().setOnFinished(e -> {
                new MediaPlayer(removeTargetSound).play();
                root.getChildren().remove(targetToRemove);
                wordTargets.remove(targetToRemove);
                root.getChildren().remove(beam);
                attacks.remove(beam);

                if (wordTargets.isEmpty()) {
                    endRound();
                } else {
                    updateEncouragmentText();
                }
            });
        }
    }

    private void endRound() {
        Round r = new Round();
        r.setMisses(misses);
        r.setWords(currentRound * wordsPerRoundFactor);
        gameSummaries.add(r);

        misses = 0;
        currentRound++;

        if (currentRound > totalRounds) {
            System.out.println("Game Over!");
        } else {
            loadTargets();
            updateEncouragmentText();
            playIntro();
        }
    }

    private void playIntro() {
        new MediaPlayer(levelIntroSound).play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
