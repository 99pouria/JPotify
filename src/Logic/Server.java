package Logic;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Server implements Runnable {

    private Socket socket;
    private ServerSocket server;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private volatile HashMap<String, ArrayList<Music>> hashMap;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            socket = server.accept();

            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(hashMap);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMap(String username, ArrayList<Music> music) {
        hashMap.put(username, music);
    }

    public void removeMap(String username) {
        hashMap.remove(username);
    }

    public void setHashMap(HashMap<String, ArrayList<Music>> hashMap) {
        this.hashMap = hashMap;
    }

    public HashMap<String, ArrayList<Music>> getHashMap() {
        return hashMap;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server( 1234);
        Client client = new Client("127.0.0.1", 1234);

        HashMap<String, ArrayList<Music>> test = new HashMap<>();
        ArrayList<Music> arrayList = new ArrayList<>();
        test.put("pooria", arrayList);

        server .setHashMap(test);

        Thread thread1 = new Thread(server);
        Thread thread2 = new Thread(client);

        thread1.start();
        thread2.start();
    }
}