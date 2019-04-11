/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * @author eng
 */
public class StartComparing extends JApplet {

    Compare2Images c;
    static String image1, image2;

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
        try {
            this.setSize(720, 700);
            this.setBackground(Color.WHITE);
            this.setLocation(250, 250);
            System.out.println("images name init:" + getImage1Name() + "-" + getImage2Name());
            getContentPane().add(c = new Compare2Images(getImage1Name(),getImage2Name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void setImagesName(String img1, String img2) {
        try {
            image1 = img1;
            image2 = img2;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getImage1Name(){
        return image1;
    }
    public String getImage2Name(){
        return image2;
    }

    public static void main(String args[]) {
        try {
            final StartComparing ops = new StartComparing();
            System.out.println("images name :main");
            setImagesName(args[0],args[1]);
            ops.init();
            JFrame f = new JFrame("Video Manipulation");
            f.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            f.getContentPane().add("Center", ops);
            f.pack();
            f.setSize(new Dimension(500, 500));
            f.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
