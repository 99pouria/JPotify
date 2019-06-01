package GUI;

import javax.swing.*;
import java.awt.*;

public class SelectionPanel extends JPanel {
    public SelectionPanel() {
        super();
        setPreferredSize(new Dimension(250,600));
        setOpaque(true);
        setBackground(Color.BLACK);

        MenuBar menu = new MenuBar();
        Libraries libraries = new Libraries();
        PlayLists playLists = new PlayLists();

        SpringLayout springLayout = new SpringLayout();

        setLayout(springLayout);

        springLayout.putConstraint(SpringLayout.NORTH, menu, 10, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, libraries, 20, SpringLayout.SOUTH, menu);
        springLayout.putConstraint(SpringLayout.EAST, libraries, 1, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.WEST, libraries, 1, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, playLists, 20, SpringLayout.SOUTH, libraries);

        add(menu);
        add(libraries);
        add(playLists);
    }
}
