package GUI;

import Logic.RunMusic;
import Logic.Save;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class PlayerTools extends JPanel implements AddIcon {

    private RunMusic runMusic;
    private static JSlider progressBar;
//    private static ProgressBar progressBar;
    private JPanel buttonsPanel;
    private JButton play;
    private JButton back;
    private JButton next;
    private JButton shuffle;
    private JButton repeat;
    private SongInfo songInfo;
    private boolean isShuffle = false;
    private static boolean isRepeat = false;

    public JButton getPlay() {
        return play;
    }

    public PlayerTools(SongInfo songInfo) throws IOException {
        super();
        this.songInfo = songInfo;
//        progressBar = new ProgressBar(0, 100, 0);
        progressBar=new JSlider(0,0,0);
        buttonsPanel = new JPanel();
        setLayout(new BorderLayout());

        setOpaque(true);
        setBackground(Color.darkGray);

        play = new JButton("");
        back = new JButton("");
        next = new JButton("");
        shuffle = new JButton("");
        repeat = new JButton("");
        String playButton;

        if (!CentralPanel.isPlaying()) {
            playButton = "icons\\my-icons-collection-2\\png\\002-play-button.png";
        } else {
            playButton = "icons\\my-icons-collection-2\\png\\001-pause.png";
        }

        createIcon(play, playButton, 35, 35);
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
//        progressBar.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//
//            }
//        });

        add(progressBar, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.NORTH);
    }

    public SongInfo getSongInfo() {
        return songInfo;
    }

    public static boolean isIsRepeat() {
        return isRepeat;
    }

    public void buttonEventHandler(JButton button, String icon1, String icon2, String icon3, String icon4) {
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mouseEntered(e);
            }


            @Override
            public void mousePressed(MouseEvent e) {
                mouseEntered(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                if (icon2.equals(icon4)) {
                    try {
                        System.out.println("1");
                        createIcon(button, icon1, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (!CentralPanel.isPlaying()) {
                    try {
                        System.out.println("2");
                        createIcon(button, icon1, 35, 35);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        System.out.println("3");

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
                        int j = 0;
                        if (e.getSource() == next) {
                            j = 1;
                        } else if (e.getSource() == back) {
                            j = -1;
                        }

                        Save save = new Save();
                        for (int i = 0; i < save.getSortedMusicsCopy().size(); i++) {
                            if (CentralPanel.getPath().equals(save.getSortedMusicsCopy().get(i))) {
                                if (i + 1 == save.getSortedMusicsCopy().size() && j == 1) {
                                    i = -1;
                                }
                                if (i + j == -1) {
                                    i = save.getSortedMusicsCopy().size();
                                }
                                if (CentralPanel.getRunMusic() != null) {
                                    CentralPanel.getRunMusic().mp3Pause();
//                     //////////////////////               CentralPanel.getThread().stop();
                                }
                                CentralPanel.setPath(save.getSortedMusicsCopy().get(i + j));
                                runMusic = new RunMusic(CentralPanel.getPath(),songInfo);
//                              ////////  Thread thread = new Thread(runMusic);
                                ///////////CentralPanel.setThread(thread);
                                CentralPanel.setRunMusic(runMusic);
                                try {
                                    if (getSongInfo() != null) {
                                        getSongInfo().changeSongInfo(save.getSortedMusicsCopy().get(i + j));
                                    }
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                                save.deleteAndReAddMusic(save.getSortedMusicsCopy().get(i + j));
                                save.saveToFile();
                                createIcon(play, "icons\\my-icons-collection-2\\png\\001-pause.png", 35, 35);
                                CentralPanel.getRunMusic().start();/////////////////////
                                CentralPanel.setPlaying(true);
                                break;
                            }
                        }

                        createIcon(button, icon1, 14, 14);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (!CentralPanel.isPlaying()) {
                    try {
                        if (CentralPanel.getRunMusic().isAlive()) {
//                    runMusic.resume(thread);
                            CentralPanel.getRunMusic().mp3Resume();////////////////////
                            CentralPanel.setPlaying(true);
                        } else {
                            CentralPanel.getRunMusic().start();
                            CentralPanel.setPlaying(true);
                        }
                        try {
                            System.out.println("2");
                            createIcon(button, icon2, 35, 35);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        createIcon(button, icon3, 35, 35);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
//                    runMusic.stopThread(thread);
                        CentralPanel.getRunMusic().mp3Pause();//////////////
                        CentralPanel.setPlaying(false);
                        createIcon(button, icon1, 35, 35);
                    } catch (Exception e1) {
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
                } else if (!CentralPanel.isPlaying()) {
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
                        createIcon(repeat, icon3, 14, 14);
                        isRepeat = true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(repeat, icon1, 14, 14);
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
                        createIcon(shuffle, icon3, 14, 14);
                        isShuffle = true;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        createIcon(shuffle, icon1, 14, 14);
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
        img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ((JButton) container).setIcon(new ImageIcon(img));
        container.setPreferredSize(new Dimension(40, 40));
        ((JButton) container).setBorderPainted(false);
        ((JButton) container).setContentAreaFilled(false);
        ((JButton) container).setFocusPainted(false);

    }

    public static void timer(){
        int moving=0;
        String progress = Integer.toString(0);
        while (moving<=100)
        {
            progress = Integer.toString(moving);
            progressBar.setValue(moving);
            moving++;
        }
    }


//    public static ProgressBar getProgressBar() {
//        return progressBar;
//    }


    public static JSlider getProgressBar() {
        return progressBar;
    }

    public static void setMaximum(String path) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File song = new Mp3File(path);
        long time= song.getLengthInSeconds();
        progressBar.setMaximum((int) time*1000);
    }

    public static void setPosition(float position){
        progressBar.setValue((int)position);
    }
}

//class ProgressBar extends JSlider {
//
//    public ProgressBar(int min, int max, int value) {
//        super(min, max, value);
//    }
//
//
//    public static void setMaximum(String path) throws InvalidDataException, IOException, UnsupportedTagException {
//        Mp3File song = new Mp3File(path);
//        long time= song.getLengthInSeconds();
//        setMaximum((int) time*1000);
//    }
//
//}