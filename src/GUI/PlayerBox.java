package GUI;

import Logic.RunMusic;
import Logic.Save;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.nio.file.Files;

public class PlayerBox extends JPanel {
    private SongInfo songInfo;
    private PlayerTools playerTools;
    private static String filePath;

    public PlayerTools getPlayerTools() {
        return playerTools;
    }

    public PlayerBox() throws IOException, InvalidDataException, UnsupportedTagException, JavaLayerException {
        setPreferredSize(new Dimension(1200, 70));
        setOpaque(true);
        setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        songInfo = new SongInfo(null);
//        songInfo=new SongInfo();
        playerTools = new PlayerTools(getSongInfo());
        VolumeBox volumeBox = new VolumeBox();


        add(songInfo, BorderLayout.WEST);
        add(playerTools, BorderLayout.CENTER);
        add(volumeBox, BorderLayout.EAST);

    }

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        PlayerBox.filePath = filePath;
    }
}

class VolumeBox extends JPanel {

    JSlider volumeSlider;
    JButton volumeButton;

    public VolumeBox() throws IOException {
        super();
        volumeSlider = new JSlider(0, 100, 25);
        volumeButton = new JButton();

        setPreferredSize(new Dimension(250, 70));
        setOpaque(true);
        setBackground(Color.DARK_GRAY);

        Image img = ImageIO.read(getClass().getResource("icons\\my-icons-collection-2\\png\\006-speaker-1.png"));
        img = img.getScaledInstance(14, 14, java.awt.Image.SCALE_SMOOTH);
        volumeButton.setIcon(new ImageIcon(img));
        volumeButton.setPreferredSize(new Dimension(40, 40));
        volumeButton.setBorderPainted(false);
        volumeButton.setContentAreaFilled(false);
        volumeButton.setFocusPainted(false);

        volumeSlider.setOpaque(true);
        volumeSlider.setBackground(Color.DARK_GRAY);
        volumeSlider.putClientProperty( "Slider.paintThumbArrowShape", Boolean.TRUE );


//        volumeSlider.setExtent(50);

        add(volumeButton);
        add(volumeSlider);



       /* javax.sound.sampled.Mixer.Info[] mixers = AudioSystem.getMixerInfo();

        Mixer.Info mixerInfo = mixers[4];
        Mixer mixer = AudioSystem.getMixer(mixerInfo);
        Line.Info[] lineinfos = mixer.getTargetLineInfo();

        try {
            Line line = mixer.getLine(lineinfos[0]);
            line.open();
            if(line.isControlSupported(FloatControl.Type.VOLUME)){
                control = (FloatControl) line.getControl(FloatControl.Type.VOLUME);

                control.setValue((float) 0.7);
                int value = (int) (control.getValue()*100);

                volumeSlider = new JSlider((int)control.getMinimum()*100,(int)control.getMaximum()*100, value);
                volumeSlider.setMajorTickSpacing(10);
                volumeSlider.setPaintLabels(true);
                System.out.println("Volume:"+control.getValue());

                j.add(slider);
                j.pack();
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                control.setValue(slider.getValue()/100f);

            }
        });*/
    }
}