package GUI;

import Logic.Audio;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class PlayerBox extends JPanel {
    private SongInfo songInfo;
    private PlayerTools playerTools;

    private static String filePath;

    public PlayerTools getPlayerTools() {
        return playerTools;
    }

    public PlayerBox() throws IOException {
        setPreferredSize(new Dimension(1200, 70));
        setOpaque(true);
        setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        songInfo = new SongInfo(null);
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

    private JSlider volumeSlider;
    private JButton volumeButton;
    private boolean isMute = false;

    public VolumeBox() throws IOException {
        super();
        volumeSlider = new JSlider(0, 100, 25);
        Audio.setMasterOutputVolume((float) 0.25);
        volumeButton = new JButton();

        Icon icon = new ImageIcon("icons\\circle.png");
        UIDefaults uiDefaults = UIManager.getDefaults();
        uiDefaults.put("Slider.horizontalThumbIcon", icon);

        setPreferredSize(new Dimension(250, 70));
        setOpaque(true);
        setBackground(Color.DARK_GRAY);

        createIcon("icons\\my-icons-collection-2\\png\\006-speaker-1.png");

        volumeSlider.setOpaque(true);
        volumeSlider.setBackground(Color.DARK_GRAY);
        volumeSlider.putClientProperty( "Slider.paintThumbArrowShape", Boolean.TRUE );

        add(volumeButton);
        add(volumeSlider);

        addAction();
    }

    public void addAction() {
        volumeSlider.addChangeListener(e -> {
            Audio.setMasterOutputVolume((float) volumeSlider.getValue() / 100);
            if (volumeSlider.getValue() == 0) {
                try {
                    createIcon("icons\\my-icons-collection-2\\png\\007-speaker-2.png");
                    isMute = true;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                try {
                    createIcon("icons\\my-icons-collection-2\\png\\006-speaker-1.png");
                    isMute = false;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        volumeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isMute || volumeSlider.getValue() == 0) {
                    isMute = false;
                    Audio.setMasterOutputMute(false);
                    try {
                        createIcon("icons\\my-icons-collection-2\\png\\006-speaker-1.png");
                        if (volumeSlider.getValue() == 0) {
                            Audio.setMasterOutputVolume((float) 0.2);
                            volumeSlider.setValue(20);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    isMute = true;
                    Audio.setMasterOutputMute(true);
                    try {
                        createIcon("icons\\my-icons-collection-1\\png\\008-speaker-2.png");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (isMute || volumeSlider.getValue() == 0) {
                    try {
                        createIcon("icons\\my-icons-collection-1\\png\\008-speaker-2.png");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon("icons\\my-icons-collection-1\\png\\007-speaker-1.png");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (isMute || volumeSlider.getValue() == 0) {
                    try {
                        createIcon("icons\\my-icons-collection-2\\png\\007-speaker-2.png");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon("icons\\my-icons-collection-2\\png\\006-speaker-1.png");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    public void createIcon(String path) throws IOException {
        Image img = ImageIO.read(getClass().getResource(path));
        img = img.getScaledInstance(14, 14, java.awt.Image.SCALE_SMOOTH);
        volumeButton.setIcon(new ImageIcon(img));
        volumeButton.setPreferredSize(new Dimension(40, 40));
        volumeButton.setBorderPainted(false);
        volumeButton.setContentAreaFilled(false);
        volumeButton.setFocusPainted(false);
    }
}