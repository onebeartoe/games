package org;


//package org.onebeartoe.games.centipedo;

/**
 * The code snippet in this video description helped me remember how to convert a
 * Java (J)Applet to an 'static void main(args)' application.
 * 
 *      https://www.youtube.com/watch?v=CKzzk32UkJA&ab_channel=RollerCoasterManiac
 * 
 * The notes at the bottom of this writeup helped remind me of (J)Applet's reliance 
 * on browser features; getCodeBase(), getAudioClip(), ect...
 * 
 * I did this with RandomJuke (Dr Brown's ((I think)) kaleidoscope did this a long time ago.
 */
import javax.swing.JFrame;

public class JWrapper 
{
    public static void main(String[] args)
    {
        System.out.println("Hello Centipedo World!");
        
        JFrame frame = new JFrame("Centipedo - Shiffman Rules!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        centipedo applet = new centipedo();

        frame.getContentPane().add(applet);

        applet.init();

        applet.start();

        frame.pack();

        frame.setSize(applet.w, applet.h);

//        frame.setResizable(false);

        frame.setVisible(true);
    }
}
