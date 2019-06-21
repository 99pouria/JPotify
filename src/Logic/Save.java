package Logic;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Save {
    private static HashMap<String, Boolean> musics = new HashMap<>();
    private static ArrayList<String> sortedMusics = new ArrayList<>();
    private static ArrayList<String> sortedMusicsCopy = new ArrayList<>();
    private static ArrayList<String> playListsName=new ArrayList<>();
    private static boolean isCopied = true;
//    private static String[][] allPlayLists=new String[20][30];
//    private static ArrayList<ArrayList<String>> allPlayLists=new ArrayList<>();
//    private static HashMap<String,ArrayList<String>> allPlayLists=new HashMap<>();


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
            c.printStackTrace();
            return null;
        }
//        setSortedMusicsCopy(getSortedMusics());
        return getSortedMusics();
    }

    public void setSortedMusicsCopy(ArrayList<String> sortedMusicsCopy) {
        Save.sortedMusicsCopy = sortedMusicsCopy;
    }

    public ArrayList<String> getSortedMusicsCopy() {
        return sortedMusicsCopy;
    }

    public void addPlayList(String name) throws IOException {
        playListsName.add(name);
        FileOutputStream fosOfPlayLists =
                new FileOutputStream("C:\\Users\\Public\\Documents\\playlistsName.ser");
        ObjectOutputStream oosOfPlayLists = new ObjectOutputStream(fosOfPlayLists);
        oosOfPlayLists.writeObject(getPlayListsName());
        oosOfPlayLists.close();
        fosOfPlayLists.close();
    }

    public void readPlayListsName() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Documents\\PlaylistsName.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        setPlayListsName((ArrayList) ois.readObject());
        ois.close();
        fis.close();
    }

    public static void setPlayListsName(ArrayList<String> playListsName) {
        Save.playListsName = playListsName;
    }

    public  ArrayList<String> getPlayListsName() {
        return playListsName;
    }

}


