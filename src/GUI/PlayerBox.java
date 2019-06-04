package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

class SongInfo extends JPanel implements AddIcon {
    private JPanel artWork;
    private JPanel info;
    private JPanel like;
    private String filePath;

    public SongInfo() throws IOException {

        super();
        setPreferredSize(new Dimension(250, 70));
        setOpaque(true);
        setBackground(Color.DARK_GRAY);

        setLayout(new BorderLayout());
        createInfoPanel("C:\\Users\\Pooria\\Downloads\\Music\\Babak Jahanbakhsh - Sheydaei.mp3");
        add(info, BorderLayout.CENTER);
        createLikeIcon();
        add(like, BorderLayout.EAST);
        createArtWorkPanel();
        add(artWork, BorderLayout.WEST);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void createInfoPanel(String filePath) throws IOException {
        info = new JPanel(new BorderLayout());
        info.setLayout(new BorderLayout());
        JLabel songName = new JLabel(findSongInfo(filePath, 0));
        JLabel artistName = new JLabel(findSongInfo(filePath, 1));
        songName.setForeground(Color.WHITE);
        songName.setPreferredSize(new Dimension(135, 35));
        artistName.setForeground(Color.WHITE);
        info.add(songName, BorderLayout.NORTH);
        info.add(artistName, BorderLayout.CENTER);
        info.setOpaque(true);
        info.setBackground(Color.DARK_GRAY);
    }

    public String findSongInfo(String filePath, int index) throws IOException {
        File file = new File(filePath);
        byte[] songName = new byte[30];
        byte[] fileContent = Files.readAllBytes(file.toPath());
        for (int i = 0; i < 30; i++) {
            songName[i] = fileContent[i + fileContent.length - 125 + index * 30];
        }
        String name = new String(songName);
        System.out.println(name);
        return name;
    }

    public void createArtWorkPanel() {
        artWork = new JPanel();
        artWork.setPreferredSize(new Dimension(70, 70));
        artWork.setOpaque(true);
        artWork.setBackground(Color.DARK_GRAY);
        JPanel pic = new JPanel();
        pic.setPreferredSize(new Dimension(60, 60));
        pic.setOpaque(true);
        pic.setBackground(Color.WHITE);
        artWork.add(pic);
    }

    public void createLikeIcon() throws IOException {
        like = new JPanel();
        like.setPreferredSize(new Dimension(50, 50));
        like.setOpaque(true);
        like.setBackground(Color.DARK_GRAY);
        JButton button = new JButton();
        createIcon(button, "icons\\colored-buttons-2\\png\\003-favorite-heart-button.png", 18, 18);
        like.add(button);
    }


    @Override
    public void createIcon(Container container, String iconAddress, int width, int height) throws IOException {

        Image img = ImageIO.read(getClass().getResource(iconAddress));
        img = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        ((JButton) container).setIcon(new ImageIcon(img));
        container.setPreferredSize(new Dimension(60, 60));
        ((JButton) container).setBorderPainted(false);
        ((JButton) container).setContentAreaFilled(false);
        ((JButton) container).setFocusPainted(false);
    }
}

class PlayerTools extends JPanel implements AddIcon {

    private JSlider progressBar;
    private JPanel buttonsPanel;
    private JButton play;
    private JButton back;
    private JButton next;
    private JButton shuffle;
    private JButton repeat;
    private boolean isPlayed = false;
    private boolean isShuffle = false;
    private boolean isRepeat = false;

    public PlayerTools() throws IOException {
        super();
        progressBar = new JSlider(0, 100, 25);
        buttonsPanel = new JPanel();

        setLayout(new BorderLayout());

        setOpaque(true);
        setBackground(Color.darkGray);

        play = new JButton("");
        back = new JButton("");
        next = new JButton("");
        shuffle = new JButton("");
        repeat = new JButton("");

        createIcon(play, "icons\\my-icons-collection-2\\png\\002-play-button.png", 35, 35);
        createIcon(next, "icons\\my-icons-collection-2\\png\\010-skip-track-option.png", 14, 14);
        createIcon(shuffle, "icons\\my-icons-collection-2\\png\\003-suffle-option.png", 14, 14);
        createIcon(repeat, "icons\\my-icons-collection-2\\png\\008-clockwise-refresh-arrow.png", 14, 14);
        createIcon(back, "icons\\my-icons-collection-2\\png\\009-saltar-a-pista-anterior.png", 14, 14);

        buttonsPanel.add(shuffle);
        buttonsPanel.add(back);
        buttonsPanel.add(play);
        buttonsPanel.add(next);
        buttonsPanel.add(repeat);

        buttonEventHandler(play, "icons\\my-icons-collection-1\\png\\002-play-button.png", "icons\\my-icons-collection-2\\png\\002-play-button.png", "icons\\my-icons-collection-1\\png\\001-pause.png", "icons\\my-icons-collection-2\\png\\001-pause.png");
        buttonEventHandler(back, "icons\\my-icons-collection-1\\png\\010-saltar-a-pista-anterior.png", "icons\\my-icons-collection-2\\png\\009-saltar-a-pista-anterior.png", "icons\\my-icons-collection-1\\png\\010-saltar-a-pista-anterior.png", "icons\\my-icons-collection-2\\png\\009-saltar-a-pista-anterior.png");
        buttonEventHandler(next, "icons\\my-icons-collection-1\\png\\011-skip-track-option.png", "icons\\my-icons-collection-2\\png\\010-skip-track-option.png", "icons\\my-icons-collection-1\\png\\011-skip-track-option.png", "icons\\my-icons-collection-2\\png\\010-skip-track-option.png");
        repeatEventHandler("icons\\my-icons-collection-1\\png\\009-clockwise-refresh-arrow.png", "icons\\my-icons-collection-2\\png\\008-clockwise-refresh-arrow.png", "icons\\colored-buttons-1\\png\\002-clockwise-refresh-arrow.png", "icons\\colored-buttons-2\\png\\002-clockwise-refresh-arrow.png");
        shuffleEventHandler("icons\\my-icons-collection-1\\png\\003-suffle-option.png", "icons\\my-icons-collection-2\\png\\003-suffle-option.png", "icons\\colored-buttons-1\\png\\001-suffle-option.png", "icons\\colored-buttons-2\\png\\001-suffle-option.png");
        buttonsPanel.setOpaque(true);
        buttonsPanel.setBackground(Color.DARK_GRAY);
        progressBar.setOpaque(true);
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setSnapToTicks(true);

        add(progressBar, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.NORTH);
    }

    public void buttonEventHandler(JButton button, String icon1, String icon2, String icon3, String icon4) {
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }


            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                if (icon2.equals(icon4)) {
                    try {
                        createIcon(button, icon1, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (!isPlayed) {
                    try {
                        createIcon(button, icon1, 35, 35);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(button, icon3, 35, 35);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (icon2.equals(icon4)) {
                    try {
                        createIcon(button, icon2, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (!isPlayed) {
                    try {
                        createIcon(button, icon4, 35, 35);
                        isPlayed = true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(button, icon2, 35, 35);
                        isPlayed = false;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (icon2.equals(icon4)) {
                    try {
                        createIcon(button, icon2, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (!isPlayed) {
                    try {
                        createIcon(button, icon2, 35, 35);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(button, icon4, 35, 35);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    public void repeatEventHandler(String icon1, String icon2, String icon3, String icon4) {
        repeat.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }


            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isRepeat) {
                    try {
                        createIcon(repeat, icon1, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(repeat, icon3, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isRepeat) {
                    try {
                        createIcon(repeat, icon4, 14, 14);
                        isRepeat = true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(repeat, icon2, 14, 14);
                        isRepeat = false;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isRepeat) {
                    try {
                        createIcon(repeat, icon2, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(repeat, icon4, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    public void shuffleEventHandler(String icon1, String icon2, String icon3, String icon4) {
        shuffle.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }


            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isShuffle) {
                    try {
                        createIcon(shuffle, icon1, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(shuffle, icon3, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isShuffle) {
                    try {
                        createIcon(shuffle, icon4, 14, 14);
                        isShuffle = true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(shuffle, icon2, 14, 14);
                        isShuffle = false;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isShuffle) {
                    try {
                        createIcon(shuffle, icon2, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(shuffle, icon4, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void createIcon(Container container, String iconAddress, int width, int height) throws IOException {
        Image img = ImageIO.read(getClass().getResource(iconAddress));
        img = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        ((JButton) container).setIcon(new ImageIcon(img));
        container.setPreferredSize(new Dimension(40, 40));
        ((JButton) container).setBorderPainted(false);
        ((JButton) container).setContentAreaFilled(false);
        ((JButton) container).setFocusPainted(false);

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

        volumeSlider.setExtent(50);

        add(volumeButton);
        add(volumeSlider);
    }
}