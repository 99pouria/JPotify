package GUI;


import Logic.Save;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

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

public class PlayLists extends JPanel {
    private BoxLayout layout;
    private JButton addPlaylistButton;
    private Save save = new Save();
    private static ArrayList<String> songs = new ArrayList<>();
    private static ArrayList<String> sortedSongs=new ArrayList<>();
    private MusicController musicController;
    private JLabel label;
    private Font font1 = new Font("Font1", Font.ITALIC, 50);
    private Font font2 = new Font("Font2", Font.BOLD, 30);
    private JButton addToPlaylist;
    private JFrame chooseFrame;
    private JFrame exchangePannel;
    private JButton allMusidsInNewFrame[];
    private String fileName;
    private String clickedButtonName;
    private JButton deletePlayList;
    private static String theCurrentFilePath;
    private JLabel sorryLable;
    private JPanel playListsButton;
    private JButton changeTiles;
    private String filepath1="";
    private String filepath2="";
    private int i;

    public PlayLists(MusicController musicController) throws IOException, ClassNotFoundException {
        super();
        this.musicController = musicController;

        save.readPlayListsName();

        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        Font font = new Font("MyFont", 1, 17);
        JLabel title = new JLabel("      PLAYLISTS");
        title.setForeground(Color.white);
        title.setFont(font);

        playListsButton=new JPanel();
        playListsButton.setPreferredSize(new Dimension(180,50));
        playListsButton.setOpaque(true);
        playListsButton.setBackground(Color.gray);
        playListsButton.setLayout(new BorderLayout());

        changeTiles=new JButton("X");
        changeTiles.setFont(font2);
        changeTiles.setPreferredSize(new Dimension(60,50));
        changeTiles.setOpaque(true  );
        changeTiles.setBackground(Color.gray);
        changeTiles.setBorderPainted(false);
        changeTiles.setFocusPainted(false);
        changeTiles.setContentAreaFilled(false);

        deletePlayList = new JButton("-");
        deletePlayList.setFont(font2);
        deletePlayList.setPreferredSize(new Dimension(60, 50));
        deletePlayList.setOpaque(true);
        deletePlayList.setBackground(Color.gray);
        deletePlayList.setFocusPainted(false);
        deletePlayList.setBorderPainted(false);
        deletePlayList.setContentAreaFilled(false);

        addToPlaylist = new JButton("+");
        addToPlaylist.setFont(font2);
        addToPlaylist.setPreferredSize(new Dimension(60, 50));
        addToPlaylist.setOpaque(true);
        addToPlaylist.setBackground(Color.gray);
        addToPlaylist.setFocusPainted(false);
        addToPlaylist.setBorderPainted(false);
        addToPlaylist.setContentAreaFilled(false);

        playListsButton.add(addToPlaylist,BorderLayout.WEST);
        playListsButton.add(changeTiles,BorderLayout.CENTER);
        playListsButton.add(deletePlayList,BorderLayout.EAST);

        allMusidsInNewFrame = new JButton[100];

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
//            if (i==save.getPlayListsName().size()-1)
//                add(Box.createVerticalStrut(25));
//            addPlayList();
        }

        add(addPlaylistButton);
        add(Box.createVerticalStrut(20));
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

        Font font1 = new Font("Font1", Font.ITALIC, 15);

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
                    add(Box.createVerticalStrut(20));
                    getSongs().clear();
                    try {
                        creatAndSaveFile(button.getText().trim());
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
                        ////////////////////////////////////////////////////////


                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
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
                            } catch (InvalidDataException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedTagException e1) {
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

                    changeTiles.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            exchangeMusicTiles(clickedButton.getText().trim(),getSongs());
                        }
                    });

                    deletePlayList.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                save.deletePlayList(clickedButton.getText().trim());
                                refreshPlayList();
                                getMusicController().getInteractivePart().removeAll();
                                getMusicController().getTitle().removeAll();
                                Files.delete(Paths.get("C:\\Users\\Public\\Documents\\" + clickedButton.getText().trim() + ".ser"));
                                save.getPlayListsName().trimToSize();
                                getMusicController().getInteractivePart().setBackground(Color.gray);
                                getMusicController().getTitle().setBackground(Color.gray);
                                revalidate();
                                repaint();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                    addToPlaylist.addActionListener(e12 -> {
                        chooseFrame = new JFrame("All Songs");
                        chooseFrame.setVisible(true);
                        chooseFrame.setSize(600, 600);
                        chooseFrame.setLayout(new FlowLayout());

                        for (int counter = 0; counter < save.getSortedMusics().size(); counter++) {
                            try {
                                allMusidsInNewFrame[counter] = new JButton(getMusicController().getInteractivePart().findSongInfo(save.getSortedMusics().get(counter), 0));
                                chooseFrame.add(allMusidsInNewFrame[counter]);
                                buttonHandler(allMusidsInNewFrame[counter]);
//                                    allMusidsInNewFrame[counter].addActionListener(e12 -> {
//                                        try {
//                                            getMusicController().getInteractivePart().makeMusicPad(save.getSortedMusics().get(counter-1));
//                                            getMusicController().getInteractivePart().revalidate();
//                                            getMusicController().getInteractivePart().repaint();
//                                        } catch (InvalidDataException e1) {
//                                            e1.printStackTrace();
//                                        } catch (IOException e1) {
//                                            e1.printStackTrace();
//                                        } catch (UnsupportedTagException e1) {
//                                            e1.printStackTrace();
//                                        }
//                                    });

                            } catch (IOException e1) {
                                e1.printStackTrace();
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

        playListName.setPreferredSize(new Dimension(150, 25));
        playListButton.add(playListName);
        playListButton.add(addMusic);
        playListButton.add(deletePlayList);
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public void addAndSave(String path, String filename) throws IOException {
        if (!getSongs().contains(path)) {
            getSongs().add(path);
            creatAndSaveFile(filename);
        }
    }

    public static void creatAndSaveFile(String fileName) throws IOException {
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
        button.addActionListener(e -> {
            for (i = 0; i < save.getSortedMusics().size(); i++) {
                if (e.getSource() == allMusidsInNewFrame[i]) {
                    try {
                        getMusicController().getInteractivePart().makeMusicPad(save.getSortedMusics().get(i));
                        addAndSave(save.getSortedMusics().get(i), getClickedButtonName());
                        if (sorryLable!=null) {
                            sorryLable.setText("");
                        }
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    }
                    getMusicController().getInteractivePart().revalidate();
                    getMusicController().getInteractivePart().repaint();

                }
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

    public void refreshPlayList(){
        this.removeAll();
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        Font font = new Font("MyFont", 1, 17);
        JLabel title = new JLabel("      PLAYLISTS");
        title.setForeground(Color.white);
        title.setFont(font);

        deletePlayList = new JButton("-");
        deletePlayList.setFont(font2);
        deletePlayList.setPreferredSize(new Dimension(100, 100));
        deletePlayList.setOpaque(true);
        deletePlayList.setBackground(Color.gray);
        deletePlayList.setFocusPainted(false);
        deletePlayList.setBorderPainted(false);

        addToPlaylist = new JButton("+");
        addToPlaylist.setFont(font2);
        addToPlaylist.setPreferredSize(new Dimension(100, 100));
        addToPlaylist.setOpaque(true);
        addToPlaylist.setBackground(Color.gray);
        addToPlaylist.setFocusPainted(false);
        addToPlaylist.setBorderPainted(false);

        allMusidsInNewFrame = new JButton[100];

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
//            if (i==save.getPlayListsName().size()-1)
//                add(Box.createVerticalStrut(25));
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

    public void exchangeMusicTiles(String fileName,ArrayList<String> arrayList)
    {
        exchangePannel=new JFrame("Exchange Pannel");
        exchangePannel.setVisible(true);
        exchangePannel.setSize(600, 600);
        exchangePannel.setLayout(new FlowLayout());

        for (int counter = 0; counter < arrayList.size(); counter++) {
            try {
                allMusidsInNewFrame[counter] = new JButton(getMusicController().getInteractivePart().findSongInfo(arrayList.get(counter), 0));
                exchangePannel.add(allMusidsInNewFrame[counter]);
                exchangeEventHandler(fileName,allMusidsInNewFrame[counter],arrayList,1);
                exchangeEventHandler(fileName,allMusidsInNewFrame[counter],arrayList,2);
                if (!filepath2.equals("") && !filepath1.equals("") )
                {
                    System.out.println("fwwwwwwwwwwwwwwwwwwwwwwwww");
                    break;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }

    public void exchangeEventHandler( String fileName,JButton button,ArrayList<String> arrayList,int index)
    {
        button.addActionListener(e -> {
            for (i = 0; i < arrayList.size(); i++) {
                if (e.getSource() == allMusidsInNewFrame[i]) {
                    if (index==1)
                    {
                        if (filepath1.equals("")) {
                            filepath1 = arrayList.get(i);
                            filepath2="";
                        }
                    }
                    else if (index==2)
                    {
                        if (filepath2.equals("") && !filepath2.equals(filepath1)) {
                            filepath2 = arrayList.get(i);
                            int index1=0,index2=0;
                            for (int j = 0; j < arrayList.size(); j++) {
                                if (arrayList.get(j).equals(filepath1))
                                {
                                    index1=j;
                                }
                                if (arrayList.get(j).equals(filepath2))
                                {
                                    index2=j;
                                }
                            }
                            Collections.swap(arrayList,index1,index2);
                            try {
                                creatAndSaveFile(fileName);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            getMusicController().getInteractivePart().removeAll();
                            for (int j = 0; j < getSongs().size(); j++) {
                                try {
                                    getMusicController().getInteractivePart().makeMusicPad(getSongs().get(j));
                                } catch (InvalidDataException e1) {
                                    e1.printStackTrace();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } catch (UnsupportedTagException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            filepath1="";
                            filepath2="";
                            revalidate();
                            repaint();
                            SwingUtilities.updateComponentTreeUI(FormGUI.getFormGUI());

                        }
                    }
                }
            }
        });
    }
}



