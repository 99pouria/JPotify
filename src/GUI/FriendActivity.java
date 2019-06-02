package GUI;

import javax.swing.*;
import java.awt.*;

public class FriendActivity extends JPanel {
    public FriendActivity() {
        super();
        setPreferredSize(new Dimension(250, 600));
        setOpaque(true);
        setBackground(Color.BLACK);

        SpringLayout springLayout = new SpringLayout();

        setLayout(springLayout);

        Font font = new Font("MyFont", 1, 19);

        JLabel title = new JLabel("     Friend Activity");
        title.setFont(font);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        JLabel line = new JLabel("  _________________________________");
        JPanel friendsList = new JPanel();
        friendsList.setOpaque(true);
        friendsList.setBackground(Color.BLACK);
        setForeground(Color.BLACK);

        springLayout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, line, 5, SpringLayout.SOUTH, title);
        springLayout.putConstraint(SpringLayout.NORTH, friendsList, 5, SpringLayout.SOUTH, line);
        springLayout.putConstraint(SpringLayout.SOUTH, friendsList, 0, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.EAST, friendsList, 0, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.WEST, friendsList, 0, SpringLayout.WEST, this);

        add(title);
        add(line);
        add(friendsList);

    }
}
