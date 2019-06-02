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

//        Image img = ImageIO.read(getClass().getResource("multimedia.png"));
//        play.setIcon(new ImageIcon(img));
        play.setPreferredSize(new Dimension(40,40));

        add(play);
    }
}

class VolumeBox extends JPanel {
    public VolumeBox() {
        super();
        setPreferredSize(new Dimension(250, 70));
        setOpaque(true);
        setBackground(Color.yellow);
        JLabel label = new JLabel("hello pooria");
        add(label);
    }
}


//        SpringLayout springLayout=new SpringLayout();
//        setLayout(BoxLayout);
//
//        SongInfo songInfo=new SongInfo();
//        PlayerTools playerTools=new PlayerTools();
//        VolumeBox volumeBox=new VolumeBox();
//
////        springLayout.putConstraint(SpringLayout.WEST,volumeBox,0,SpringLayout.WEST,this);
////        springLayout.putConstraint(SpringLayout.EAST,volumeBox,250,SpringLayout.WEST,this);
////        springLayout.putConstraint(SpringLayout.NORTH,volumeBox,0,SpringLayout.NORTH,this);
////        springLayout.putConstraint(SpringLayout.SOUTH,volumeBox,0,SpringLayout.SOUTH,this);
//        springLayout.putConstraint(SpringLayout.EAST,songInfo,1200,SpringLayout.WEST,this);
//        springLayout.putConstraint(SpringLayout.WEST,songInfo,950,SpringLayout.WEST,this);
//        springLayout.putConstraint(SpringLayout.NORTH,songInfo,0,SpringLayout.NORTH,this);
//        springLayout.putConstraint(SpringLayout.SOUTH,songInfo,0,SpringLayout.SOUTH,this);
////        springLayout.putConstraint(SpringLayout.WEST,playerTools,0,SpringLayout.EAST,songInfo);
////        springLayout.putConstraint(SpringLayout.EAST,playerTools,0,SpringLayout.WEST,volumeBox);
////        springLayout.putConstraint(SpringLayout.NORTH,playerTools,0,SpringLayout.NORTH,this);
////        springLayout.putConstraint(SpringLayout.SOUTH,playerTools,0,SpringLayout.SOUTH,this);