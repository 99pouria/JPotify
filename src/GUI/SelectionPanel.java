package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SelectionPanel extends JPanel {
    public SelectionPanel(InteractivePart interactivePart) throws IOException {
        super();
        setPreferredSize(new Dimension(250,600));
        setOpaque(true);
        setBackground(Color.BLACK);

        MenuBar menu = new MenuBar();
        Libraries libraries = new Libraries();
        PlayLists playLists = new PlayLists();
        SwitchPanel switchPanel=new SwitchPanel(interactivePart);

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




        add(menu);
        add(libraries);
        add(playLists);
        add(switchPanel);
    }
}
