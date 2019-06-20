package Logic;

import GUI.PlayerBox;
import GUI.PlayerTools;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class RunMusic implements Runnable {
    private String path;


    public RunMusic(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        try {

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
            Player player = new Player(bis);
            try {
                player.play();
                if (PlayerTools.isIsRepeat())
                {
                    while (PlayerTools.isIsRepeat())
                    {
                        if (player.isComplete())
                        {
                            player.close();
                            bis = new BufferedInputStream(new FileInputStream(path));
                            player = new Player(bis);
                            System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                            player.play();
                        }
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopThread(Thread thread) throws InterruptedException {
        thread.suspend();
    }

    public void resume(Thread thread) {
        thread.resume();
    }

    public String getPath() {
        return path;
    }
}
