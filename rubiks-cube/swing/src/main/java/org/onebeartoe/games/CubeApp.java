//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Event;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;

public class CubeApp extends Applet {
    private CubePanel CubeWin;
    private Button NewButton;
    private Button ScrambleButton;
    private Button SolveButton;
    private Button AboutButton;
    private Button UndoButton;
    private Button RedoButton;
    private Checkbox SoundCheckbox;
    private Checkbox LightCheckbox;
    private Label StepLabel;
    private Label StepText;

    public void stop() {
        this.CubeWin.Stop();
    }

    public CubeApp() {
    }

    public void start() {
        this.CubeWin.Start();
    }

    public void init() {
        this.resize(this.size().width <= 420 ? 420 : this.size().width, this.size().height <= 200 ? 200 : this.size().height);
        this.setLayout((LayoutManager)null);
        Panel control = new Panel();
        control.setLayout((LayoutManager)null);
        this.add(control);
        control.reshape(0, 0, 200, this.size().height);
        this.NewButton = new Button("New Game");
        this.ScrambleButton = new Button("Scramble");
        this.SolveButton = new Button("Solve");
        this.AboutButton = new Button("About");
        this.UndoButton = new Button("Undo");
        this.RedoButton = new Button("Redo");
        this.SoundCheckbox = new Checkbox("Sound");
        this.LightCheckbox = new Checkbox("Light");
        this.StepLabel = new Label("Step");
        this.StepText = new Label("0");
        this.SoundCheckbox.setState(true);
        this.LightCheckbox.setState(false);
        control.add(this.NewButton);
        control.add(this.ScrambleButton);
        control.add(this.SolveButton);
        control.add(this.AboutButton);
        control.add(this.UndoButton);
        control.add(this.RedoButton);
        control.add(this.SoundCheckbox);
        control.add(this.LightCheckbox);
        control.add(this.StepLabel);
        control.add(this.StepText);
        this.NewButton.reshape(10, 10, 80, 25);
        this.AboutButton.reshape(110, 10, 80, 25);
        this.ScrambleButton.reshape(10, 40, 80, 25);
        this.SolveButton.reshape(110, 40, 80, 25);
        this.UndoButton.reshape(10, 70, 80, 25);
        this.RedoButton.reshape(110, 70, 80, 25);
        this.StepLabel.reshape(10, 145, 35, 25);
        this.StepText.reshape(50, 145, 30, 25);
        this.SoundCheckbox.reshape(110, 130, 80, 25);
        this.LightCheckbox.reshape(110, 160, 80, 25);
        this.CubeWin = new CubePanel(this.StepText);
        this.CubeWin.setLayout((LayoutManager)null);
        this.add(this.CubeWin);
        this.CubeWin.reshape(220, 0, this.size().width - 220, this.size().height);
        this.CubeWin.DefineBound();
        CubePanel.BadSound = this.getAudioClip(this.getCodeBase(), "illegal.au");
        CubePanel.SpinSound = this.getAudioClip(this.getCodeBase(), "spin.au");
    }

    @Override
    public synchronized boolean handleEvent(Event event) {
        if (event.id == 1001) {
            if (event.target == this.AboutButton) {
                CubeAbout about = new CubeAbout();
                about.show();
            } else if (event.target == this.LightCheckbox) {
                Face.SetLight(this.LightCheckbox.getState());
            } else {
                this.CubeWin.CommandAction(event);
            }
        }

        return true;
    }
}

/**
 *  Use the following command to run the applet, from the command line.
 * 
 * $ bappletviewer CubeApp.java
 */


/*
<applet code="CubeApp.class" width=600 height=600>
</applet>
*/