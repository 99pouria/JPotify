package Logic;

import GUI.CentralPanel;
import GUI.PlayerBox;
import GUI.PlayerTools;
import GUI.SongInfo;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.PlayerApplet;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class RunMusic extends Thread {
    private BufferedInputStream bis;
    private String path;
    private AdvancedPlayer player;
    private Save save = new Save();
    private Random random = new Random();
    private SongInfo songInfo;
    private boolean isPaused;

    public RunMusic(String path, SongInfo songInfo) {
        this.path = path;
        this.songInfo = songInfo;
    }

    @Override
    public synchronized void run() {
        try {
            int randomNumber;

            bis = new BufferedInputStream(new FileInputStream(path));
            player = new AdvancedPlayer(bis);
            PlayerTools.setMaximum(path);

            while (player.play(1)) {
                PlayerTools.setPosition(player.getPosition());
                if (isPaused) {
//                    PlayerTools.getThread().start();
                    synchronized (player) {
                        player.wait();
                    }
                }
            }

            while (PlayerTools.isIsRepeat()) {
                player.close();
                bis = new BufferedInputStream(new FileInputStream(path));
                player = new AdvancedPlayer(bis);

                while (player.play(1)) {
                    PlayerTools.setPosition(player.getPosition());

                    if (isPaused) {
                        synchronized (player) {
                            player.wait();
                        }
                    }
                }
            }

            while (true) {
                randomNumber = random.nextInt(save.getSortedMusics().size());
                player.close();
                bis = new BufferedInputStream(new FileInputStream(save.getSortedMusicsCopy().get(randomNumber)));
                player = new AdvancedPlayer(bis);
                CentralPanel.setPath(save.getSortedMusicsCopy().get(randomNumber));
                try {
                    if (songInfo != null) {
                        songInfo.changeSongInfo(CentralPanel.getPath());
                    }
                } catch (IOException | InvalidDataException | UnsupportedTagException e1) {
                    e1.printStackTrace();
                }
                save.deleteAndReAddMusic(CentralPanel.getPath());
                save.saveToFile();
                PlayerTools.setMaximum(path);
                while (player.play(1)) {
                    PlayerTools.setPosition(player.getPosition());
                    if (isPaused) {
                        synchronized (player) {
                            player.wait();
                        }
                    }
                }

                while (PlayerTools.isIsRepeat()) {
                    player.close();
                    bis = new BufferedInputStream(new FileInputStream(path));
                    player = new AdvancedPlayer(bis);
                    while (player.play(1)) {
                        PlayerTools.setPosition(player.getPosition());

                        if (isPaused) {
                            synchronized (player) {
                                player.wait();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mp3Pause() {
        this.isPaused = true;
    }

    public void mp3Resume() {
        this.isPaused = false;
        synchronized (player) {
            player.notifyAll();
        }
    }


    public void seekTo(int frame) throws JavaLayerException {
        synchronized (player) {
            player.close();
            player = new AdvancedPlayer(bis);
            player.play(frame, frame + 1);
        }
    }

    public String getPath() {
        return path;
    }

}
