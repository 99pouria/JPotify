package Logic;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class RunMusic implements Runnable{
    private String path;


    public RunMusic(String path) {
        this.path=path;
    }

    @Override
    public void run() {
        try {

            BufferedInputStream bis=new BufferedInputStream(new FileInputStream(path));
            Player player=new Player(bis);
            try {
                player.play();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void stopThread(Thread thread) throws InterruptedException {
        thread.suspend();
    }

    public void resume(Thread thread)
    {
        System.out.println("fek konam shoro shod !!!!!!!!!!!!!!!!!!");
        thread.resume();
    }

    public String getPath() {
        return path;
    }
}
