package GUI;

import Logic.Music;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FriendActivity extends JPanel {
    private JPanel friendsList;
    private JButton[] buttons;

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
        friendsList = new JPanel();
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

    public void refreshFriendList(HashMap<String, ArrayList<Music>> hashMap) {
        friendsList.removeAll();
        buttons = new JButton[hashMap.size()];
        int index = 0;
        for (Map.Entry<String, ArrayList<Music>> entry : hashMap.entrySet()) {
            buttons[index] = new JButton(entry.getKey() + ": " + entry.getValue().get(entry.getValue().size()-1));
            friendsList.add(buttons[index]);
            index++;
        }
        SwingUtilities.updateComponentTreeUI(friendsList);
    }
}
