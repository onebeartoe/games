/**
 * The code snippet in this video description helped me remember how to convert a
 * Java (J)Applet to an 'static void main(args)' application.
 * 
 *      https://www.youtube.com/watch?v=CKzzk32UkJA&ab_channel=RollerCoasterManiac
 * 
 * I did this with RandomJuke (Dr Brown's ((I think)) kaleidoscope did this a long time ago.
 */


import javax.swing.JFrame;

//import CubeApp;



public class Wrapplet 
{
    public static void main(String[] args)
    {
        System.out.println("Hello Rubic World!");
        
        JFrame frame = new JFrame("New Sand");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         CubeApp sand = new CubeApp();
  frame.getContentPane().add(sand);
  //ADD TO THE CONTENT PANE!!!
  //i made a mistake
  sand.init();
  sand.start();
  frame.pack();
  /* Some applets do not allow packing the frame.
   * Others do, Its mostly trial and error.
   */
  frame.setResizable(false);
  frame.setVisible(true);
    }
}
