package GUI;

import javax.swing.*;
import java.awt.*;

public class PlayLists extends JPanel {
    public PlayLists() {
        super();
        setLayout(new GridLayout(3,1));

        Font font = new Font("MyFont", 1, 17);
        Font font1=new Font("Font1",Font.ITALIC,15);

        JButton[] buttons = new JButton[2];
        JLabel title = new JLabel("      PLAYLISTS");
        title.setForeground(Color.white);
        title.setFont(font);

        title.setPreferredSize(new Dimension(250, 45));
        add(title);

        for (int i = 0; i < 2; i++) {
            buttons[i] = new JButton();
            buttons[i].setPreferredSize(new Dimension(250, 45));
            buttons[i].setHorizontalAlignment(SwingConstants.LEFT);
            buttons[i].setText("  playlist " + (i+1));
            buttons[i].setContentAreaFilled(false);
//            btn3.setFocusPainted(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setFont(font1);
            buttons[i].setForeground(Color.WHITE);
            add(buttons[i]);
        }


        setOpaque(true);
        setBackground(Color.BLACK);
    }
}
