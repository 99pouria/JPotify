package GUI;

import javax.swing.*;
import java.awt.*;

public class CentralPanel extends JPanel {
    public CentralPanel() {
        setLayout(new BorderLayout());

        TitleBar titleBar = new TitleBar();
        InteractivePart interactivePart = new InteractivePart();

        add(titleBar, BorderLayout.NORTH);
        add(interactivePart, BorderLayout.CENTER);

    }
}

class TitleBar extends JPanel {
    private JPanel searchBar;
    private JLabel id;
    private JLabel line;
    private JButton priviousBtn;
    private JButton nextBtn;
    private JTextField searchField;

    public TitleBar() {
        super();
        setOpaque(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(700, 55));

        searchBar = new JPanel();
        id = new JLabel();
        line = new JLabel();


    }

    public void setSearchBarGUI(){
        priviousBtn=new JButton();
        nextBtn=new JButton();
        searchField=new JTextField();

        SpringLayout springLayout=new SpringLayout();
        setLayout(springLayout);


    }
}


class InteractivePart extends JPanel {
    public InteractivePart() {
        super();
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
    }
}