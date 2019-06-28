package Logic;// A Java program for a Client

import GUI.FormGUI;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Client implements Runnable {
    private Socket socket;

    private volatile HashMap<String, ArrayList<Music>> hashMap;
    private FormGUI formGUI;
    private volatile String message = "";
    private volatile boolean isSet = false;

    public Client(String host, int port, FormGUI formGUI) throws IOException {
        socket = new Socket("127.0.0.1", port);
        System.out.println("Connected");
        this.formGUI = formGUI;
    }

    @Override
    public void run() {
        try {

            InputStream inputStream = socket.getInputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            while (true) {
                if (!isSet) {
                    break;
                }
//                Thread.sleep(1000);
                objectOutputStream.writeObject(message);
                objectOutputStream.flush();

                if (message.equals("playlist")) {
                    System.out.println("we a re here");
//                    hashMap = (HashMap<String, ArrayList<Music>>) objectInputStream.readObject();
                    hashMap = (HashMap<String, ArrayList<Music>>) objectInputStream.readObject();
                    formGUI.getFriendActivity().setClient(this);
                    formGUI.getFriendActivity().refreshFriendList(getHashMap());
                    formGUI.getFriendActivity().setHashMap(hashMap);
                } else if (message.equals("song")) {

                }
                message = "";
                isSet = false;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setHashMap(HashMap<String, ArrayList<Music>> hashMap) {
        this.hashMap = hashMap;
    }

    public String getMessage() {
        return message;
    }

    public synchronized HashMap<String, ArrayList<Music>> getHashMap() {
        return hashMap;
    }

    public synchronized void setMessage(String message) {
        isSet = true;
        this.message = message;
        System.out.println("here you change the message");
    }
}