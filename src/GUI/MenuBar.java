package GUI;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JPanel {
    public MenuBar() {
        super();
        Font font = new Font("MyFont", 1, 24);


        JMenu menu = new JMenu("   ...");
        menu.setFont(font);
        menu.setForeground(Color.white);
        menu.setBackground(Color.BLACK);
        menu.setOpaque(true);
        menu.setBorderPainted(false);

        add(menu);
    }
}
