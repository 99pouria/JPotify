package GUI;

import javax.swing.*;
import java.awt.*;

public class Libraries extends JPanel {
    public Libraries() {
        super();
        setLayout(new GridLayout(7,1));

        Font font = new Font("MyFont", 1, 17);
        Font font1=new Font("Font1",Font.ITALIC,15);

        JLabel title = new JLabel("     Your LIBRARIES");
        title.setForeground(Color.white);
        JButton[] buttons = new JButton[6];


        title.setPreferredSize(new Dimension(250, 45));
        title.setFont(font);
        add(title);

        for (int i = 0; i < 6; i++) {
            buttons[i] = new JButton();
            buttons[i].setPreferredSize(new Dimension(250, 50));
            buttons[i].setHorizontalAlignment(SwingConstants.LEFT);
            buttons[i].setContentAreaFilled(false);
//            btn3.setFocusPainted(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setFont(font1);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setText("  library " + (i+1));
            add(buttons[i]);
        }

        setOpaque(true);
        setBackground(Color.BLACK);
    }
}
