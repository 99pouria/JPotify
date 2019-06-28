package GUI;

import Logic.Client;
import Logic.Music;
import Logic.Save;
import Logic.Server;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FormGUI extends JFrame {
    private PlayerBox playerBox;
    private CentralPanel centralPanel;
    private FriendActivity friendActivity;

    private static FormGUI formGUI;

    public FormGUI() throws Exception {
        super();
        setTitle("JPotify");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playerBox = new PlayerBox();
        add(playerBox, BorderLayout.SOUTH);

        centralPanel = new CentralPanel(getPlayerBox().getSongInfo(), playerBox);
        add(centralPanel, BorderLayout.CENTER);

        friendActivity = new FriendActivity();
        add(friendActivity, BorderLayout.EAST);

        SelectionPanel selectionPanel = new SelectionPanel(getCentralPanel().getMusicController());
        add(selectionPanel, BorderLayout.WEST);

        pack();
        this.setVisible(true);
    }

    public FriendActivity getFriendActivity() {
        return friendActivity;
    }

    public static FormGUI getFormGUI() {
        return formGUI;
    }

    public PlayerBox getPlayerBox() {
        return playerBox;
    }

    public CentralPanel getCentralPanel() {
        return centralPanel;
    }

    public static void main(String[] args) throws Exception {
        formGUI = new FormGUI();
        Server server = new Server(1234);
        Client client = new Client("127.0.0.1", 1234);

        HashMap<String, ArrayList<Music>> test = new HashMap<>();
        ArrayList<Music> arrayList = new ArrayList<>();
        Music music = new Music("D:\\Mehdi Ahmadvand - Dard.wav");
        arrayList.add(music);
        test.put("pooria", arrayList);

        System.out.println(music);

        server.setHashMap(test);

        Thread thread1 = new Thread(server);
        Thread thread2 = new Thread(client);

        thread1.start();
        thread2.start();

        formGUI.getFriendActivity().refreshFriendList(client.getHashMap());

        Music music1 = new Music("D:\\Mehdi Ahmadvand - Dard.wav");
        ArrayList arrayList1 = new ArrayList();
        arrayList1.add(music1);
        test.put("saeed", arrayList1);

        Thread.sleep(5000);

        formGUI.getFriendActivity().refreshFriendList(client.getHashMap());
    }
}
