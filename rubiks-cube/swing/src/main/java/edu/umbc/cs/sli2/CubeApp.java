
package edu.umbc.cs.sli2;

import java.awt.Container;
import java.awt.Event;
import java.awt.LayoutManager;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Source code recreated from a .class file by IntelliJ IDEA
 * (powered by FernFlower decompiler)
 * and modified by Roberto Marquez
 */
public class CubeApp extends JApplet
{
    private CubePanel CubeWin;
    private JButton NewButton;
    private JButton ScrambleButton;
    private JButton SolveButton;
    private JButton AboutButton;
    private JButton UndoButton;
    private JButton RedoButton;
    private JCheckBox SoundCheckbox;
    private JCheckBox LightCheckbox;
    private JLabel StepLabel;
    private JLabel StepText;
    
    final int width = 600;
    
    final int height = 600;
    
    @Override
    public void stop() {
        this.CubeWin.Stop();
    }

    @Override
    public void start() {
        this.CubeWin.Start();
    }

    @Override
    public void init() 
//    public JPanel init() 
    {
        this.resize(this.size().width <= 420 ? 420 : this.size().width, this.size().height <= 200 ? 200 : this.size().height);
        this.setLayout((LayoutManager)null);
        JPanel control = new JPanel();
        control.setLayout((LayoutManager)null);
        this.add(control);
        control.reshape(0, 0, 200, this.size().height);
        this.NewButton = new JButton("New Game");
        this.ScrambleButton = new JButton("Scramble");
        this.SolveButton = new JButton("Solve");
        this.AboutButton = new JButton("About");
        this.UndoButton = new JButton("Undo");
        this.RedoButton = new JButton("Redo");
        this.SoundCheckbox = new JCheckBox("Sound");
        this.LightCheckbox = new JCheckBox("Light");
        this.StepLabel = new JLabel("Step");
        this.StepText = new JLabel("0");
        this.SoundCheckbox.setSelected(true);
        this.LightCheckbox.setSelected(false);
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
        
//TODO: re-enable these sounds        
//        CubePanel.BadSound = this.getAudioClip(this.getCodeBase(), "illegal.au");
//        CubePanel.SpinSound = this.getAudioClip(this.getCodeBase(), "spin.au");

//        return control;
    }

    @Override
    public synchronized boolean handleEvent(Event event) {
        if (event.id == 1001) {
            if (event.target == this.AboutButton) {
                CubeAbout about = new CubeAbout();
                about.show();
            } else if (event.target == this.LightCheckbox) {
                Face.SetLight(this.LightCheckbox.isSelected() );
            } else {
                this.CubeWin.CommandAction(event);
            }
        }

        return true;
    }

    public CubeApp()
    {
//        super( "HtmlUtility by onebeartoe.com" );
                
//        init();
//        JPanel content = init();
        
//        start();
        
//        Container contentPane = getContentPane();
        
        setSize(width, height);

//        contentPane.add(content);
        
//        addComponentListener(this);
        
        setVisible(true);
    }
    
    public static void main(String [] args) 
    {
        final CubeApp app = new CubeApp();
        
//        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
    }
}


/**
 *  Use the following command to run the applet, from the command line.
 * 
 * $ bappletviewer CubeApp.java
 */


/*
<applet code="edu.umbc.cs.sli2.CubeApp.class" width=600 height=600>
</applet>
*/