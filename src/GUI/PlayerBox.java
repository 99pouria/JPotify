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
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.nio.file.Files;

public class PlayerBox extends JPanel {
    private SongInfo songInfo;

    private static String filePath;

    public PlayerBox() throws IOException, InvalidDataException, UnsupportedTagException, JavaLayerException {
        setPreferredSize(new Dimension(1200, 70));
        setOpaque(true);
        setBackground(Color.GRAY);
        setLayout(new BorderLayout());

        songInfo = new SongInfo("C:\\Users\\asus\\Music\\Reza Bahram - Az Eshgh Bego.mp3");
        PlayerTools playerTools = new PlayerTools(getSongInfo());
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

class SongInfo extends JPanel implements AddIcon {
    private JPanel artWork;
    private JPanel info;
    private JPanel like;
    private String filePath;
    private JLabel label = null;
    private CreateInfoPanel createInfoPanel;
    private createArtWorkPanel createArtWorkPanel;
    private likeIcon likeIcon;

    public SongInfo(String path) throws IOException, InvalidDataException, UnsupportedTagException {

        super();
        filePath = "F:\\Reza Bahram - Az Eshgh Bego.mp3";
        setPreferredSize(new Dimension(250, 70));
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());
        createInfoPanel = new CreateInfoPanel(path);
        add(info, BorderLayout.CENTER);
        likeIcon = new likeIcon(filePath);
        add(like, BorderLayout.EAST);
        createArtWorkPanel = new createArtWorkPanel(path);
        add(artWork, BorderLayout.WEST);
    }

    public void changeSongInfo(String filePath) throws IOException, InvalidDataException, UnsupportedTagException {
        createInfoPanel.changeSongInfo(filePath);
        createArtWorkPanel.changeSongInfo(filePath);
    }


    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    class CreateInfoPanel {

        JLabel songName;
        JLabel artistName;

        public CreateInfoPanel(String path) throws IOException {
            info = new JPanel(new BorderLayout());
            info.setLayout(new BorderLayout());
            setFilePath(path);
            songName = new JLabel(findSongInfo(filePath, 0));
            artistName = new JLabel(findSongInfo(filePath, 1));
            songName.setForeground(Color.WHITE);
            songName.setPreferredSize(new Dimension(135, 35));
            artistName.setForeground(Color.WHITE);
            info.add(songName, BorderLayout.NORTH);
            info.add(artistName, BorderLayout.CENTER);
            info.setOpaque(true);
            info.setBackground(Color.DARK_GRAY);
        }


        public void changeSongInfo(String newPath) throws IOException {
            songName.setText(findSongInfo(newPath, 0));
            artistName.setText(findSongInfo(newPath, 1));
            likeIcon.setPlayedSongPath(newPath);
            likeIcon.changeLikeIcon(newPath);
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
        System.out.println(name);
        return name;
    }


    class createArtWorkPanel {
        public createArtWorkPanel(String filePath) throws InvalidDataException, IOException, UnsupportedTagException {
            artWork = new JPanel();
            artWork.setPreferredSize(new Dimension(70, 70));
            artWork.setOpaque(true);
            artWork.setBackground(Color.DARK_GRAY);
            JPanel pic = new JPanel();
            pic.setPreferredSize(new Dimension(60, 60));

//            showCoverImage(artWork, filePath);
        }

        public void changeSongInfo(String path) throws InvalidDataException, IOException, UnsupportedTagException {
            showCoverImage(artWork, path);
        }
    }

    class likeIcon implements AddIcon {
        JButton button;
        String playedSongPath;

        public likeIcon(String path) throws IOException {
            like = new JPanel();
            like.setPreferredSize(new Dimension(50, 50));
            like.setOpaque(true);
            like.setBackground(Color.DARK_GRAY);
            button = new JButton();
            Save save = new Save();
            playedSongPath = path;
            save.readFile();
            save.addMusic("C:\\Users\\asus\\Music\\Reza Bahram - Az Eshgh Bego.mp3",true);
            if (save.getMusics().get(path)) {
                createIcon(button, "icons\\colored-buttons-2\\png\\003-favorite-heart-button.png", 18, 18);
            } else {
                createIcon(button, "icons\\my-icons-collection-2\\png\\004-heart.png", 18, 18);
            }


            likeEventHandler("icons\\colored-buttons-1\\png\\003-favorite-heart-button.png", "icons\\colored-buttons-2\\png\\003-favorite-heart-button.png", "icons\\my-icons-collection-3\\png\\004-heart.png", "icons\\my-icons-collection-2\\png\\004-heart.png");
            like.add(button);
        }

        public void setPlayedSongPath(String playedSongPath) {
            this.playedSongPath = playedSongPath;
        }

        public void likeEventHandler(String icon1, String icon2, String icon3, String icon4) {
            Save save = new Save();
            save.readFile();
            button.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }


                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (save.getMusics().get(playedSongPath)) {
                        try {
                            createIcon(button, icon1, 18, 18);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        try {
                            createIcon(button, icon3, 18, 18);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (save.getMusics().get(playedSongPath)) {
                        try {
                            createIcon(button, icon4, 18, 18);
                            save.getMusics().put(playedSongPath, false);
                            save.saveToFile();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        try {
                            createIcon(button, icon2, 18, 18);
                            save.getMusics().put(playedSongPath, true);
                            save.saveToFile();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (save.getMusics().get(playedSongPath)) {
                        try {
                            createIcon(button, icon2, 18, 18);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        try {
                            createIcon(button, icon4, 18, 18);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
        }


        public void changeLikeIcon(String newPath) throws IOException {
            Save save = new Save();
            save.readFile();
            System.out.println(save.getMusics().get(newPath) + "000000000000000000000000000000000000000000000000000000000000000000");
            if (save.getMusics().get(newPath)) {
                createIcon(button, "icons\\colored-buttons-2\\png\\003-favorite-heart-button.png", 18, 18);
            } else {
                createIcon(button, "icons\\my-icons-collection-2\\png\\004-heart.png", 18, 18);
            }
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

    public void showCoverImage(Container container, String filePath) throws InvalidDataException, IOException, UnsupportedTagException {
        if (label == null) {
            label = new JLabel();
            Mp3File song = new Mp3File(filePath);
            if (song.hasId3v2Tag()) {
                ID3v2 id3v2tag = song.getId3v2Tag();
                byte[] imageData = id3v2tag.getAlbumImage();
                if (imageData != null) {
                    System.out.println("debug:: imageData is not null");
                    Image img = ImageIO.read(new ByteArrayInputStream(imageData));
                    img = img.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(img);
                    label.setIcon(icon);
                }
            }
        } else {
            label.setIcon(null);
            Mp3File song = new Mp3File(filePath);
            if (song.hasId3v2Tag()) {
                ID3v2 id3v2tag = song.getId3v2Tag();
                byte[] imageData = id3v2tag.getAlbumImage();
                if (imageData != null) {
                    System.out.println("debug:: imageData is not null");
                    Image img = ImageIO.read(new ByteArrayInputStream(imageData));
                    img = img.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(img);
                    label.setIcon(icon);

                }
            }
        }

        container.add(label);

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