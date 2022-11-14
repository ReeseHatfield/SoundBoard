package Reese;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Soundboard extends JPanel {

    public Soundboard(int size) {

        GridLayout layout = new GridLayout();
        layout.setColumns(size);
        layout.setRows(size);
        this.setLayout(layout);

        File dir = new File("./audio");

        makeButtons(Objects.requireNonNull(dir.listFiles()));
    }

    public void makeButtons(File[] files) {
        ArrayList<SoundButton> btns = new ArrayList<>();
        for (File file : files) {
            File filename = new File(file.getAbsolutePath());
            btns.add(new SoundButton(getName(file.getAbsolutePath()), filename));
        }
        for(SoundButton b : btns){
            if(!b.getAudioFile().endsWith("wav")) continue;


            b.setText(b.getName());
            b.setBackground(Color.BLUE);
            b.setOpaque(true);
            b.addActionListener(e ->{
                try{
                    b.play();
                }
                catch (Exception err){
                    err.printStackTrace();
                }
            });
            this.add(b);
        }

    }


    public static String getName(String filename) {
        return filename.substring(filename.lastIndexOf('/')+1, filename.lastIndexOf('.'));
    }

}
