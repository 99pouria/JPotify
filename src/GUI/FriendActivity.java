package GUI;

import Logic.Client;
import Logic.Music;
import Logic.Server;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FriendActivity extends JPanel {
    private JPanel friendsList;
    private JButton[] buttons;

    public void setServer(Server server) {
        this.server = server;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private Server server;
    private Client client;
    private FormGUI formGUI;
    private HashMap<String, ArrayList<Music>> hashMap;

    public FriendActivity(FormGUI formGUI, Server server, Client client) {
        super();

        this.formGUI = formGUI;
        this.server = server;
        this.client = client;

        setPreferredSize(new Dimension(250, 600));
        setOpaque(true);
        setBackground(Color.BLACK);

        SpringLayout springLayout = new SpringLayout();

        setLayout(new BorderLayout());

        Font font = new Font("MyFont", 1, 19);

        JLabel title = new JLabel("Friend Activity");
        title.setFont(font);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(250, 70));
        JLabel line = new JLabel("  _________________________________");
        friendsList = new JPanel();
        friendsList.setOpaque(true);
        friendsList.setBackground(Color.BLACK);
        BoxLayout layout = new BoxLayout(friendsList, BoxLayout.Y_AXIS);
        friendsList.setLayout(layout);
        setForeground(Color.BLACK);

//        springLayout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
//        springLayout.putConstraint(SpringLayout.NORTH, line, 5, SpringLayout.SOUTH, title);
//        springLayout.putConstraint(SpringLayout.NORTH, friendsList, 5, SpringLayout.SOUTH, line);
//        springLayout.putConstraint(SpringLayout.SOUTH, friendsList, 0, SpringLayout.SOUTH, this);
//        springLayout.putConstraint(SpringLayout.EAST, friendsList, 0, SpringLayout.EAST, this);
//        springLayout.putConstraint(SpringLayout.WEST, friendsList, 0, SpringLayout.WEST, this);

        add(title, BorderLayout.NORTH);
        friendsList.add(line);
        add(friendsList, BorderLayout.CENTER);
    }

    public void refreshFriendList(HashMap<String, ArrayList<Music>> hashMap) {
        friendsList.removeAll();
        buttons = new JButton[hashMap.size()];
        int index = 0;
        for (Map.Entry<String, ArrayList<Music>> entry : hashMap.entrySet()) {
            buttons[index] = new JButton(entry.getKey() /*+ ": " + entry.getValue().get(entry.getValue().size()-1)*/);
            buttonHandler(buttons[index]);
            friendsList.add(buttons[index]);
            index++;
        }
        SwingUtilities.updateComponentTreeUI(friendsList);
    }

    public FormGUI getFormGUI() {
        return formGUI;
    }

    public void setHashMap(HashMap<String, ArrayList<Music>> hashMap) {
        this.hashMap = hashMap;
    }

    public void buttonHandler(JButton button) {

        Font font1 = new Font("Font2", Font.ITALIC, 20);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(font1);
        button.setContentAreaFilled(false);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);

        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getFormGUI().getCentralPanel().getMusicController().getInteractivePart().clearPanel();
                SwingUtilities.updateComponentTreeUI(getFormGUI().getCentralPanel().getMusicController().getInteractivePart());

                System.out.println(client.getHashMap().get(button.getText()));
                for (int i = 0; i < client.getHashMap().get(button.getText()).size(); i++) {
                    String songName = client.getHashMap().get(button.getText()).get(i).getSongName();
                    String artistName = client.getHashMap().get(button.getText()).get(i).getArtistName();
                    getFormGUI().getCentralPanel().getMusicController().getInteractivePart().makeSharedMusicTile(songName, artistName);
                }
                SwingUtilities.updateComponentTreeUI(getFormGUI().getCentralPanel().getMusicController().getInteractivePart());

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
}