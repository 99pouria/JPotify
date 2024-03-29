package GUI;

import Logic.RunMusic;
import Logic.Save;
import com.mpatric.mp3agic.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import javazoom.jl.decoder.JavaLayerException;

public class CentralPanel extends JPanel {
    private MusicController musicController;

    private static String path;
    private static Thread thread = null;
    private static boolean Playing = false;
    private static RunMusic runMusic;
    private static ArrayList<String> currentList;


    public CentralPanel(SongInfo songInfo, PlayerBox playerBox) throws IOException, InvalidDataException, UnsupportedTagException, JavaLayerException {
        setLayout(new BorderLayout());

        currentList=new ArrayList<>();

        musicController = new MusicController(songInfo, playerBox);
        TitleBar titleBar = new TitleBar(getMusicController(),getMusicController().getInteractivePart());

        add(titleBar, BorderLayout.NORTH);
        add(musicController, BorderLayout.CENTER);
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        CentralPanel.path = path;
    }

    public static Thread getThread() {
        return thread;
    }

    public static void setThread(Thread thread) {
        CentralPanel.thread = thread;
    }

    public MusicController getMusicController() {
        return musicController;
    }

    public static boolean isPlaying() {
        return Playing;
    }

    public static void setPlaying(boolean isPlaying) {
        CentralPanel.Playing = isPlaying;
    }

    public static RunMusic getRunMusic() {
        return runMusic;
    }

    public static void setRunMusic(RunMusic runMusic) {
        CentralPanel.runMusic = runMusic;
    }

}


class MusicController extends JPanel {
    private JPanel title;
    private InteractivePart interactivePart;
    private JScrollPane scrollableInteractive;

    public MusicController(SongInfo songInfo, PlayerBox playerBox) {
        setLayout(new BorderLayout());

        title = new JPanel();
        interactivePart = new InteractivePart(songInfo, playerBox);
        scrollableInteractive = new JScrollPane(interactivePart, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableInteractive.setBorder(null);

        title.setPreferredSize(new Dimension(700, 100));
        title.setOpaque(true);
        title.setBackground(Color.gray);

        add(title, BorderLayout.NORTH);
        add(scrollableInteractive, BorderLayout.CENTER);

    }

    public InteractivePart getInteractivePart() {
        return interactivePart;
    }

    public JPanel getTitle() {
        return title;
    }

    public void clearMusidControler() {
        getTitle().removeAll();
        getInteractivePart().clearPanel();
        revalidate();
        repaint();
    }

    public JScrollPane getScrollableInteractive() {
        return scrollableInteractive;
    }
}





class TitleBar extends JPanel implements MouseListener {
    private JPanel searchBar;
    private JPanel emptySpace;
    private JPanel id;
    private JLabel line;
    private JButton previousBtn;
    private JButton nextBtn;
    private JTextField searchField;
    private JLabel idLabel;
    private MusicController musicController;
    private InteractivePart interactivePart;

    public TitleBar(MusicController musicController,InteractivePart interactivePart) throws IOException, JavaLayerException {
        super();
        this.musicController=musicController;
        this.interactivePart=interactivePart;
        setOpaque(true);
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(700, 40));

        searchBar = new JPanel();
        emptySpace = new JPanel();
        id = new JPanel();
        line = new JLabel();

        setLayout(new BorderLayout());

        setSearchBarGUI();
        setIdGUI();
        setEmptySpaceGUI();

        add(searchBar, BorderLayout.WEST);
        add(emptySpace, BorderLayout.CENTER);
        add(id, BorderLayout.EAST);
    }

    public MusicController getMusicController() {
        return musicController;
    }

    public InteractivePart getInteractivePart() {
        return interactivePart;
    }

    public void setSearchBarGUI() throws IOException {
        Save save=new Save();
        previousBtn = new JButton();
        nextBtn = new JButton();
        searchField = new JTextField(" Search");

        searchBar.setOpaque(true);
        searchBar.setBackground(Color.GRAY);

        searchBar.setPreferredSize(new Dimension(320, 40));

        searchBar.setLayout(new BorderLayout());

        previousBtn.setPreferredSize(new Dimension(40, 50));
        searchField.setPreferredSize(new Dimension(240, 50));
        previousBtn.setPreferredSize(new Dimension(40, 40));
        searchField.setPreferredSize(new Dimension(240, 40));

        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()== KeyEvent.VK_ENTER) {
                    getInteractivePart().clearPanel();
                    getMusicController().getInteractivePart().clearPanel();
                    String substr=searchField.getText();
                    for (int i = 0; i < save.getSortedMusics().size(); i++) {
                        try {
                            if (getInteractivePart().findSongInfo(save.getSortedMusics().get(i),0).toLowerCase().contains(substr.toLowerCase()))
                            {
                                try {
                                    getMusicController().getInteractivePart().makeMusicPad(save.getSortedMusics().get(i));
                                    SwingUtilities.updateComponentTreeUI(FormGUI.getFormGUI());
                                    revalidate();
                                    repaint();
                                } catch (InvalidDataException e1) {
                                    e1.printStackTrace();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } catch (UnsupportedTagException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        Image img = ImageIO.read(getClass().getResource("icons\\top-screen-icons-2\\png\\002-left-arrow.png"));
        img = img.getScaledInstance(14, 14, java.awt.Image.SCALE_SMOOTH);
        previousBtn.setIcon(new ImageIcon(img));
        previousBtn.setPreferredSize(new Dimension(40, 40));
        previousBtn.setBorderPainted(false);
        previousBtn.setContentAreaFilled(false);
        previousBtn.setFocusPainted(false);


        previousBtn.addActionListener(e -> {
            if (CentralPanel.getRunMusic().isAlive()) {
                CentralPanel.getRunMusic().mp3Resume();
            } else {
                CentralPanel.getRunMusic().start();
            }
        });

        img = ImageIO.read(getClass().getResource("icons\\top-screen-icons-2\\png\\001-right-arrow.png"));
        img = img.getScaledInstance(14, 14, java.awt.Image.SCALE_SMOOTH);
        nextBtn.setIcon(new ImageIcon(img));
        nextBtn.setPreferredSize(new Dimension(40, 40));
        nextBtn.setBorderPainted(false);
        nextBtn.setContentAreaFilled(false);
        nextBtn.setFocusPainted(false);

        nextBtn.addActionListener(e -> {
            try {
                CentralPanel.getRunMusic().mp3Resume();
                CentralPanel.setPlaying(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        searchBar.add(previousBtn, BorderLayout.WEST);
        searchBar.add(nextBtn, BorderLayout.CENTER);
        searchBar.add(searchField, BorderLayout.EAST);

    }

    public void setIdGUI() {
        id.setOpaque(true);
        id.setBackground(Color.WHITE);
        id.setPreferredSize(new Dimension(100, 40));

        Font font1 = new Font("Font1", 1, 20);

        idLabel = new JLabel("9731***");
        id.setFont(font1);

        id.add(idLabel);

        searchField.addMouseListener(this);
    }

    public void setEmptySpaceGUI() {
        emptySpace.setOpaque(true);
        emptySpace.setBackground(Color.lightGray);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (searchField.getText().equals(" Search")) {
            searchField.setText("");
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


class InteractivePart extends JPanel implements AddIcon {
    private static int gridX = 0;
    private static int gridY = 0;
    private static int part = 0;
    private SongInfo songInfo;
    private PlayerBox playerBox;
    private Save save = new Save();
    private RunMusic runMusic;

    public static void setGridX(int gridX) {
        InteractivePart.gridX = gridX;
    }

    public static void setGridY(int gridY) {
        InteractivePart.gridY = gridY;
    }

    public InteractivePart(SongInfo songInfo, PlayerBox playerBox) {
        super();
        this.songInfo = songInfo;
        this.playerBox = playerBox;

        setOpaque(true);
        setBackground(Color.GRAY);

        setLayout(new GridBagLayout());

        if (Files.exists(Paths.get("C:\\Users\\Public\\Documents\\hashmap.ser"))) {
            save.readFile();
        }
    }

    public String findSongInfo(String filePath, int index) throws IOException {
        File file = new File(filePath);
        byte[] songName = new byte[30];
        byte[] fileContent = Files.readAllBytes(file.toPath());
        for (int i = 0; i < 30; i++) {
            songName[i] = fileContent[i + fileContent.length - 125 + index * 30];
        }
        String name = new String(songName);
//        System.out.println(name);
        return name;
    }


    public void showCoverImage(Container container, String path) throws InvalidDataException, IOException, UnsupportedTagException {
        JLabel label = new JLabel();

        Mp3File song = new Mp3File(path);
        if (song.hasId3v2Tag()) {
            ID3v2 id3v2tag = song.getId3v2Tag();
            byte[] imageData = id3v2tag.getAlbumImage();
            if (imageData != null) {
                Image img = ImageIO.read(new ByteArrayInputStream(imageData));
                img = img.getScaledInstance(400, 230, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                label.setIcon(icon);
            }
        }
        container.add(label);
    }

    public void makeMusicPad(String path) throws InvalidDataException, IOException, UnsupportedTagException {

        JPanel panel = new JPanel();
        JPanel coverImage = new JPanel();
        JPanel coverImageJust = new JPanel();
        JButton artistName = new JButton();
        JButton albumName = new JButton();


        panel.setLayout(new BorderLayout());

        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 2;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        gridX++;
        if (gridX == 4) {
            gridX = 0;
            gridY++;
        }
        add(panel, constraints);

        Font font = new Font("font", 1, 17);

        coverImage.setPreferredSize(new Dimension(330, 250));
        albumName.setPreferredSize(new Dimension(330, 35));
        artistName.setPreferredSize(new Dimension(330,30));

        coverImage.setOpaque(true);
        coverImage.setBackground(Color.DARK_GRAY);

        artistName.setOpaque(true);
        artistName.setBackground(Color.DARK_GRAY);
        artistName.setForeground(Color.white);
        artistName.setContentAreaFilled(false);
        artistName.setFocusPainted(false);
        artistName.setBorderPainted(false);
        artistName.setFont(font);
        artistName.setHorizontalAlignment(SwingConstants.LEFT);
        artistName.setAutoscrolls(true);

        albumName.setOpaque(true);
        albumName.setBackground(Color.DARK_GRAY);
        albumName.setForeground(Color.white);
        albumName.setContentAreaFilled(false);
        albumName.setFocusPainted(false);
        albumName.setBorderPainted(false);
        albumName.setHorizontalAlignment(SwingConstants.LEFT);
        albumName.setAutoscrolls(true);

        showCoverImage(coverImageJust, path);
        artistName.setText(findSongInfo(path, 0));
        albumName.setText(findSongInfo(path, 2));


        Font font2 = new Font("Font2", Font.BOLD, 20);
        coverImageJust.setOpaque(true);
        coverImageJust.setBackground(Color.gray);
        coverImage.setLayout(new BorderLayout());
        coverImage.add(coverImageJust, BorderLayout.CENTER);
        JButton delete = new JButton();
        delete.setOpaque(true);
        delete.setBackground(Color.gray);
        delete.setBorderPainted(false);
        delete.setFocusPainted(false);
        coverImage.add(delete, BorderLayout.NORTH);

        focusListener(coverImage, delete);
        focusListener(artistName, delete);
        focusListener(albumName, delete);

        delete.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (getPart() == 0) {
                    try {
                        deleteMusicTiles(panel, path, InteractivePart.getPart());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
                if (getPart() == 1) {
                    try {
                        deleteMusicTilesInPlayList(panel, PlayLists.getTheCurrentFilePath(), path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
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
                delete.setFont(font2);
                delete.setText("_");
                delete.setHorizontalAlignment(SwingConstants.RIGHT);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                delete.setText("");
            }
        });

        panel.add(coverImage, BorderLayout.NORTH);
        panel.add(artistName, BorderLayout.CENTER);
        panel.add(albumName, BorderLayout.SOUTH);


        Save save = new Save();
        if (Files.exists(Paths.get("C:\\Users\\Public\\Documents\\hashmap.ser")))
            save.readFile();

        artistName.addActionListener(e -> {
            if (CentralPanel.getRunMusic() != null) {
                CentralPanel.getRunMusic().mp3Pause();/*stop*/
            }
            CentralPanel.setPath(path);
            runMusic = new RunMusic(CentralPanel.getPath(),songInfo);
            CentralPanel.setRunMusic(runMusic);
            try {
                if (songInfo != null) {
                    songInfo.changeSongInfo(path);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            save.deleteAndReAddMusic(path);
            save.saveToFile();
        });

        albumName.addActionListener(e -> {
            if (CentralPanel.getRunMusic() != null) {
                CentralPanel.getRunMusic().mp3Pause();/*stop*/
            }
            CentralPanel.setPath(path);
            runMusic = new RunMusic(CentralPanel.getPath(),songInfo);
            CentralPanel.setRunMusic(runMusic);
            try {
                if (songInfo != null) {
                    songInfo.changeSongInfo(path);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            save.deleteAndReAddMusic(path);
            save.saveToFile();
//            }

        });

        coverImage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (CentralPanel.getRunMusic() != null) {
                    CentralPanel.getRunMusic().mp3Pause();/*stop*/
                }
                CentralPanel.setPath(path);
                runMusic = new RunMusic(CentralPanel.getPath(),songInfo);
                CentralPanel.setRunMusic(runMusic);
                try {
                    if (songInfo != null) {
                        songInfo.changeSongInfo(path);
                    }
                } catch (IOException | InvalidDataException | UnsupportedTagException e1) {
                    e1.printStackTrace();
                }
                save.deleteAndReAddMusic(path);
                save.saveToFile();
                try {
                    createIcon(playerBox.getPlayerTools().getPlay(), "icons\\my-icons-collection-2\\png\\001-pause.png", 35, 35);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                CentralPanel.getRunMusic().start();
                CentralPanel.setPlaying(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public void makeMusicTiles(int choice) throws InvalidDataException, IOException, UnsupportedTagException {
        if (choice == 1) {
            for (int i = 0; i < save.getSortedMusics().size(); i++) {
                makeMusicPad(save.getSortedMusics().get(i));
            }
            revalidate();
            repaint();
            updateUI();
        }
        if (choice == 2) {
            for (int i = 0; i < save.getSortedMusics().size(); i++) {

                for (Map.Entry<String, Boolean> entry :
                        save.getMusics().entrySet()) {
                    if (entry.getKey().equals(save.getSortedMusics().get(i))) {
                        if (entry.getValue())
                            makeMusicPad(entry.getKey());
                        break;
                    }
                }
            }
            revalidate();
            repaint();
            updateUI();
        }
    }

    public void deleteMusicTiles(JPanel panel, String path, int index) throws IOException, ClassNotFoundException {
        remove(panel);
        if (index == 0) {
            save.deletTile(path);
            save.saveToFile();
        }
        revalidate();
        repaint();
    }

    public void deleteMusicTilesInPlayList(JPanel panel, String filename, String path) throws IOException, ClassNotFoundException {
        remove(panel);
        PlayLists.getSongs().clear();
        PlayLists.readFile(filename);
        PlayLists.getSongs().remove(path);
        PlayLists.createAndSaveFile(filename);
        revalidate();
        repaint();
    }

    public void clearPanel() {
        this.removeAll();
        setGridX(0);
        setGridY(0);
        System.gc();
    }

    public void focusListener(Container container, JButton button) {
        container.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.gray);

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

    public static void setPart(int part) {
        InteractivePart.part = part;
    }

    public static int getPart() {
        return part;
    }

    public void makeSharedMusicTile(String sharedSongName,String sharedArtistName){

        JPanel panel = new JPanel();
        JPanel coverImage = new JPanel();
        JPanel coverImageJust = new JPanel();
        JButton artistName = new JButton();
        JButton albumName = new JButton();


        panel.setLayout(new BorderLayout());

        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
//        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 2;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        gridX++;
        if (gridX == 4) {
            gridX = 0;
            gridY++;
        }
        add(panel, constraints);

        Font font = new Font("font", 1, 17);

        coverImage.setPreferredSize(new Dimension(330, 250));
        albumName.setPreferredSize(new Dimension(330, 35));
        artistName.setPreferredSize(new Dimension(330,30));

        coverImage.setOpaque(true);
        coverImage.setBackground(Color.DARK_GRAY);

        artistName.setOpaque(true);
        artistName.setBackground(Color.DARK_GRAY);
        artistName.setForeground(Color.white);
        artistName.setContentAreaFilled(false);
        artistName.setFocusPainted(false);
        artistName.setBorderPainted(false);
        artistName.setFont(font);
        artistName.setHorizontalAlignment(SwingConstants.LEFT);
        artistName.setAutoscrolls(true);

        albumName.setOpaque(true);
        albumName.setBackground(Color.DARK_GRAY);
        albumName.setForeground(Color.white);
        albumName.setContentAreaFilled(false);
        albumName.setFocusPainted(false);
        albumName.setBorderPainted(false);
        albumName.setHorizontalAlignment(SwingConstants.LEFT);
        albumName.setAutoscrolls(true);

//        showCoverImage(coverImageJust, path);
        artistName.setText(sharedSongName);
        albumName.setText(sharedSongName);

        panel.add(coverImage, BorderLayout.NORTH);
        panel.add(artistName, BorderLayout.CENTER);
        panel.add(albumName, BorderLayout.SOUTH);
    }
}