package GUI;

import javax.swing.*;
import java.awt.*;

public class FormGUI extends JFrame {
//    private final int WIDTH = 1500, HEIGHT = 1500;

    public FormGUI() throws Exception {
        super();
        setTitle("JPotify");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CentralPanel centralPanel = new CentralPanel();
        add(centralPanel, BorderLayout.CENTER);
//        centralPanel.setPreferredSize(new Dimension(200, 350));

        FriendActivity friendActivity = new FriendActivity();
        add(friendActivity, BorderLayout.EAST);

        PlayerBox playerBox = new PlayerBox();
        add(playerBox, BorderLayout.SOUTH);

        SelectionPanel selectionPanel = new SelectionPanel();
        add(selectionPanel, BorderLayout.WEST);

        pack();
        this.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        FormGUI formGUI = new FormGUI();
    }
}
