package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
            buttons[i].setFocusPainted(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setFont(font1);
            buttons[i].setForeground(Color.WHITE);
            buttonEventHandler(buttons[i]);
            add(buttons[i]);
        }


        setOpaque(true);
        setBackground(Color.BLACK);
    }

    public void buttonEventHandler(JButton button){
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setForeground(Color.getHSBColor(104,69,55));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setForeground(Color.GREEN);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }
        });
    }
}
