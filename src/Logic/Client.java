package Logic;// A Java program for a Client

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Client implements Runnable {
    private Socket socket;
    private InputStream in;
    private ObjectInputStream os;
    private volatile HashMap<String, ArrayList<Music>> hashMap;

    public Client(String host, int port) throws IOException {
        socket = new Socket("127.0.0.1", port);
        System.out.println("Connected");
    }

    @Override
    public void run() {
        try {

            in = socket.getInputStream();
            os = new ObjectInputStream(in);
            hashMap = (HashMap<String, ArrayList<Music>>) os.readObject();
            socket.close();
            os.close();
            in.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setHashMap(HashMap<String, ArrayList<Music>> hashMap) {
        this.hashMap = hashMap;
    }

    public HashMap<String, ArrayList<Music>> getHashMap() {
        return hashMap;
    }
}