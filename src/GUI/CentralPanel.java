package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class CentralPanel extends JPanel {
    public CentralPanel() throws IOException {
        setLayout(new BorderLayout());

        TitleBar titleBar = new TitleBar();
        InteractivePart interactivePart = new InteractivePart();

        add(titleBar, BorderLayout.NORTH);
        add(interactivePart, BorderLayout.CENTER);

    }
}

class TitleBar extends JPanel {
    private JPanel searchBar;
    private JPanel emptySpace;
    private JPanel id;
    private JLabel line;
    private JButton previousBtn;
    private JButton nextBtn;
    private JTextField searchField;
    private JLabel idLabel;

    public TitleBar() throws IOException {
        super();
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
//        setLineGUI();

        add(searchBar, BorderLayout.WEST);
        add(emptySpace, BorderLayout.CENTER);
        add(id, BorderLayout.EAST);
//        add(line,BorderLayout.SOUTH);

    }

    public void setSearchBarGUI() throws IOException {
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

        Image img = ImageIO.read(getClass().getResource("icons\\top-screen-icons-2\\png\\002-left-arrow.png"));
        img = img.getScaledInstance(14, 14, java.awt.Image.SCALE_SMOOTH);
        previousBtn.setIcon(new ImageIcon(img));
        previousBtn.setPreferredSize(new Dimension(40, 40));
        previousBtn.setBorderPainted(false);
        previousBtn.setContentAreaFilled(false);
        previousBtn.setFocusPainted(false);

        img = ImageIO.read(getClass().getResource("icons\\top-screen-icons-2\\png\\001-right-arrow.png"));
        img = img.getScaledInstance(14, 14, java.awt.Image.SCALE_SMOOTH);
        nextBtn.setIcon(new ImageIcon(img));
        nextBtn.setPreferredSize(new Dimension(40, 40));
        nextBtn.setBorderPainted(false);
        nextBtn.setContentAreaFilled(false);
        nextBtn.setFocusPainted(false);


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
    }

    public void setLineGUI() {
        line.setText("_______________________________________________________________________________________________________________________");
        line.setForeground(Color.DARK_GRAY);
    }

    public void setEmptySpaceGUI() {
        emptySpace.setOpaque(true);
        emptySpace.setBackground(Color.lightGray);
    }
}


class InteractivePart extends JPanel {
    public InteractivePart() throws IOException {
        super();
        setOpaque(true);
        setBackground(Color.GRAY);
        findSongInfo("C:\\Users\\Pooria\\Downloads\\Music\\Hayedeh - Ashiooneh.mp3", 0);
        findSongInfo("C:\\Users\\Pooria\\Downloads\\Music\\Hayedeh - Ashiooneh.mp3", 1);
        findSongInfo("C:\\Users\\Pooria\\Downloads\\Music\\Hayedeh - Ashiooneh.mp3", 2);
    }

    public String findSongInfo (String filePath, int index) throws IOException {
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
}