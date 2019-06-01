package GUI;

import javax.swing.*;
import java.awt.*;

public class SwitchPanel extends JPanel {
    public SwitchPanel() {
        super();
        setLayout(new GridLayout(2, 1));

        JButton homeBtn = new JButton("Home");
        JButton browseBtn = new JButton("Browse");

        add(homeBtn);
        add(browseBtn);
    }
}
