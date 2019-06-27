package GUI;


import Logic.Save;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class PlayLists extends JPanel implements AddIcon {
    private BoxLayout layout;
    private JButton addPlaylistButton;
    private Save save = new Save();
    private static ArrayList<String> songs = new ArrayList<>();
    private static ArrayList<String> sortedSongs = new ArrayList<>();
    private MusicController musicController;
    private JLabel label;
    private Font font1 = new Font("Font1", Font.ITALIC, 50);
    private Font font2 = new Font("Font2", Font.BOLD, 30);
    private JButton addToPlaylist;
    private JFrame chooseFrame;
    private JFrame exchangePannel;
    private JButton allMusicsInNewFrame[];
    private String fileName;
    private String clickedButtonName;
    private JButton deletePlayList;
    private static String theCurrentFilePath;
    private JLabel sorryLable;
    private JPanel playListsButton;
    private JButton changeTiles;
    private String filepath1 = "";
    private String filepath2 = "";
    private String theWantedPlayList;
    private int i;

    public PlayLists(MusicController musicController) throws IOException, ClassNotFoundException {
        super();
        this.musicController = musicController;

        if (Files.exists(Paths.get("C:\\Users\\Public\\Documents\\PlaylistsName.ser"))) {
            save.readPlayListsName();
        }

        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        Font font = new Font("MyFont", 1, 20);
        add(Box.createVerticalStrut(20));
        JLabel title = new JLabel("    PLAYLISTS");
        title.setForeground(Color.white);
        title.setFont(font);

        playListsButton = new JPanel();
        playListsButton.setPreferredSize(new Dimension(180, 50));
        playListsButton.setOpaque(true);
        playListsButton.setBackground(Color.gray);
        playListsButton.setLayout(new BorderLayout());

        changeTiles = new JButton();
        changeTiles.setFont(font2);
        changeTiles.setPreferredSize(new Dimension(60, 50));
        changeTiles.setOpaque(true);
        changeTiles.setBackground(Color.gray);
        changeTiles.setBorderPainted(false);
        changeTiles.setFocusPainted(false);
        changeTiles.setContentAreaFilled(false);
        createIcon(changeTiles, "icons\\playlist-icons-2\\png\\002-exchange.png", 35, 35);

        deletePlayList = new JButton();
        deletePlayList.setFont(font2);
        deletePlayList.setPreferredSize(new Dimension(60, 50));
        deletePlayList.setOpaque(true);
        deletePlayList.setBackground(Color.gray);
        deletePlayList.setFocusPainted(false);
        deletePlayList.setBorderPainted(false);
        deletePlayList.setContentAreaFilled(false);
        createIcon(deletePlayList, "icons\\playlist-icons-2\\png\\001-garbage.png", 35, 35);

        addToPlaylist = new JButton();
        addToPlaylist.setFont(font2);
        addToPlaylist.setPreferredSize(new Dimension(60, 50));
        addToPlaylist.setOpaque(true);
        addToPlaylist.setBackground(Color.gray);
        addToPlaylist.setFocusPainted(false);
        addToPlaylist.setBorderPainted(false);
        addToPlaylist.setContentAreaFilled(false);
        createIcon(addToPlaylist, "icons\\playlist-icons-2\\png\\003-plus.png", 35, 35);

        playListsButton.add(addToPlaylist, BorderLayout.WEST);
        playListsButton.add(changeTiles, BorderLayout.CENTER);
        playListsButton.add(deletePlayList, BorderLayout.EAST);

        allMusicsInNewFrame = new JButton[100];

        JButton[] buttons = new JButton[50];
        addPlaylistButton = new JButton();

        createButton(addPlaylistButton, "+ Add playlist");
        add(title);
        add(Box.createVerticalStrut(20));

        for (int i = 0; i < save.getPlayListsName().size(); i++) {
            if (i != 0) {
                add(Box.createVerticalStrut(20));
            }
            buttons[i] = new JButton();
            createButton(buttons[i], save.getPlayListsName().get(i));
            add(buttons[i]);
            if (i == save.getPlayListsName().size() - 1)
                add(Box.createVerticalStrut(20));
//            addPlayList();
        }

        add(addPlaylistButton);
//        add(Box.createVerticalStrut(20));
        setOpaque(true);
        setBackground(Color.BLACK);
    }

//    public InteractivePart getInteractivePart() {
//        return interactivePart;
//    }


    public static ArrayList<String> getSongs() {
        return songs;
    }

    public MusicController getMusicController() {
        return musicController;
    }

    public void createButton(JButton button, String name) {

        Font font1 = new Font("Font1", Font.ITALIC, 17);

        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setText("  " + name);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(font1);
        button.setForeground(Color.WHITE);
        buttonEventHandler(button);
    }

    public void buttonEventHandler(JButton clickedButton) {
        clickedButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == addPlaylistButton) {
                    JFrame f = new JFrame("PlayList");
                    String name = JOptionPane.showInputDialog(f, "Enter Name");
                    try {
                        save.addPlayList(name);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JButton button = new JButton(name);
                    createButton(button, name);
                    remove(addPlaylistButton);
                    add(button);
                    add(Box.createVerticalStrut(20));
//                    revalidate();
//                    repaint();
                    add(addPlaylistButton);
//                    add(Box.createVerticalStrut(25));
                    getSongs().clear();
                    try {
                        createAndSaveFile(button.getText().trim());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    revalidate();
                    repaint();

                    for (int i = 0; i < save.getPlayListsName().size(); i++) {
                        System.out.println(save.getPlayListsName().get(i));
                    }
                } else {
                    InteractivePart.setPart(1);
                    getMusicController().clearMusidControler();

                    getMusicController().getTitle().setLayout(new BorderLayout());

                    label = new JLabel("........." + clickedButton.getText() + " playlist  .........");
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setFont(font1);

                    theWantedPlayList = clickedButton.getText().trim();

                    setTheCurrentFilePath(clickedButton.getText().trim());

                    setClickedButtonName(clickedButton.getText().trim());

                    try {
                        getSongs().clear();
                        readFile(clickedButton.getText().trim());

                        //////////////////////////////////////////////////////
//                        for (int j = 0; j < save.getSortedMusics().size(); j++) {
//                            if (getSongs().contains(save.getSortedMusics().get(i)))
//                            {
//                                getSortedSongs().add(save.getSortedMusics().get(i));
//                            }
//                        }
                        save.setSortedMusicsCopy(getSongs());
                        //////////////////////////////////////////////////////


                    } catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    if (getSongs().size() == 0) {
                        sorryLable = new JLabel("Sorry , but there is no music in the playlist ...!");
                        sorryLable.setFont(font2);
                        sorryLable.setHorizontalAlignment(SwingConstants.CENTER);
                        getMusicController().getInteractivePart().add(sorryLable);
                    } else {
                        for (int j = 0; j < getSongs().size(); j++) {
                            try {
                                getMusicController().getInteractivePart().makeMusicPad(getSongs().get(j));
                            } catch (InvalidDataException | UnsupportedTagException | IOException e1) {
                                e1.printStackTrace();
                            }
                        }
//                        for (int j = 0; j < save.getSortedMusics().size(); j++) {
//                            if (getSongs().contains(save.getSortedMusics().get(j))) {
//                                try {
//                                    getMusicController().getInteractivePart().makeMusicPad(save.getSortedMusics().get(j));
//                                } catch (InvalidDataException e1) {
//                                    e1.printStackTrace();
//                                } catch (IOException e1) {
//                                    e1.printStackTrace();
//                                } catch (UnsupportedTagException e1) {
//                                    e1.printStackTrace();
//                                }
//                            }
//                        }
                    }


//                    getMusicController().getTitle().add(addToPlaylist, BorderLayout.EAST);
//                    getMusicController().getTitle().add(deletePlayList, BorderLayout.WEST);
                    getMusicController().getTitle().add(playListsButton, BorderLayout.EAST);
                    getMusicController().getTitle().add(label, BorderLayout.CENTER);

                    changeTiles.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            exchangeMusicTiles(clickedButton.getText().trim(), getSongs());
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            try {
                                createIcon(changeTiles, "icons\\playlist-icons-1\\png\\002-exchange.png", 35, 35);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            try {
                                createIcon(changeTiles, "icons\\playlist-icons-2\\png\\002-exchange.png", 35, 35);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                    /*if (theWantedPlayList.equals("Shared"))
                    {
                        deletePlayList.setText("");
                        revalidate();
                        repaint();
                        deletePlayList.setBackground(Color.gray);
                        deletePlayList.setOpaque(true);
                        deletePlayList.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("Sorry you can not delete shared playlist !");
                            }
                        });
                        SwingUtilities.updateComponentTreeUI(FormGUI.getFormGUI());
                    }*/

//                        deletePlayList.setText("-");
                    deletePlayList.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                save.eliminatePlayList(theWantedPlayList);
                                refreshPlayList();
                                getMusicController().getInteractivePart().removeAll();
                                getMusicController().getTitle().removeAll();
                                Files.deleteIfExists(Paths.get( "C:\\Users\\Public\\Documents\\" + theWantedPlayList + ".ser"));
                                System.out.println(theWantedPlayList);
//                                save.getPlayListsName().trimToSize();
                                getMusicController().getInteractivePart().setBackground(Color.gray);
                                getMusicController().getTitle().setBackground(Color.gray);
                                revalidate();
                                repaint();
                                SwingUtilities.updateComponentTreeUI(FormGUI.getFormGUI());
                                try {
                                    createIcon(deletePlayList, "icons\\playlist-icons-2\\png\\001-garbage.png", 35, 35);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }

                            } catch (IOException e1) {
                                e1.printStackTrace();
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
                            try {
                                createIcon(deletePlayList, "icons\\playlist-icons-1\\png\\001-garbage.png", 35, 35);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            try {
                                createIcon(deletePlayList, "icons\\playlist-icons-2\\png\\001-garbage.png", 35, 35);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
//                    }

                    addToPlaylist.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            chooseFrame = new JFrame("All Songs");
                            chooseFrame.setVisible(true);
                            JPanel panel = new JPanel();
                            BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
                            panel.setLayout(layout);
                            JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                            chooseFrame.setSize(600, 600);
                            panel.setBackground(Color.black);
                            Font font1 = new Font("Font2", Font.ITALIC, 20);
                            Font font2 = new Font("Font2", Font.BOLD, 20);
                            JLabel label = new JLabel("  Choose song to add to playlist: ");
                            panel.add(Box.createVerticalStrut(200));
                            panel.add(label);
                            label.setFont(font2);
                            panel.add(Box.createVerticalStrut(20));
//                        chooseFrame.pack();

                            for (int counter = 0; counter < save.getSortedMusics().size(); counter++) {
                                try {
                                    String songInfo = " +         " + getMusicController().getInteractivePart().findSongInfo(save.getSortedMusics().get(counter), 0) + " - " + getMusicController().getInteractivePart().findSongInfo(save.getSortedMusics().get(counter), 1);
                                    allMusicsInNewFrame[counter] = new JButton(songInfo);
                                    allMusicsInNewFrame[counter].setHorizontalAlignment(SwingConstants.LEFT);
                                    allMusicsInNewFrame[counter].setBorderPainted(false);
                                    allMusicsInNewFrame[counter].setFocusPainted(false);
                                    allMusicsInNewFrame[counter].setFont(font1);
                                    allMusicsInNewFrame[counter].setContentAreaFilled(false);
                                    allMusicsInNewFrame[counter].setBackground(Color.GRAY);
                                    allMusicsInNewFrame[counter].setForeground(Color.WHITE);
                                    panel.add(allMusicsInNewFrame[counter]);
                                    panel.add(Box.createVerticalStrut(20));
                                    buttonHandler(allMusicsInNewFrame[counter]);

                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            chooseFrame.add(scrollPane);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            try {
                                createIcon(addToPlaylist, "icons\\playlist-icons-1\\png\\003-plus.png", 35, 35);
                            } catch (IOException e1) {
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            try {
                                createIcon(addToPlaylist, "icons\\playlist-icons-2\\png\\003-plus.png", 35, 35);
                            } catch (IOException e1) {
                            }
                        }
                    });
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                clickedButton.setForeground(Color.getHSBColor(104, 69, 55));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                clickedButton.setForeground(Color.GREEN);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                clickedButton.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                clickedButton.setForeground(Color.WHITE);
            }
        });
    }

    public void addPlayList() {
        JPanel playListButton = new JPanel();
        JLabel playListName = new JLabel();
        JButton addMusic = new JButton();
        JButton deletePlayList = new JButton();

        playListButton.setLayout(new FlowLayout());
        add(Box.createVerticalStrut(25));
        add(playListButton);

        playListName.setText("PlayList");
        addMusic.setText("+");
        deletePlayList.setText("-");

        playListName.setPreferredSize(new Dimension(150, 35));
        playListButton.add(playListName);
        playListButton.add(addMusic);
        playListButton.add(deletePlayList);
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public void addAndSave(String path, String filename) throws IOException {
        if (!getSongs().contains(path)) {
            getSongs().add(path);
            createAndSaveFile(filename);
        }
    }

    public static void createAndSaveFile(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Public\\Documents\\" + fileName + ".ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(getSongs());
        oos.close();
        fos.close();
    }

    public static void setSongs(ArrayList<String> songs) {
        getSongs().clear();
        PlayLists.songs = songs;
    }

    public static void readFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fisOfArreyList = new FileInputStream("C:\\Users\\Public\\Documents\\" + fileName + ".ser");
        ObjectInputStream oisOfArreyList = new ObjectInputStream(fisOfArreyList);
        setSongs((ArrayList) oisOfArreyList.readObject());
        for (int i = 0; i < getSongs().size(); i++) {
        }
        oisOfArreyList.close();
        fisOfArreyList.close();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    public void buttonHandler(JButton button) {
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (i = 0; i < save.getSortedMusics().size(); i++) {
                    if (e.getSource() == allMusicsInNewFrame[i]) {
                        try {
                            getMusicController().getInteractivePart().makeMusicPad(save.getSortedMusics().get(i));
                            addAndSave(save.getSortedMusics().get(i), getClickedButtonName());
                            if (sorryLable != null) {
                                sorryLable.setText("");
                            }
                        } catch (InvalidDataException | IOException | UnsupportedTagException e1) {
                            e1.printStackTrace();
                        }
                        getMusicController().getInteractivePart().revalidate();
                        getMusicController().getInteractivePart().repaint();

                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setForeground(Color.getHSBColor(104, 69, 55));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setForeground(Color.GREEN);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }

        });
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getClickedButtonName() {
        return clickedButtonName;
    }

    public void setClickedButtonName(String clickedButtonName) {
        this.clickedButtonName = clickedButtonName;
    }

    public void refreshPlayList() throws IOException {
        this.removeAll();
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        Font font = new Font("MyFont", 1, 20);
        add(Box.createVerticalStrut(20));
        JLabel title = new JLabel("    PLAYLISTS");
        title.setForeground(Color.white);
        title.setFont(font);

//        deletePlayList = new JButton();
//        deletePlayList.setFont(font2);
//        deletePlayList.setPreferredSize(new Dimension(100, 100));
//        deletePlayList.setOpaque(true);
//        deletePlayList.setBackground(Color.gray);
//        deletePlayList.setFocusPainted(false);
//        deletePlayList.setBorderPainted(false);
//        createIcon(deletePlayList, "icons\\playlist-icons-2\\png\\001-garbage.png", 35, 35);
//
//        addToPlaylist = new JButton("+");
//        addToPlaylist.setFont(font2);
//        addToPlaylist.setPreferredSize(new Dimension(100, 100));
//        addToPlaylist.setOpaque(true);
//        addToPlaylist.setBackground(Color.gray);
//        addToPlaylist.setFocusPainted(false);
//        addToPlaylist.setBorderPainted(false);
//        createIcon(addToPlaylist, "icons\\playlist-icons-2\\png\\003-plus.png", 35, 35);

        allMusicsInNewFrame = new JButton[100];

        JButton[] buttons = new JButton[50];
        addPlaylistButton = new JButton();
        createButton(addPlaylistButton, "+ Add playlist");
        add(title);
        add(Box.createVerticalStrut(20));

        for (int i = 0; i < save.getPlayListsName().size(); i++) {
            if (i != 0) {
                add(Box.createVerticalStrut(20));
            }
            buttons[i] = new JButton();
            createButton(buttons[i], save.getPlayListsName().get(i));
            add(buttons[i]);
            if (i == save.getPlayListsName().size() - 1)
                add(Box.createVerticalStrut(20));
//            addPlayList();
        }

        add(addPlaylistButton);
        add(Box.createVerticalStrut(20));
        setOpaque(true);
        setBackground(Color.BLACK);
        revalidate();
        repaint();
    }

    public static void setTheCurrentFilePath(String theCurrentFilePath) {
        PlayLists.theCurrentFilePath = theCurrentFilePath;
    }

    public static String getTheCurrentFilePath() {
        return theCurrentFilePath;
    }

    public static ArrayList<String> getSortedSongs() {
        return sortedSongs;
    }

    public static void setSortedSongs(ArrayList<String> sortedSongs) {
        PlayLists.sortedSongs = sortedSongs;
    }

    public void exchangeMusicTiles(String fileName, ArrayList<String> arrayList) {
        exchangePannel = new JFrame("Exchange Pannel");
        exchangePannel.setVisible(true);
        exchangePannel.setSize(600, 600);
        exchangePannel.setLayout(new FlowLayout());
        filepath1 = "";
        filepath2 = "";

        for (int counter = 0; counter < arrayList.size(); counter++) {
            try {
                allMusicsInNewFrame[counter] = new JButton(getMusicController().getInteractivePart().findSongInfo(arrayList.get(counter), 0));
                exchangePannel.add(allMusicsInNewFrame[counter]);
                exchangeEventHandler(fileName, allMusicsInNewFrame[counter], arrayList, 1);
                exchangeEventHandler(fileName, allMusicsInNewFrame[counter], arrayList, 2);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }

    public void exchangeEventHandler(String fileName, JButton button, ArrayList<String> arrayList, int index) {
        button.addActionListener(e -> {
            for (i = 0; i < arrayList.size(); i++) {
                if (e.getSource() == allMusicsInNewFrame[i]) {
                    if (index == 1) {
                        if (filepath1.equals("")) {
                            filepath1 = arrayList.get(i);
                            filepath2 = "";
                        }
                    } else if (index == 2) {
                        if (filepath2.equals("") && !filepath2.equals(filepath1)) {
                            filepath2 = arrayList.get(i);
                            int index1 = 0, index2 = 0;
                            for (int j = 0; j < arrayList.size(); j++) {
                                if (arrayList.get(j).equals(filepath1)) {
                                    index1 = j;
                                }
                                if (arrayList.get(j).equals(filepath2)) {
                                    index2 = j;
                                }
                            }
                            Collections.swap(arrayList, index1, index2);
                            try {
                                createAndSaveFile(fileName);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            getMusicController().getInteractivePart().clearPanel();
                            for (int j = 0; j < getSongs().size(); j++) {
                                try {
                                    getMusicController().getInteractivePart().makeMusicPad(getSongs().get(j));
                                    exchangePannel.dispose();
                                } catch (InvalidDataException | UnsupportedTagException | IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            filepath1 = "";
                            filepath2 = "";
                            revalidate();
                            repaint();
                            SwingUtilities.updateComponentTreeUI(FormGUI.getFormGUI());

                        }
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



