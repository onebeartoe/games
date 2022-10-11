package edu.umbc.cs.sli2;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import edu.umbc.cs.sli2.CubeAction;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.image.ImageObserver;

public class CubePanel extends Panel implements Runnable {
    private Thread CubeThread;
    private CubeSystem CubeSys = new CubeSystem(new Vertex(60.0F, 60.0F, 60.0F));
    private CubeStack SpinStack;
    private Button ButtonSolve;
    private boolean Automatic;
    public static AudioClip BadSound;
    public static AudioClip SpinSound;
    private boolean SoundEnabled = true;
    Image OffScreen;
    Graphics OffGraphics;
    Dimension OffScreenSize;
    private boolean Ready;
    int PrevX;
    int PrevY;
    Vertex PrevHotpoint;

    public void DefineBound() {
        this.CubeSys.DefineBound(this.size().width, this.size().height);
    }

    public void paint(Graphics graph) {
        this.update(graph);
    }

    public boolean mouseUp(Event event, int x, int y) {
        if (event.controlDown() || event.metaDown()) {
            if (this.PrevHotpoint == null) {
                return true;
            }

            Vertex hotpoint = this.CubeSys.Unproject((float)(x - this.size().width / 2), (float)(this.size().height / 2 - y));
            int spinparam = CubeSystem.ComputeSpin(this.PrevHotpoint, hotpoint);
            if (spinparam == 9999 && this.SoundEnabled) {
                BadSound.play();
                this.PrevHotpoint = null;
                return true;
            }

            int axis = spinparam > 0 ? (spinparam - 1) / 3 : (-spinparam - 1) / 3;
            int plane = spinparam > 0 ? (spinparam - 1) % 3 : (-spinparam - 1) % 3;
            boolean dir = spinparam > 0;
            if (this.SoundEnabled) {
                SpinSound.play();
            }

            this.CubeSys.SpinBegin(axis, plane, dir);
            this.SpinStack.Do(new CubeAction(axis, plane, dir));
            this.CubeThread.resume();
            this.PrevHotpoint = null;
        }

        return true;
    }

    public void Stop() {
        this.CubeThread.stop();
        this.CubeThread = null;
    }

    public boolean CommandAction(Event event) {
        if ("New Game".equals(event.arg)) {
            this.CubeSys.NewGame();
            this.SpinStack.Clear();
            this.repaint();
        }

        CubeAction action;
        if ("Undo".equals(event.arg)) {
            action = this.SpinStack.Undo();
            if (action != null) {
                this.CubeSys.SpinBegin(action.GetAxis(), action.GetPlane(), !action.GetDir());
                this.CubeThread.resume();
            }
        }

        if ("Redo".equals(event.arg)) {
            action = this.SpinStack.Redo();
            if (action != null) {
                this.CubeSys.SpinBegin(action.GetAxis(), action.GetPlane(), action.GetDir());
                this.CubeThread.resume();
            }
        }

        if ("Scramble".equals(event.arg)) {
            this.CubeSys.NewGame();
            this.SpinStack.Clear();
            int axis = -1;
            int plane = -1;
            boolean dir = false;
            this.SpinStack.EnableUpdate(false);
            int i = 0;

            do {
                int newplane;
                boolean newdir;
                int newaxis;
                do {
                    do {
                        do {
                            newaxis = (int)(Math.random() * 3.0);
                            newplane = (int)(Math.random() * 3.0);
                            newdir = Math.random() > 0.5;
                        } while(newaxis > 2);
                    } while(newplane > 2);
                } while(newaxis == axis && newplane == plane && newdir == !dir);

                axis = newaxis;
                plane = newplane;
                dir = newdir;
                this.SpinStack.Do(new CubeAction(newaxis, newplane, newdir));
                this.CubeSys.SpinMap(newaxis, newplane, newdir);
                ++i;
            } while(i < 60);

            this.SpinStack.SetWatermark();
            this.SpinStack.EnableUpdate(true);
            this.CubeSys.SetCubeMap();
            this.repaint();
        }

        if ("Stop".equals(event.arg)) {
            this.Automatic = false;
            this.ButtonSolve.setLabel("Solve");
        }

        if ("Solve".equals(event.arg)) {
            this.ButtonSolve = (Button)event.target;
            this.Automatic = true;
            this.ButtonSolve.setLabel("Stop");
            this.CubeThread.resume();
        }

        if (event.target instanceof Checkbox) {
            this.SoundEnabled = ((Checkbox)event.target).getState();
        }

        return true;
    }

    public synchronized void update(Graphics graph) {
        Dimension dim = this.size();
        if (this.OffScreen == null || dim.width != this.OffScreenSize.width || dim.height != this.OffScreenSize.height) {
            this.OffScreen = this.createImage(dim.width, dim.height);
            this.OffScreenSize = dim;
            this.OffGraphics = this.OffScreen.getGraphics();
            this.OffGraphics.setFont(this.getFont());
        }

        this.OffGraphics.setColor(Color.lightGray);
        this.OffGraphics.fillRect(0, 0, dim.width, dim.height);
        if (this.CubeSys != null) {
            this.CubeSys.Paint(this.OffGraphics, dim.width, dim.height);
            graph.drawImage(this.OffScreen, 0, 0, (ImageObserver)null);
        }

    }

    CubePanel(Label steptext) {
        this.SpinStack = new CubeStack(steptext);
        this.OffScreen = this.createImage(200, 200);
        if (this.OffScreen == null) {
            System.out.println("failed");
        } else {
            System.out.println("succeeded");
        }

    }

    public boolean mouseDown(Event event, int x, int y) {
        if (!event.controlDown() && !event.metaDown()) {
            this.PrevX = x;
            this.PrevY = y;
        } else {
            this.PrevHotpoint = this.CubeSys.Unproject((float)(x - this.size().width / 2), (float)(this.size().height / 2 - y));
            if (this.PrevHotpoint == null && this.SoundEnabled) {
                BadSound.play();
            }
        }

        return true;
    }

    public void run() {
        while(!this.Ready) {
        }

        for(; this.CubeThread != null; this.CubeThread.suspend()) {
            do {
                if (this.Automatic) {
                    CubeAction action = this.SpinStack.ForceUndo();
                    if (action == null) {
                        this.Automatic = false;
                        this.ButtonSolve.setLabel("Solve");
                        break;
                    }

                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException var4) {
                    }

                    this.CubeSys.SpinBegin(action.GetAxis(), action.GetPlane(), !action.GetDir());
                }

                int i = 0;

                do {
                    this.CubeSys.Spin(1);
                    this.repaint();

                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException var5) {
                    }

                    ++i;
                } while(i < 5);

                this.CubeSys.SpinEnd();
                this.repaint();
            } while(this.Automatic);
        }

        this.CubeThread = null;
    }

    public boolean mouseDrag(Event event, int x, int y) {
        if (!event.controlDown() && !event.metaDown()) {
            double xtheta = (double)((this.PrevY - y) * 2) * Math.PI / (double)this.size().width;
            double ytheta = (double)((this.PrevX - x) * 2) * Math.PI / (double)this.size().height;
            this.CubeSys.XRotateView(xtheta);
            this.CubeSys.YRotateView(ytheta);
            this.repaint();
            this.PrevX = x;
            this.PrevY = y;
            return true;
        } else {
            return true;
        }
    }

    public void Start() {
        if (this.CubeThread == null) {
            this.CubeThread = new Thread(this);
            this.CubeThread.start();
            this.CubeThread.suspend();
            this.Ready = true;
        }

    }
}
