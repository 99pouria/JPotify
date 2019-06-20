package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayLists extends JPanel {
    public PlayLists() {
        super();
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);

        Font font = new Font("MyFont", 1, 17);
        JLabel title = new JLabel("      PLAYLISTS");
        title.setForeground(Color.white);
        title.setFont(font);

        JButton[] buttons = new JButton[50];
        JButton addPlaylistButton = new JButton();
        createButton(addPlaylistButton, "+ Add playlist");
        add(title);
        add(Box.createVerticalStrut(25));

        for (int i = 0; i < 2; i++) {
            buttons[i] = new JButton();
            createButton(buttons[i], "playList" + i);
            add(buttons[i]);
            add(Box.createVerticalStrut(25));
        }

        add(addPlaylistButton);
        add(Box.createVerticalStrut(25));
        setOpaque(true);
        setBackground(Color.BLACK);
    }

    public void createButton(JButton button, String name) {

        Font font1 = new Font("Font1", Font.ITALIC, 15);

        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setText("  " + name);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(font1);
        button.setForeground(Color.WHITE);
        buttonEventHandler(button);
    }

    public void buttonEventHandler(JButton button) {
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setForeground(Color.getHSBColor(104, 69, 55));
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
