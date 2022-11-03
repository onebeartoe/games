//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Button;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;

class CubeAbout extends Frame {
    Button OK = new Button(" O K ");
    Label Caption = new Label("Virtual Cube   V1.2", 1);
    Label Space = new Label("                          ");
    Label Author = new Label("(C) Song Li, 1996", 1);
    Label Address = new Label("www.cs.umbc.edu/~sli2", 1);
    Label Space2 = new Label("                          ");
    Label Author2 = new Label("Portion of Code By David W. Liu", 1);
    Label Address2 = new Label("reality.sgi.com/employees/davidliu_mti", 1);
    Label Space3 = new Label("                          ");
    Label Hotkey = new Label("To View: Drag your mouse.", 0);
    Label Hotkey2 = new Label("To Spin: Use mouse's right button, or", 0);
    Label Hotkey3 = new Label("Drag your mouse with CTRL down.", 0);
    Label Space4 = new Label("                          ");

    public CubeAbout() {
        super("Virtual Cube");
        this.resize(280, 350);
        this.setResizable(false);
        this.setLayout(new FlowLayout(1, 1000, 0));
        this.add(this.Caption);
        this.add(this.Space);
        this.add(this.Author);
        this.add(this.Address);
        this.add(this.Space2);
        this.add(this.Author2);
        this.add(this.Address2);
        this.add(this.Space3);
        this.add(this.Hotkey);
        this.add(this.Hotkey2);
        this.add(this.Hotkey3);
        this.add(this.Space4);
        this.add(this.OK);
    }

    public synchronized boolean handleEvent(Event event) {
        if (event.id != 1001) {
            return false;
        } else {
            if (" O K ".equals(event.arg)) {
                this.dispose();
            }

            return true;
        }
    }
}
