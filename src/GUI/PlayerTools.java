package GUI;

import Logic.RunMusic;
import Logic.Save;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class PlayerTools extends JPanel implements AddIcon {

    private JSlider progressBar;
    private JPanel buttonsPanel;
    private JButton play;
    private JButton back;
    private JButton next;
    private JButton shuffle;
    private JButton repeat;
    private SongInfo songInfo;
    private boolean isPlayed = false;
    private boolean isShuffle = false;
    private static boolean isRepeat = false;


    public PlayerTools(SongInfo songInfo) throws IOException, JavaLayerException {
        super();
        this.songInfo = songInfo;
        progressBar = new JSlider(0, 100, 0);
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
            }


            @Override
            public void mousePressed(MouseEvent e) {
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
                } else if (!isPlayed) {
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
                                if (i + 1 == save.getSortedMusicsCopy().size() && j==1) {
                                    i = -1;
                                }
                                if (i + j == -1) {
                                    i = save.getSortedMusicsCopy().size();
                                }
                                if (CentralPanel.getThread() != null) {
                                    CentralPanel.getThread().stop();
                                }
                                CentralPanel.setPath(save.getSortedMusicsCopy().get(i + j));
                                RunMusic runMusic = new RunMusic(CentralPanel.getPath());
                                Thread thread = new Thread(runMusic);
                                CentralPanel.setThread(thread);
                                try {
                                    if (getSongInfo() != null) {
                                        getSongInfo().changeSongInfo(save.getSortedMusicsCopy().get(i + j));
                                    }
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                                save.deleteAndReAddMusic(save.getSortedMusicsCopy().get(i + j));
                                save.saveToFile();
                                break;
                            }
                        }

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

/*

                Save save = new Save();
                for (int i = 0; i < save.getSortedMusicsCopy().size(); i++) {
                    if (CentralPanel.getPath().equals(save.getSortedMusicsCopy().get(i))) {
                        if (i + 1 == save.getSortedMusicsCopy().size()) {
                            i = -1;
                        }
                        if (CentralPanel.getThread() != null) {
                            CentralPanel.getThread().stop();
                        }
                        CentralPanel.setPath(save.getSortedMusicsCopy().get(i + 1));
                        RunMusic runMusic = new RunMusic(CentralPanel.getPath());
                        Thread thread = new Thread(runMusic);
                        CentralPanel.setThread(thread);
                        try {
                            if (getSongInfo() != null) {
                                getSongInfo().changeSongInfo(save.getSortedMusicsCopy().get(i + 1));
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        save.deleteAndReAddMusic(save.getSortedMusicsCopy().get(i + 1));
                        save.saveToFile();
                        break;
                    }
                }
*/

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
        img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ((JButton) container).setIcon(new ImageIcon(img));
        container.setPreferredSize(new Dimension(40, 40));
        ((JButton) container).setBorderPainted(false);
        ((JButton) container).setContentAreaFilled(false);
        ((JButton) container).setFocusPainted(false);

    }


}
