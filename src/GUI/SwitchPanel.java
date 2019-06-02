package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchPanel extends JPanel implements ActionListener {

    private JButton homeBtn;
    private JButton browseBtn;
    private JFileChooser fileChooser;

    public SwitchPanel() {
        super();
        setLayout(new GridLayout(2, 1));

        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        homeBtn = new JButton("Home");
        browseBtn = new JButton("Browse");

        homeBtn.setOpaque(true);
        homeBtn.setBackground(Color.BLACK);
        homeBtn.setForeground(Color.white);

        browseBtn.setOpaque(true);
        browseBtn.setBackground(Color.BLACK);
        browseBtn.setForeground(Color.white);

        setOpaque(true);
        setBackground(Color.black);

        homeBtn.setContentAreaFilled(false);
        homeBtn.setFocusPainted(false);
        homeBtn.setBorderPainted(false);
        browseBtn.setContentAreaFilled(false);
        browseBtn.setFocusPainted(false);
        browseBtn.setBorderPainted(false);

        Font font = new Font("MyFont", 1, 17);

        homeBtn.setFont(font);
        browseBtn.setFont(font);

        add(homeBtn);
        add(browseBtn);

        browseBtn.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == browseBtn) {
            JLabel l = new JLabel();

            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setDialogTitle("Select a .mp3 file");
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .mp3 files", "mp3");
            fileChooser.addChoosableFileFilter(restrict);
            int r = fileChooser.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                l.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
            else
                l.setText("the user cancelled the operation");
            add(l);
        }
    }
}
