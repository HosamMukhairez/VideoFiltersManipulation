/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author eng
 */
public class Compare2Images extends javax.swing.JPanel {

    int WIDTH1 = 0, HEIGHT1 = 0, WIDTH2 = 0, HEIGHT2 = 0; // Size of our example
    Image image1, image2;
    Graphics2D g2D;
    BufferedImage bi1, bi2, bi3;
    Graphics2D big1, big2, big3;
    int x, y;
    MediaTracker tracker;

    public Compare2Images(String imgName1, String imgName2) {
        try {
            System.out.println("images name Compare2Images:" + imgName1 + "-" + imgName2);
            image1 = Toolkit.getDefaultToolkit().getImage(imgName1);
            image2 = Toolkit.getDefaultToolkit().getImage(imgName2);

            tracker = new MediaTracker(this);
            tracker.addImage(image1, 0);
            tracker.addImage(image2, 1);
            tracker.waitForAll();

            setHieghtImage1();
            setWidthImage1();
            setHieghtImage2();
            setWidthImage2();

            bi1 = new BufferedImage(getWidth1() / 10, getHeight1() / 10, BufferedImage.TYPE_INT_RGB);//1st image
            bi2 = new BufferedImage(getWidth2() / 10, getHeight2() / 10, BufferedImage.TYPE_INT_RGB);//2nd image
            bi3 = new BufferedImage(getWidth1() / 10, getHeight1() / 10, BufferedImage.TYPE_INT_RGB);//the result image

            big1 = bi1.createGraphics();
            big2 = bi2.createGraphics();
            big3 = bi3.createGraphics();

            big1.drawImage(image1, 0, 0, this);
            big2.drawImage(image2, 0, 0, this);
            big3.drawImage(image1, 0, 0, this);
            tracker.waitForAll();

            if (!big1.equals(big2)) {
                for (int i = 0; i < image1.getWidth(null); i++) {
                    for (int j = 0; j < image1.getHeight(null); j++) {
                        int p1 = bi1.getRGB(i, j);//getting pixel's color for image1
                        int p2 = bi2.getRGB(i, j);//getting pixel's color for image2

                        if (p1 != p2) {
                            System.out.println("not equal  " + "p1:" + p1 + "   p2:" + p2 + "    i:" + i + "   j:" + j);
                            bi3.setRGB(i, j, 1);
                        }
                    }
                }
                ImageIO.write(bi3, "jpg", new File("modified.jpg"));//save image on pc
            } else {
            }
            repaint();
            revalidate();
            //end
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    //public void paintComponent(Graphics g) {
    public void paint(Graphics g) {
        super.paint(g);
        g2D = (Graphics2D) g;
        g2D.drawImage(bi1, 0, 0, this);
        g2D.drawString("Original", 0, image1.getHeight(this) + 20); // Label the image1
        g2D.translate(image1.getWidth(null) + 10, 0); // Move over
        g2D.drawImage(bi2, 0, 0, this);
        g2D.drawString("Second", 0, image2.getHeight(this) + 20); // Label the image2
        g2D.translate(image1.getWidth(null) + 10, 0); // Move over
        g2D.drawImage(bi3, 0, 0, this);
        g2D.drawString("Modified", 0, image1.getHeight(this) + 20); // Label the result image
        repaint();
        revalidate();
    }

    public int getWidth1() {
        return WIDTH1;
    }

    public int getHeight1() {
        return HEIGHT1;
    }

    public int getWidth2() {
        return WIDTH2;
    }

    public int getHeight2() {
        return HEIGHT2;
    }

    public void setHieghtImage1() {
        HEIGHT1 = (image1.getHeight(null))*10;
        System.out.println("HEIGHT1:" + HEIGHT1);
    }

    public void setWidthImage1() {
        WIDTH1 = (image1.getWidth(null))*10;
        System.out.println("WIDTH2:" + WIDTH1);
    }

    public void setHieghtImage2() {
        HEIGHT2 = (image2.getHeight(null)) * 10;
        System.out.println("HEIGHT2:" + HEIGHT2);
    }

    public void setWidthImage2() {
        WIDTH2 = (image2.getWidth(null)) * 10;
        System.out.println("WIDTH2:" + WIDTH2);
    }
}


