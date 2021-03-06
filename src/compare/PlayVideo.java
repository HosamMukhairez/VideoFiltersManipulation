/*
 * SMP.java
 *
 * Created on October 1, 2006, 12:40 AM
 */
package compare;

import com.sun.glass.ui.Pixels.Format;
import com.sun.media.jfxmedia.control.VideoFormat;
import java.awt.*;
import javax.media.*;
import javax.swing.*;
import java.awt.event.*;
import javax.media.Format.*;
import javax.media.control.FramePositioningControl.*;
import java.net.URL.*;
import java.net.*;
import java.io.*;
import javax.media.protocol.*;
import javax.media.format.AudioFormat.*;
import javax.media.format.VideoFormat.*;
import javax.media.format.*;
import javax.media.control.TrackControl.*;
import javax.media.Format;
import javax.media.control.*;
import javax.media.cdm.CaptureDeviceManager.*;
import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;
import java.util.*;
import javax.media.util.*;
import java.awt.Graphics;
import java.nio.Buffer;
import java.sql.Time;
import java.time.Duration;
import javax.activation.DataSource;
import javax.annotation.processing.Processor;
import org.omg.IOP.Codec;

/**
 *
 * @author  wael
 */
public class PlayVideo extends javax.swing.JFrame implements javax.media.ControllerListener {

    private Player player;
    private Component visual = null;
    private Component control = null;
    Format[] formats = new Format[2];
    private int videoWidth = 0;
    private int videoHeight = 0;
    private int controlHeight = 30;
    private int insetWidth = 10;
    private int insetHeight = 30;
    private boolean start, loop, teststopat = false, fixed = false;
    private UIManager.LookAndFeelInfo looks[];
    FramePositioningControl fpc;
    int totalFrames = FramePositioningControl.FRAME_UNKNOWN;
    MediaLocator captureLocation = null;
    MediaLocator destinationLocation;
    String destinationName = null;
    ContentDescriptor audioContainer = new ContentDescriptor(FileTypeDescriptor.WAVE);
    ContentDescriptor videoContainer = new ContentDescriptor(FileTypeDescriptor.MSVIDEO);
    ContentDescriptor container = null;
    double duration = 10;
    int waitFor = 0;
    boolean useKnownDevices = false;
    JFileChooser fc, fc1, fc2;
    File f, f1, f2;
    String file;

    /** Creates new form SMP */
    public PlayVideo() {
        looks = UIManager.getInstalledLookAndFeels();
        changeTheLookAndFeel(2);
        initComponents();
        setLocation(200, 200);
        setSize(500, 500);
    }

    public String openFolder() {
        String ret = null;
        int returnVal = 0;
        try {
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            returnVal = fc.showDialog(this, "إستعلام"); //to show JFileChooser
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                return ret = fc.getSelectedFile().getPath();
            } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            }
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
        }
        return ret;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();
        View = new javax.swing.JMenu();
        Frames = new javax.swing.JMenuItem();
        compareImages = new javax.swing.JMenu();
        compare = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Complex Media Player");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuBar1.setToolTipText("");
        jMenuBar1.setFont(new java.awt.Font("Tahoma", 1, 12));

        File.setMnemonic('f');
        File.setText("File");
        File.setToolTipText("File Menu");
        File.setAutoscrolls(true);
        File.setFocusCycleRoot(true);
        File.setFocusPainted(true);
        File.setFocusTraversalPolicyProvider(true);
        File.setFont(new java.awt.Font("Tahoma", 1, 12));
        File.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        File.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        File.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileActionPerformed(evt);
            }
        });

        Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        Open.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Open.setText("Open");
        Open.setToolTipText("Open File");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });
        File.add(Open);

        Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        Exit.setFont(new java.awt.Font("Tahoma", 1, 12));
        Exit.setText("Exit");
        Exit.setToolTipText("Exit SMP");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        File.add(Exit);

        jMenuBar1.add(File);

        View.setText("View");
        View.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        View.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewActionPerformed(evt);
            }
        });

        Frames.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Frames.setText("Break Video to frames");
        Frames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FramesActionPerformed(evt);
            }
        });
        View.add(Frames);

        jMenuBar1.add(View);

        compareImages.setText("Compare Images");
        compareImages.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        compare.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        compare.setText("Compare two images");
        compare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compareActionPerformed(evt);
            }
        });
        compareImages.add(compare);

        jMenuBar1.add(compareImages);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FramesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FramesActionPerformed
        try {
            String url = file;
            if (url == null) {
                url = openFolder();
                if (url != null) {
                    Play(url);
                }
            }
            if (url.indexOf(":") < 0) {
                System.exit(0);
            }
            MediaLocator ml;
            if ((ml = new MediaLocator(url)) == null) {
                System.err.println("Cannot build media locator from: " + url);
                System.exit(0);
            }
            FrameAccess fa = new FrameAccess();
            if (!fa.open(ml)) {
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_FramesActionPerformed

    private void ViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewActionPerformed

    private void FileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileActionPerformed
    }//GEN-LAST:event_FileActionPerformed

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
        file = GetFile();
        if (file != null) {
            Play(file);
        }
    }//GEN-LAST:event_OpenActionPerformed
    ////////////////////////////////////////////////////////////////

    public void controllerUpdate(ControllerEvent ce) {
        try {
            if (ce instanceof RealizeCompleteEvent) {
                GetFramInfo();
                player.prefetch();
            } else if (ce instanceof PrefetchCompleteEvent) {
                if (visual != null) {
                    return;
                }
                if ((visual = player.getVisualComponent()) != null) {
                    visual.validate();
                    Dimension size = visual.getPreferredSize();
                    if (fixed) {
                        videoWidth = 300;
                        videoHeight = 300;
                        getContentPane().add("Center", visual);
                    } else {
                        videoWidth = size.width;
                        videoHeight = size.height;
                        getContentPane().add("Center", visual);
                    }
                } else {
                    videoWidth = 300;
                    videoHeight = 0;
                }
                if ((control = player.getControlPanelComponent()) != null) {
                    control.repaint();
                    control.validate();
                    controlHeight = control.getPreferredSize().height;
                    getContentPane().add("South", control);
                }
                setSize(videoWidth + insetWidth, videoHeight + controlHeight + insetHeight + 30);
                validate();
                if (start) {
                    player.start();
                }

                if (teststopat == true) {
                    player.stop();
                } else {
                    player.stop();
                }

            } else if (ce instanceof EndOfMediaEvent) {
                if (loop) {
                    player.start();
                }
                /////////////////////////////////////////////
                player.setMediaTime(new Time(0));
                player.stop();
            }
        } catch (Exception e) {
        }
    }

    /******************************************************
     * Play Method
     * @param path path of the file
     ******************************************************/
    public void Play(String path) {
        try {
            if (player == null) {
                String f = path;
                URL url = new URL(f);
                player = Manager.createPlayer(url);
                player.addControllerListener((ControllerListener) this);
                player.realize();
            } else {
                player.close();
                getContentPane().remove(control);
                if (visual != null) {
                    getContentPane().remove(visual);
                }
                initComponents();
                getContentPane().add(player.getControlPanelComponent(), BorderLayout.SOUTH);
                this.setSize(300, 300);
                validate();
                String t = path;
                URL url = new URL(t);
                player = Manager.createPlayer(url);
                player.addControllerListener((ControllerListener) this);
                player.realize();

            }
            File.setMenuLocation(30, -1);
            File.setEnabled(true);
            View.setMenuLocation(30, -1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /*********************************************************
     * Get File
     * @return an<code>String</code>
     ********************************************************/
    public String GetFile() {
        String ret = null;
        FileDialog fd = new FileDialog(this, "Open File", FileDialog.LOAD);
        fd.setDirectory("/movies");
        fd.show();
        if (fd.getFile() != null) {
            ret = fd.getDirectory() + fd.getFile();
            ret = "file:" + ret;
        }
        return ret;
    }

    /******************************************************
     * changeTheLookAndFeel Method
     * @param value int value
     ******************************************************/
    public void changeTheLookAndFeel(int value) {
        try {
            UIManager.setLookAndFeel(looks[value].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    void GetFramInfo() {
        fpc = (FramePositioningControl) player.getControl("javax.media.control.FramePositioningControl");
        if (fpc == null) {
            // JOptionPane.showMessageDialog(this,"The player does not support FramePositioningControl.");
            System.err.println("There's no reason to go on for the purpose of this demo.");
            return;
        }

        Time duration = player.getDuration();
        if (duration != Duration.DURATION_UNKNOWN) {
            System.err.println("Movie duration: " + duration.getSeconds());
            totalFrames = fpc.mapTimeToFrame(duration);
            if (totalFrames != FramePositioningControl.FRAME_UNKNOWN) {
                System.err.println("Total # of video frames in the movies: " + totalFrames);
            } else {
                System.err.println("The FramePositiongControl does not support mapTimeToFrame.");
            }
        } else {
            System.err.println("Movie duration: unknown");
        }
    }

   private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed

       System.exit(0);
       player.close();

   }//GEN-LAST:event_ExitActionPerformed

   private void compareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compareActionPerformed
       // TODO add your handling code here:
       try {
           DetectImages d = new DetectImages();
           d.show();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }//GEN-LAST:event_compareActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new PlayVideo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenu File;
    private javax.swing.JMenuItem Frames;
    private javax.swing.JMenuItem Open;
    private javax.swing.JMenu View;
    private javax.swing.JMenuItem compare;
    private javax.swing.JMenu compareImages;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

    ///////////////////////////////////////////////////////////////////////////
    class Clone extends JFrame implements ControllerListener {

        Player p;
        Object waitSync = new Object();
        boolean stateTransitionOK = true;

        public Clone() {

            super("CLone Window");
            setSize(300, 300);
            setLocation(300, 300);
            WindowListener l = new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                    p.close();
                    //System.exit(0);
                    dispose();
                    hide();
                    p.close();
                }
            };
            addWindowListener(l);

        }

        /**
         * Given a DataSource, create a player and use that player
         * as a player to playback the media.
         */
        public boolean open(DataSource ds) {

            System.err.println("create player for: " + ds.getContentType());

            try {
                p = Manager.createPlayer(ds);
            } catch (Exception e) {
                System.err.println("Failed to create a player from the given DataSource: " + e);
                return false;
            }

            p.addControllerListener(this);

            // Realize the player.
            p.prefetch();

            if (!waitForState(p.Prefetched)) {
                System.err.println("Failed to realize the player.");
                return false;
            }



            setLayout(new BorderLayout());


            return true;
        }

        public void addNotify() {
            super.addNotify();
            pack();
        }

        /**
         * Block until the player has transitioned to the given state.
         * Return false if the transition failed.
         */
        boolean waitForState(int state) {
            synchronized (waitSync) {
                try {
                    while (p.getState() < state && stateTransitionOK) {
                        waitSync.wait();
                    }
                } catch (Exception e) {
                    System.out.println(e);

                }
            }
            return stateTransitionOK;
        }

        /**
         * Controller Listener.
         */
        public void controllerUpdate(ControllerEvent evt) {

            if (evt instanceof ConfigureCompleteEvent
                    || evt instanceof RealizeCompleteEvent
                    || evt instanceof PrefetchCompleteEvent) {

                synchronized (waitSync) {
                    stateTransitionOK = true;



                    Component cc;

                    Component vc;
                    if ((vc = p.getVisualComponent()) != null) {
                        add("Center", vc);
                    }

                    if ((cc = p.getControlPanelComponent()) != null) {
                        add("South", cc);
                    }
                    //Start the player.

                    p.start();
                    setVisible(true);


                    waitSync.notifyAll();
                }
            } else if (evt instanceof ResourceUnavailableEvent) {
                synchronized (waitSync) {
                    stateTransitionOK = false;
                    waitSync.notifyAll();
                }
            } else if (evt instanceof EndOfMediaEvent) {
                p.close();
                return;
            }

        }
    }

    class FrameAccess implements ControllerListener {

        Processor p;
        Object waitSync = new Object();
        boolean stateTransitionOK = true;
        public boolean alreadyPrnt = false;

        public boolean open(MediaLocator ml) {
            try {
                p = Manager.createProcessor(ml);
            } catch (Exception e) {
                System.err.println("Failed to create a processor from the given url: " + e);
                return false;
            }

            p.addControllerListener(this);

            // Put the Processor into configured state.
            p.configure();
            if (!waitForState(Processor.Configured)) {
                System.err.println("Failed to configure the processor.");
                return false;
            }

            // So I can use it as a player.
            p.setContentDescriptor(null);

            // Obtain the track controls.
            TrackControl tc[] = p.getTrackControls();

            if (tc == null) {
                System.err.println("Failed to obtain track controls from the processor.");
                return false;
            }

            // Search for the track control for the video track.
            TrackControl videoTrack = null;

            for (int i = 0; i < tc.length; i++) {
                if (tc[i].getFormat() instanceof VideoFormat) {
                    videoTrack = tc[i];
                } else {
                    tc[i].setEnabled(false);
                }
            }

            if (videoTrack == null) {
                System.err.println("The input media does not contain a video track.");
                return false;
            }
            String videoFormat = videoTrack.getFormat().toString();
            Dimension videoSize = parseVideoSize(videoFormat);
            System.err.println("Video format: " + videoFormat);

            // Instantiate and set the frame access codec to the data flow path.
            try {
                Codec codec[] = {new PostAccessCodec(videoSize)};
                videoTrack.setCodecChain(codec);
            } catch (UnsupportedPlugInException e) {
                System.err.println("The process does not support effects.");
            }

            // Realize the processor.
            p.prefetch();
            if (!waitForState(Processor.Prefetched)) {
                System.err.println("Failed to realise the processor.");
                return false;
            }

            p.start();
            return true;
        }

        /**parse the size of the video from the string videoformat*/
        public Dimension parseVideoSize(String videoSize) {
            int x = 300, y = 200;
            StringTokenizer strtok = new StringTokenizer(videoSize, ", ");
            strtok.nextToken();
            String size = strtok.nextToken();
            StringTokenizer sizeStrtok = new StringTokenizer(size, "x");
            try {
                x = Integer.parseInt(sizeStrtok.nextToken());
                y = Integer.parseInt(sizeStrtok.nextToken());
            } catch (NumberFormatException e) {
                System.out.println("unable to find video size, assuming default of 300x200");
            }
            System.out.println("Image width  = " + String.valueOf(x) + "\nImage height = " + String.valueOf(y));
            return new Dimension(x, y);
        }

        /**
         * Block until the processor has transitioned to the given state.
         * Return false if the transition failed.
         */
        boolean waitForState(int state) {
            synchronized (waitSync) {
                try {
                    while (p.getState() != state && stateTransitionOK) {
                        waitSync.wait();
                    }
                } catch (Exception e) {
                }
            }
            return stateTransitionOK;
        }

        /**
         * Controller Listener.
         */
        public void controllerUpdate(ControllerEvent evt) {

            if (evt instanceof ConfigureCompleteEvent
                    || evt instanceof RealizeCompleteEvent
                    || evt instanceof PrefetchCompleteEvent) {
                synchronized (waitSync) {
                    stateTransitionOK = true;
                    waitSync.notifyAll();
                }
            } else if (evt instanceof ResourceUnavailableEvent) {
                synchronized (waitSync) {
                    stateTransitionOK = false;
                    waitSync.notifyAll();
                }
            } else if (evt instanceof EndOfMediaEvent) {

                JOptionPane.showMessageDialog(null, "Movie successfully converteted to Picturs");

                p.close();
                return;




            }
        }

        class PreAccessCodec implements Codec {

            void accessFrame(Buffer frame) {

                // For demo, we'll just print out the frame #, time &
                // data length.

                long t = (long) (frame.getTimeStamp() / 10000000f);

                System.err.println(
                        "Pre: frame #: "
                        + frame.getSequenceNumber()
                        + ", time: "
                        + ((float) t) / 100f
                        + ", len: "
                        + frame.getLength());


            }
            // We'll advertize as supporting all video formats.
            protected Format supportedIns[] = new Format[]{new VideoFormat(null)};
            // We'll advertize as supporting all video formats.
            protected Format supportedOuts[] = new Format[]{new VideoFormat(null)};
            Format input = null, output = null;

            public String getName() {
                return "Pre-Access Codec";
            }

            //these dont do anything
            public void open() {
            }

            public void close() {
            }

            public void reset() {
            }

            public Format[] getSupportedInputFormats() {
                return supportedIns;
            }

            public Format[] getSupportedOutputFormats(Format in) {
                if (in == null) {
                    return supportedOuts;
                } else {
                    // If an input format is given, we use that input format
                    // as the output since we are not modifying the bit stream
                    // at all.
                    Format outs[] = new Format[1];
                    outs[0] = in;
                    return outs;
                }
            }

            public Format setInputFormat(Format format) {
                input = format;
                return input;
            }

            public Format setOutputFormat(Format format) {
                output = format;
                return output;
            }

            public int process(Buffer in, Buffer out) {

                // This is the "Callback" to access individual frames.
                accessFrame(in);

                // Swap the data between the input & output.
                Object data = in.getData();
                in.setData(out.getData());
                out.setData(data);

                // Copy the input attributes to the output
                out.setFlags(Buffer.FLAG_NO_SYNC);
                out.setFormat(in.getFormat());
                out.setLength(in.getLength());
                out.setOffset(in.getOffset());

                return BUFFER_PROCESSED_OK;
            }

            public Object[] getControls() {
                return new Object[0];
            }

            public Object getControl(String type) {
                return null;
            }
        }

        class PostAccessCodec extends PreAccessCodec {

            public File picFile;
            // We'll advertize as supporting all video formats.

            public PostAccessCodec(Dimension size) {
                supportedIns = new Format[]{new RGBFormat()};
                this.size = size;
                fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int result = fc.showSaveDialog(null);


                if (result == JFileChooser.CANCEL_OPTION) {
                    return;
                }
                picFile = fc.getSelectedFile();
                if (picFile == null || picFile.getName().equals("")) {
                    JOptionPane.showMessageDialog(null, "Invalid file Name");
                }
            }

            /**
             * Callback to access individual video frames.
             */
            void accessFrame(Buffer frame) {

                // For demo, we'll just print out the frame #, time &
                // data length.
                if (!alreadyPrnt) {
                    BufferToImage stopBuffer = new BufferToImage((VideoFormat) frame.getFormat());
                    Image stopImage = stopBuffer.createImage(frame);
                    try {
                        BufferedImage outImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
                        Graphics og = outImage.getGraphics();
                        og.drawImage(stopImage, 0, 0, size.width, size.height, null);
                        //prepareImage(outImage,rheight,rheight, null);
                        Iterator writers = ImageIO.getImageWritersByFormatName("jpg");
                        ImageWriter writer = (ImageWriter) writers.next();

                        String file = picFile.getPath() + "";
                        //Once an ImageWriter has been obtained, its destination must be set to an ImageOutputStream:
                        File f = new File(file, frame.getSequenceNumber() + ".jpg");

                        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
                        writer.setOutput(ios);

                        //Finally, the image may be written to the output stream:
                        //BufferedImage bi;
                        //writer.write(imagebi);
                        writer.write(outImage);
                        ios.close();
                        long t = (long) (frame.getTimeStamp() / 10000000f);
                        System.err.println(
                                "Post: frame #: "
                                + frame.getSequenceNumber()
                                + ", time: "
                                + ((float) t) / 100f
                                + ", len: "
                                + frame.getLength());

                    } catch (IOException e) {
                        System.out.println("Error :" + e);
                    }
                }
                //alreadyPrnt = true;
            }

            public String getName() {
                return "Post-Access Codec";
            }
            private Dimension size;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////    
    ////////////////////////////////////////////////////////////////////////////////////
}
