package Logic;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Server implements Runnable {

    private Socket socket;
    private ServerSocket server;
    private volatile HashMap<String, ArrayList<Music>> hashMap;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void run() {
        try {
            socket = server.accept();

            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                String request = (String) objectInputStream.readObject();


                if (request.equals("playlist")) {

                    objectOutputStream.writeObject(hashMap);
                    outputStream.flush();

                } else if (request.equals("song")) {

                }
            }


        } catch (IOException | ClassNotFoundException e) {
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
}