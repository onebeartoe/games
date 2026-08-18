package net.onebeartoe.type.areli;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
import net.onebeartoe.type.areli.sounds.SoundManager;
import net.onebeartoe.type.areli.targets.WordTarget;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private Pane gamePane;

    @FXML
    private Label encouragementLabel;

    @FXML
    private Label typedInputLabel;

    @FXML
    private StackPane summaryOverlay;

    @FXML
    private Label summaryTitleLabel;

    @FXML
    private ListView<String> summaryListView;

    @FXML
    private Button nextRoundButton;

    @FXML
    private StackPane startOverlay;

    @FXML
    private Button startButton;

    private RobotChicken robotChicken;
    private final SoundManager soundManager = new SoundManager();
    private final WordsService wordsService = new SimpleWordService();

    private final List<WordTargetFactory> wordTargetServicePool = new ArrayList<>();
    private final ObservableList<WordTarget> wordTargets = FXCollections.observableArrayList();
    private final ObservableList<Attack> attacks = FXCollections.observableArrayList();
    private final List<Round> gameSummaries = new ArrayList<>();

    private final StringBuilder inputBuffer = new StringBuilder();

    private int currentRound = 1;
    private final int totalRounds = 5;
    private final int wordsPerRoundFactor = 3;
    private int misses = 0;
    private int roundMisses = 0;

    private final int arenaWidth = 900;
    private final int arenaHeight = 600;
    private final double cannonTipX = 141.0;
    private final double cannonTipY = 421.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupRobotChicken();
        setupFactories();
    }

    private void setupRobotChicken() {
        robotChicken = new RobotChicken();
        robotChicken.setTranslateX(-35);
        robotChicken.setTranslateY(arenaHeight * 0.61);
        gamePane.getChildren().add(robotChicken);
    }

    private void setupFactories() {
        double targetMaxX = arenaWidth - 180;
        double targetMaxY = cannonTipY * 0.75;
        double targetMinY = 60.0;
        double targetMinX = 50.0;

        WordTargetFactory factoryA = new StaticWordTargetFactory();
        factoryA.setXRange(arenaWidth);
        factoryA.setTargetMinX(targetMinX);
        factoryA.setTargetMaxX(targetMaxX);
        factoryA.setTargetMinY(targetMinY);
        factoryA.setTargetMaxY(targetMaxY);

        WordTargetFactory factoryB = new VerticalWordTargetFactory();
        factoryB.setXRange(arenaWidth);
        factoryB.setTargetMinX(targetMinX);
        factoryB.setTargetMaxX(targetMaxX);
        factoryB.setTargetMinY(targetMinY);
        factoryB.setTargetMaxY(targetMaxY);

        WordTargetFactory factoryC = new DiagnalWordTargetFactory();
        factoryC.setXRange(arenaWidth);
        factoryC.setTargetMinX(targetMinX);
        factoryC.setTargetMaxX(targetMaxX);
        factoryC.setTargetMinY(targetMinY);
        factoryC.setTargetMaxY(targetMaxY);

        Collections.addAll(wordTargetServicePool, factoryA, factoryB, factoryC);
    }

    @FXML
    private void onStartGameClicked(ActionEvent event) {
        startOverlay.setVisible(false);
        rootPane.requestFocus();
        startRound();
    }

    private void startRound() {
        clearTargetsAndBeams();
        loadTargets();
        updateEncouragementText();
        soundManager.playIntro();
        rootPane.requestFocus();
    }

    private void loadTargets() {
        int wordCount = currentRound * wordsPerRoundFactor;
        String[] words = wordsService.getWords(wordCount);

        WordTargetFactory factory = wordTargetServicePool.remove(0);
        wordTargetServicePool.add(factory);

        WordTarget[] targets = factory.createTargets(words);
        for (WordTarget target : targets) {
            wordTargets.add(target);
            gamePane.getChildren().add(target);
        }
    }

    private void clearTargetsAndBeams() {
        for (WordTarget target : wordTargets) {
            target.stopAnimation();
            gamePane.getChildren().remove(target);
        }
        wordTargets.clear();

        for (Attack attack : attacks) {
            gamePane.getChildren().remove(attack);
        }
        attacks.clear();
    }

    private void updateEncouragementText() {
        encouragementLabel.setText("Only " + wordTargets.size() + " more to go, in Round " + currentRound + "!");
    }

    @FXML
    public void handleKeyPressed(KeyEvent event) {
        if (summaryOverlay.isVisible() || startOverlay.isVisible()) {
            return;
        }

        if (event.getCode() == KeyCode.SPACE) {
            inputBuffer.setLength(0);
            typedInputLabel.setText("");
            misses++;
            roundMisses++;
            return;
        }

        if (event.getCode() == KeyCode.BACK_SPACE) {
            if (inputBuffer.length() > 0) {
                inputBuffer.deleteCharAt(inputBuffer.length() - 1);
                typedInputLabel.setText(inputBuffer.toString());
            }
            return;
        }

        String text = event.getText();
        if (text != null && !text.isEmpty() && Character.isLetterOrDigit(text.charAt(0))) {
            inputBuffer.append(text.toUpperCase());
            typedInputLabel.setText(inputBuffer.toString());
            checkTargets();
        }
    }

    private void checkTargets() {
        String currentInput = inputBuffer.toString();
        WordTarget matchedTarget = null;

        for (WordTarget target : wordTargets) {
            if (target.getLabelText().equalsIgnoreCase(currentInput)) {
                matchedTarget = target;
                break;
            }
        }

        if (matchedTarget != null) {
            inputBuffer.setLength(0);
            typedInputLabel.setText("");

            matchedTarget.stopAnimation();
            matchedTarget.setEffect(new Glow(1.0));

            soundManager.playLineBeam();

            double targetCenterX = matchedTarget.getTranslateX() + 70.0;
            double targetCenterY = matchedTarget.getTranslateY() + 45.0;

            LineBeam beam = new LineBeam(cannonTipX, cannonTipY, targetCenterX, targetCenterY);
            attacks.add(beam);
            gamePane.getChildren().add(beam);

            final WordTarget targetToRemove = matchedTarget;
            beam.fire(e -> {
                soundManager.playRemoveTarget();

                gamePane.getChildren().remove(targetToRemove);
                wordTargets.remove(targetToRemove);

                gamePane.getChildren().remove(beam);
                attacks.remove(beam);

                if (wordTargets.isEmpty()) {
                    endRound();
                } else {
                    updateEncouragementText();
                }
            });
        }
    }

    private void endRound() {
        int wordsInRound = currentRound * wordsPerRoundFactor;
        Round round = new Round(wordsInRound, misses);
        gameSummaries.add(round);

        roundMisses = 0;
        misses = 0;
        currentRound++;

        updateSummaryDialog();
        summaryOverlay.setVisible(true);
    }

    private void updateSummaryDialog() {
        summaryListView.getItems().clear();
        for (int i = 0; i < gameSummaries.size(); i++) {
            Round r = gameSummaries.get(i);
            String item = String.format("Round %d: Words: %d | Misses: %d | Hit Ratio: %.1f%%",
                i + 1, r.getWords(), r.getMisses(), r.getHitRatio());
            summaryListView.getItems().add(item);
        }

        if (currentRound > totalRounds) {
            summaryTitleLabel.setText("Game Complete! Excellent Typing!");
            nextRoundButton.setText("Play Again!");
        } else {
            summaryTitleLabel.setText("Round " + (currentRound - 1) + " Complete!");
            nextRoundButton.setText("Next Round");
        }
    }

    @FXML
    private void onNextRoundClicked(ActionEvent event) {
        if (currentRound > totalRounds) {
            currentRound = 1;
            gameSummaries.clear();
            nextRoundButton.setText("Next Round");
        }

        summaryOverlay.setVisible(false);
        startRound();
    }
}
