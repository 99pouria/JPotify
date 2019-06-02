package GUI;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JPanel {
    private JMenuBar menuBar;
    private JMenu menu,file,edit,playBack,help;
    private JMenuItem newPlaylist,exit;
    private JMenuItem undo,redo;
    private JMenuItem play,next,previous,seekForward,seekBackward;

    public MenuBar() {
        super();
        Font font = new Font("MyFont", 1, 24);
        Font JMenuFonts = new Font("MyFont", 7, 18);


        menuBar=new JMenuBar();

        file=new JMenu("   File   ");
        edit=new JMenu("   Edit   ");
        playBack=new JMenu("   PlayBack   ");
        help=new JMenu("   Help   ");

        newPlaylist=new JMenuItem("    New PlayList    ");
        exit=new JMenuItem("    Exit    ");
        undo=new JMenuItem("    Undo    ");
        redo=new JMenuItem("    Redo    ");
        play=new JMenuItem("    Play    ");
        next=new JMenuItem("    Next    ");
        previous=new JMenuItem("    Previous    ");
        seekBackward=new JMenuItem("    SeekForward    ");
        seekForward=new JMenuItem("    SeekBackward    ");

        menu = new JMenu("   ...");
        menu.setFont(font);
        menu.setForeground(Color.white);
        menu.setBackground(Color.BLACK);
        menu.setOpaque(true);
        menu.setBorderPainted(false);

        file.setFont(JMenuFonts);
        edit.setFont(JMenuFonts);
        playBack.setFont(JMenuFonts);
        newPlaylist.setFont(JMenuFonts);
        help.setFont(JMenuFonts);
        exit.setFont(JMenuFonts);
        undo.setFont(JMenuFonts);
        redo.setFont(JMenuFonts);
        play.setFont(JMenuFonts);
        next.setFont(JMenuFonts);
        previous.setFont(JMenuFonts);
        seekForward.setFont(JMenuFonts);
        seekBackward.setFont(JMenuFonts);


        menu.add(file);menu.add(edit);menu.add(playBack);menu.add(help);
        file.add(newPlaylist);file.add(exit);
        edit.add(undo);edit.add(redo);
        playBack.add(play);playBack.add(next);playBack.add(previous);playBack.add(seekBackward);playBack.add(seekForward);

        menuBar.add(menu);
        add(menuBar);
    }
}
