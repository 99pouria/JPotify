package GUI;

import javax.swing.*;
import java.awt.*;

public class CentralPanel extends JPanel {
    public CentralPanel() {
        setLayout(new BorderLayout());

        TitleBar titleBar = new TitleBar();
        InteractivPart interactivPart = new InteractivPart();

        add(titleBar, BorderLayout.NORTH);
        add(interactivPart, BorderLayout.CENTER);

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


class InteractivPart extends JPanel {
    public InteractivPart() {
        super();
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
    }
}