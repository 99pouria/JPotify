package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayerBox extends JPanel {
    public PlayerBox() throws IOException {
        setPreferredSize(new Dimension(1200, 70));
        setOpaque(true);
        setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        SongInfo songInfo = new SongInfo();
        PlayerTools playerTools = new PlayerTools();
        VolumeBox volumeBox = new VolumeBox();


        add(songInfo, BorderLayout.WEST);
        add(playerTools, BorderLayout.CENTER);
        add(volumeBox, BorderLayout.EAST);

    }
}

class SongInfo extends JPanel {
    public SongInfo() {
        super();
        setPreferredSize(new Dimension(250, 70));
        setOpaque(true);
        setBackground(Color.blue);
    }
}

class PlayerTools extends JPanel {
    public PlayerTools() throws IOException {
        super();
        setOpaque(true);
        setBackground(Color.red);

        JButton play = new JButton("");
        JButton back = new RoundButton("");
        JButton next = new RoundButton("");
        JButton shuffle = new RoundButton("");
        JButton repeat = new RoundButton("");

        Image img = ImageIO.read(getClass().getResource("icons\\my-icons-collection-2\\png\\002-play-button.png"));
        img = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH ) ;
        play.setIcon(new ImageIcon(img));
        play.setPreferredSize(new Dimension(40,40));
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);

        add(play);
    }
}

class VolumeBox extends JPanel {
    public VolumeBox() {
        super();
        setPreferredSize(new Dimension(250, 70));
        setOpaque(true);
        setBackground(Color.yellow);
    }
}