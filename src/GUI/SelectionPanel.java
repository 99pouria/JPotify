package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SelectionPanel extends JPanel {
    public SelectionPanel(MusicController musicController) throws IOException, ClassNotFoundException {
        super();
//        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setOpaque(true);
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

//        MenuBar menu = new MenuBar();
//        menu.setAlignmentX(SwingConstants.RIGHT);
        Libraries libraries = new Libraries(musicController);
        PlayLists playLists = new PlayLists(musicController);
        SwitchPanel switchPanel = new SwitchPanel(musicController);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(null);
        panel.setOpaque(true);
        panel.setBackground(Color.BLACK);
        panel.add(playLists, BorderLayout.CENTER);
        panel.add(libraries, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(true);
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setBorder(null);
        /*
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);


        springLayout.putConstraint(SpringLayout.NORTH, menu, 10, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, switchPanel, 20, SpringLayout.SOUTH, menu);
        springLayout.putConstraint(SpringLayout.EAST, libraries, 1, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.WEST, libraries, 1, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.WEST, switchPanel, 1, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, switchPanel, 1, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.NORTH, libraries, 20, SpringLayout.SOUTH, switchPanel);
        springLayout.putConstraint(SpringLayout.NORTH, playLists, 20, SpringLayout.SOUTH, libraries);
        */

//        add(menu,BorderLayout.NORTH);
//        add(Box.createVerticalStrut(25));

        add(switchPanel,BorderLayout.NORTH);
        switchPanel.setPreferredSize(new Dimension(250,170));
//        add(Box.createVerticalStrut(35));
        add(scrollPane);
    }
}
