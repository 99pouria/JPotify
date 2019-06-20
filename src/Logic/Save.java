package Logic;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Save {
    private static HashMap<String, Boolean> musics = new HashMap<>();
    private static ArrayList<String> sortedMusics = new ArrayList<>();
    private static ArrayList<String> sortedMusicsCopy = new ArrayList<>();
    private static boolean isCopied = true;

    public Save() {
        if (isCopied) {
            readFile();
            setSortedMusicsCopy(getSortedMusics());
            isCopied=false;
        }
    }

    public void addMusic(String path, boolean isLiked) {
        if (!getMusics().containsKey(path)) {
            sortedMusics.add(path);
            sortedMusicsCopy.add(path);
        }
        musics.put(path, isLiked);
        saveToFile();
    }


    public void deleteAndReAddMusic(String path) {
        sortedMusics.remove(path);
        sortedMusics.add(path);
    }

    public HashMap<String, Boolean> getMusics() {
        return musics;
    }

    public void setMusics(HashMap<String, Boolean> musics) {
        this.musics = musics;
    }

    public ArrayList<String> getSortedMusics() {
        return sortedMusics;
    }

    public void setSortedMusics(ArrayList<String> sortedMusics) {
        this.sortedMusics = sortedMusics;
    }

    public void saveToFile() {
        try {
            FileOutputStream fos =
                    new FileOutputStream("C:\\Users\\Public\\Documents\\hashmap.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(getMusics());
            oos.close();
            fos.close();

            FileOutputStream fosOfArreyList =
                    new FileOutputStream("C:\\Users\\Public\\Documents\\arreylist.ser");
            ObjectOutputStream oosOfArreyList = new ObjectOutputStream(fosOfArreyList);
            oosOfArreyList.writeObject(getSortedMusics());
            fosOfArreyList.close();
            oosOfArreyList.close();
            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public ArrayList<String> readFile() {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Documents\\hashmap.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            setMusics((HashMap) ois.readObject());
            ois.close();
            fis.close();


            FileInputStream fisOfArreyList = new FileInputStream("C:\\Users\\Public\\Documents\\arreylist.ser");
            ObjectInputStream oisOfArreyList = new ObjectInputStream(fisOfArreyList);
            setSortedMusics((ArrayList) oisOfArreyList.readObject());
            oisOfArreyList.close();
            fisOfArreyList.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        System.out.println("Deserialized HashMap..");
//        setSortedMusicsCopy(getSortedMusics());
        return getSortedMusics();
    }

    public void setSortedMusicsCopy(ArrayList<String> sortedMusicsCopy) {
        Save.sortedMusicsCopy = sortedMusicsCopy;
    }

    public ArrayList<String> getSortedMusicsCopy() {
        return sortedMusicsCopy;
    }
}


