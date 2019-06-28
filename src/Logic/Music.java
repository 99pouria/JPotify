package Logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

public class Music implements Serializable {
    private String path;
    private String songName;
    private String artistName;
    private Image image;

    public Music(String path) throws IOException {
        this.path = path;
        File file = new File(path);
        byte[] name = new byte[30];
        byte[] artist = new byte[30];
        byte[] fileContent = Files.readAllBytes(file.toPath());
        for (int i = 0; i < 30; i++) {
            name[i] = fileContent[i + fileContent.length - 125];
            artist[i] = fileContent[i + fileContent.length - 125 + 30];
        }
        songName = new String(name);
        artistName = new String(artist);

//        this.image = getArtwork(path, 50, 50);

    }

    public String getPath() {
        return path;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public Image getImage() {
        return image;
    }

    public String toString() {
        return getSongName() + "\n" + getSongName();
    }

    public Image getArtwork(String iconAddress, int width, int height) throws IOException {
        Image img = ImageIO.read(getClass().getResource(iconAddress));
        img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return img;
    }
}
