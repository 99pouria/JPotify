package GUI;

import javax.swing.*;
import java.awt.*;

public class SwitchPanel extends JPanel {
    public SwitchPanel() {
        super();
        setLayout(new GridLayout(2, 1));

        JButton homeBtn = new JButton("Home");
        JButton browseBtn = new JButton("Browse");

        homeBtn.setOpaque(true);
        homeBtn.setBackground(Color.BLACK);
        homeBtn.setForeground(Color.white);

        browseBtn.setOpaque(true);
        browseBtn.setBackground(Color.BLACK);
        browseBtn.setForeground(Color.white);

        setOpaque(true);
        setBackground(Color.black);

        homeBtn.setContentAreaFilled(false);
        homeBtn.setFocusPainted(false);
        homeBtn.setBorderPainted(false);
        browseBtn.setContentAreaFilled(false);
        browseBtn.setFocusPainted(false);
        browseBtn.setBorderPainted(false);

        Font font = new Font("MyFont", 1, 17);

        homeBtn.setFont(font);
        browseBtn.setFont(font);

        add(homeBtn);
        add(browseBtn);
    }
}
