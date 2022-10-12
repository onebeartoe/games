package edu.umbc.cs.sli2;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Event;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class CubeAbout extends JFrame {
    JButton OK = new JButton(" O K ");
    JLabel Caption = new JLabel("Virtual Cube   V1.2", 1);
    JLabel Space = new JLabel("                          ");
    JLabel Author = new JLabel("(C) Song Li, 1996", 1);
    JLabel Address = new JLabel("www.cs.umbc.edu/~sli2", 1);
    JLabel Space2 = new JLabel("                          ");
    JLabel Author2 = new JLabel("Portion of Code By David W. Liu", 1);
    JLabel Address2 = new JLabel("reality.sgi.com/employees/davidliu_mti", 1);
    JLabel Space3 = new JLabel("                          ");
    JLabel Hotkey = new JLabel("To View: Drag your mouse.", 0);
    JLabel Hotkey2 = new JLabel("To Spin: Use mouse's right button, or", 0);
    JLabel Hotkey3 = new JLabel("Drag your mouse with CTRL down.", 0);
    JLabel Space4 = new JLabel("                          ");

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
