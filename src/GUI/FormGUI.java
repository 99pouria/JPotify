package GUI;

import Logic.Save;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class FormGUI extends JFrame {
//    private final int WIDTH = 1500, HEIGHT = 1500;
    private PlayerBox playerBox;
    private CentralPanel centralPanel;

    public FormGUI() throws Exception {
        super();
        setTitle("JPotify");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playerBox = new PlayerBox();
        add(playerBox, BorderLayout.SOUTH);

        centralPanel = new CentralPanel(getPlayerBox().getSongInfo());
        add(centralPanel, BorderLayout.CENTER);
//        centralPanel.setPreferredSize(new Dimension(200, 350));

        FriendActivity friendActivity = new FriendActivity();
        add(friendActivity, BorderLayout.EAST);

        SelectionPanel selectionPanel = new SelectionPanel(getCentralPanel().getMusicController());
        add(selectionPanel, BorderLayout.WEST);

        pack();
        this.setVisible(true);
    }

    public PlayerBox getPlayerBox() {
        return playerBox;
    }

    public CentralPanel getCentralPanel() {
        return centralPanel;
    }

    public static void main(String[] args) throws Exception {

        FormGUI formGUI = new FormGUI();

//        Save save=new Save();
//        save.addMusic("F:\\Reza Bahram - Az Eshgh Bego.mp3",false);
//        save.addMusic("C:\\Users\\asus\\Downloads\\Music\\Reza Bahram - Shabhaye Bad Az To.mp3",false);
//        save.saveToFile();
    }
}
