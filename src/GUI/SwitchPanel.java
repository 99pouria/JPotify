package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwitchPanel extends JPanel implements ActionListener{

    private JFileChooser fileChooser;

    public SwitchPanel() {
        super();

        fileChooser = new JFileChooser();

        setLayout(new GridLayout(2, 1));

        JButton homeBtn = new JButton("Home");
        JButton browseBtn = new JButton("Browse");

        homeBtn.setOpaque(true);
        homeBtn.setBackground(Color.BLACK);
        homeBtn.setForeground(Color.white);

        browseBtn.setOpaque(true);
        browseBtn.setBackground(Color.BLACK);
        browseBtn.setForeground(Color.white);

        setOpaque(true);
        setBackground(Color.black);

        homeBtn.setHorizontalAlignment(SwingConstants.LEFT);
        browseBtn.setHorizontalAlignment(SwingConstants.LEFT);

        homeBtn.setContentAreaFilled(false);
        homeBtn.setFocusPainted(false);
        homeBtn.setBorderPainted(false);
        browseBtn.setContentAreaFilled(false);
        browseBtn.setFocusPainted(false);
        browseBtn.setBorderPainted(false);

        Font font = new Font("MyFont", 1, 17);

        homeBtn.setFont(font);
        browseBtn.setFont(font);

        buttonEventHandler(homeBtn);
        buttonEventHandler(browseBtn);

//        homeBtn.addActionListener(this);

        add(homeBtn);
        add(browseBtn);
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

    public void addActionListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
