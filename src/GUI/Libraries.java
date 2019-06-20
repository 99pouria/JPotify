package GUI;

import Logic.Save;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class Libraries extends JPanel {
    private JButton[] buttons;
    private Save save=new Save();

    public Libraries() {
        super();
        setLayout(new GridLayout(5,1));

        Font font = new Font("MyFont", 1, 17);
        Font font1=new Font("Font1",Font.ITALIC,15);

        JLabel title = new JLabel("     YOUR LIBRARY");
        title.setForeground(Color.white);


        title.setPreferredSize(new Dimension(250, 45));
        title.setFont(font);
        add(title);

        buttons = new JButton[4];
        buttons[0] = new JButton("Songs");
        buttons[1] = new JButton("Albums");
        buttons[2] = new JButton("Artists");
        buttons[3] = new JButton("Add To Library");

        for (int i = 0; i < 4; i++) {
            buttons[i].setPreferredSize(new Dimension(250, 50));
            buttons[i].setHorizontalAlignment(SwingConstants.LEFT);
            buttons[i].setContentAreaFilled(false);
            buttons[i].setFocusPainted(false);
            buttons[i].setBorderPainted(false);
            buttons[i].setFont(font1);
            buttons[i].setForeground(Color.WHITE);
            buttonEventHandler(buttons[i]);
            add(buttons[i]);
        }
        
        

        setOpaque(true);
        setBackground(Color.BLACK);
    }


    public void buttonEventHandler(JButton button){
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource()==buttons[0])
                {
//                    for (Map.Entry<String,Boolean> entry:
//                         save.readFile().entrySet()) {
//                        System.out.println(entry.getKey());
//                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setForeground(Color.getHSBColor(104,69,55));
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

    public void allSongsPage(){

    }
}
