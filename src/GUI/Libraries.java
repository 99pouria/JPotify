package GUI;

import javax.swing.*;
import java.awt.*;

public class Libraries extends JPanel {
    public Libraries() {
        super();
        setLayout(new GridLayout(7,1));

        JLabel title = new JLabel("LIBRARIES");
        JButton[] buttons = new JButton[6];

        Font font = new Font("MyFont", 7, 17);

        title.setPreferredSize(new Dimension(250, 45));
        title.setFont(font);
        add(title);

        for (int i = 0; i < 6; i++) {
            buttons[i] = new JButton();
            buttons[i].setPreferredSize(new Dimension(250, 45));
            buttons[i].setHorizontalAlignment(SwingConstants.LEFT);
            buttons[i].setText("library " + (i+1));
            add(buttons[i]);
        }

        setOpaque(true);
        setBackground(Color.CYAN);
    }
}
