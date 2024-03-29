package GUI;

import Logic.Save;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import sun.plugin2.util.PluginTrace;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;



public class SwitchPanel extends JPanel implements ActionListener {

    private JPanel buttonsPanel;
    private JButton homeBtn;
    private JButton browseBtn;
    private JFileChooser fileChooser;
    private Save save;
//    private InteractivePart interactivePart;
    private MusicController musicController;

    public SwitchPanel(MusicController musicController) throws IOException {
        super();
        this.musicController=musicController;

        setLayout(new BorderLayout());

        MenuBar menu = new MenuBar();
        menu.setAlignmentX(SwingConstants.RIGHT);
        menu.setPreferredSize(new Dimension(250,70));
        menu.setOpaque(true);
        menu.setBackground(Color.black);

        buttonsPanel=new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1));

        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        homeBtn = new JButton("Home");
        browseBtn = new JButton("Browse");
        save=new Save();

        homeBtn.setOpaque(true);
        homeBtn.setBackground(Color.BLACK);
        homeBtn.setForeground(Color.white);

        browseBtn.setOpaque(true);
        browseBtn.setBackground(Color.BLACK);
        browseBtn.setForeground(Color.white);

        setOpaque(true);
        setBackground(Color.black);

        homeBtn.setHorizontalAlignment(SwingConstants.LEFT);
        browseBtn.setHorizontalAlignment(SwingConstants.LEFT);

        homeBtn.setContentAreaFilled(false);
        homeBtn.setFocusPainted(false);
        homeBtn.setBorderPainted(false);
        browseBtn.setContentAreaFilled(false);
        browseBtn.setFocusPainted(false);
        browseBtn.setBorderPainted(false);

        Font font = new Font("MyFont", 1, 17);

        homeBtn.setFont(font);
        browseBtn.setFont(font);

        buttonEventHandler(homeBtn);
        buttonEventHandler(browseBtn);

//        homeBtn.addActionListener(this);

        buttonsPanel.add(homeBtn);
        buttonsPanel.add(browseBtn);
        buttonsPanel.setPreferredSize(new Dimension(250,100));
        buttonsPanel.setOpaque(true);
        buttonsPanel.setBackground(Color.black);


        add(menu,BorderLayout.NORTH);
        add(buttonsPanel,BorderLayout.SOUTH);

        browseBtn.addActionListener(this);
    }

    public MusicController getMusicController() {
        return musicController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel l = new JLabel();
        if (e.getSource() == browseBtn) {


            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setDialogTitle("Select a .mp3 file");
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .mp3 files", "mp3");
            fileChooser.addChoosableFileFilter(restrict);
            int r = fileChooser.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                l.setText(fileChooser.getSelectedFile().getAbsolutePath());
            } else
                l.setText("the user cancelled the operation");
//            add(l);

            try {
                if (!save.getMusics().containsKey(fileChooser.getSelectedFile().getAbsolutePath())) {
                    getMusicController().getInteractivePart().makeMusicPad(fileChooser.getSelectedFile().getAbsolutePath());
                    save.addMusic(fileChooser.getSelectedFile().getAbsolutePath(), false);
                    save.saveToFile();
                }
                else;
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            updateUI();
        }
    }


    public void buttonEventHandler(JButton button) {
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setForeground(Color.getHSBColor(104, 69, 55));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setForeground(Color.GREEN);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }
        });
    }

}
