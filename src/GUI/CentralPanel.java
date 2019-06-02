package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
    private JPanel emptySpace;
    private JPanel id;
    private JLabel line;
    private JButton priviousBtn;
    private JButton nextBtn;
    private JTextField searchField;
    private JLabel idLabel;

    public TitleBar() {
        super();
        setOpaque(true);
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(700, 40));

        searchBar = new JPanel();
        emptySpace=new JPanel();
        id = new JPanel();
        line = new JLabel();

        setLayout(new BorderLayout());

        setSearchBarGUI();
        setIdGUI();
        setEmptySpaceGUI();
//        setLineGUI();

        add(searchBar,BorderLayout.WEST);
        add(emptySpace,BorderLayout.CENTER);
        add(id,BorderLayout.EAST);
//        add(line,BorderLayout.SOUTH);

    }

    public void setSearchBarGUI(){
        priviousBtn=new JButton();
        nextBtn=new JButton();
        searchField=new JTextField("Search");

        searchBar.setOpaque(true);
        searchBar.setBackground(Color.WHITE);

        searchBar.setPreferredSize(new Dimension(320,40));

        searchBar.setLayout(new BorderLayout());
//
//        Border roundedBorder = BorderFactory.createEmptyBorder();
//        searchField.setBorder(roundedBorder);

        priviousBtn.setPreferredSize(new Dimension(40,40));
        searchField.setPreferredSize(new Dimension(240,40));

        searchBar.add(priviousBtn,BorderLayout.WEST);
        searchBar.add(nextBtn,BorderLayout.CENTER);
        searchBar.add(searchField,BorderLayout.EAST);

    }

    public void setIdGUI(){
        id.setOpaque(true);
        id.setBackground(Color.WHITE);
        id.setPreferredSize(new Dimension(100,40));

        Font font1=new Font("Font1",1,20);

        idLabel=new JLabel("9731***");
        id.setFont(font1);

        id.add(idLabel);
    }

    public void setLineGUI(){
        line.setText("_______________________________________________________________________________________________________________________");
        line.setForeground(Color.DARK_GRAY);
    }

    public void setEmptySpaceGUI(){
        emptySpace.setOpaque(true);
        emptySpace.setBackground(Color.lightGray);
    }
}


class InteractivePart extends JPanel {
    public InteractivePart() {
        super();
        setOpaque(true);
        setBackground(Color.GRAY);
    }
}