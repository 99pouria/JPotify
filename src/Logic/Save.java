package Logic;

import java.io.*;
import java.util.HashMap;

public class Save {
    private static HashMap<String, Boolean> musics = new HashMap<>();

    public void addMusic(String path, boolean isLiked) {
        musics.put(path, isLiked);
    }

    public HashMap<String, Boolean> getMusics() {
        return musics;

    }

    public void setMusics(HashMap<String, Boolean> musics) {
        this.musics = musics;
    }

    public void saveToFile() {
        try {
            FileOutputStream fos =
                    new FileOutputStream("C:\\Users\\Public\\Documents\\hashmap.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(getMusics());
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public HashMap<String, Boolean> readFile() {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Documents\\hashmap.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            setMusics((HashMap) ois.readObject());
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        System.out.println("Deserialized HashMap..");
        return getMusics();
    }
}


