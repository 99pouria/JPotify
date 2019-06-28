package GUI;

import Logic.Client;
import Logic.Music;
import Logic.Save;
import Logic.Server;

import javax.imageio.ImageIO;
import javax.script.ScriptContext;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FormGUI extends JFrame {
    //    private final int WIDTH = 1500, HEIGHT = 1500;
    private static FormGUI formGUI;
    private PlayerBox playerBox;
    private CentralPanel centralPanel;
    private FriendActivity friendActivity;
    private Server server;
    private Client client;

    public FormGUI() throws Exception {
        super();
        setTitle("JPotify");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playerBox = new PlayerBox();
        add(playerBox, BorderLayout.SOUTH);

        centralPanel = new CentralPanel(getPlayerBox().getSongInfo(), playerBox);
        add(centralPanel, BorderLayout.CENTER);
//        centralPanel.setPreferredSize(new Dimension(200, 350));

        friendActivity = new FriendActivity(this, server, client);
        add(friendActivity, BorderLayout.EAST);

        SelectionPanel selectionPanel = new SelectionPanel(getCentralPanel().getMusicController());
        add(selectionPanel, BorderLayout.WEST);

        pack();
        this.setVisible(true);
    }

    public Server getServer() {
        return server;
    }

    public Client getClient() {
        return client;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setClient(Client client) {
        this.client = client;
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
        Client client = new Client("127.0.0.1", 1234, formGUI);

        formGUI.setServer(server);
        formGUI.setClient(client);

        Thread thread1 = new Thread(server);
        Thread thread2 = new Thread(client);

        thread1.start();
        thread2.start();


        HashMap<String, ArrayList<Music>> test = new HashMap<>();
        ArrayList<Music> arrayList = new ArrayList<>();

        Music music = new Music("C:\\Users\\Pooria\\Downloads\\Music\\Hayedeh - Bezan Tar.mp3");
        arrayList.add(music);
        test.put("pooria", arrayList);
        server.setHashMap(test);
        client.setMessage("playlist");

        System.out.println(formGUI.getClient().getHashMap()+"llllllll");
        System.out.println(client.getHashMap()+"llllllll");
//
//        Music music1 = new Music("C:\\Users\\Pooria\\Downloads\\Music\\Hayedeh - Ashiooneh.mp3");
//        ArrayList<Music> arrayList1 = new ArrayList<>();
//        arrayList1.add(music1);
//        test.put("saeed", arrayList1);
//        client.setMessage("playlist");
    }
}
