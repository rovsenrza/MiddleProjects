import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {
    char k;
    int i = 0;
    headphones sound = new headphones();
    JSlider slider;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        URL soundURL[] = {
                getClass().getResource("Heat_Waves_java.wav"),
                getClass().getResource("Infinity_java.wav"),
                getClass().getResource("enemy_java.wav"),
                getClass().getResource("stan_java.wav"),
                getClass().getResource("gangstas_paradise_java.wav"),
        };
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(1, 3));

        JButton playB = new JButton("Play");
        playB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playMusic(soundURL[0]);
            }
        });
        window.add(playB);

        JButton nextB = new JButton("Next");
        nextB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.stop();
                i++;
                if (i == 5) {
                    i = 0;
                }
                playMusic(soundURL[i]);
            }
        });
        window.add(nextB);

        JButton prevB = new JButton("Previous");
        prevB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.stop();
                i--;
                if (i == -1) {
                    i = 4;
                }
                playMusic(soundURL[i]);
            }
        });
        window.add(prevB);

        JButton stopB = new JButton("Stop");
        stopB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.stop();
            }
        });
        window.add(stopB);

        JButton muteB = new JButton("Mute");
        muteB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.volumeMute(slider);
            }
        });
        window.add(muteB);

        slider = new JSlider(-40, 6);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sound.currentVolume = slider.getValue();
                if (sound.currentVolume == -40) {
                    sound.currentVolume = -80;
                }
                System.out.println("volume:" + sound.currentVolume);
                sound.fc.setValue(sound.currentVolume);
            }
        });
        window.add(slider);
        window.pack();
        window.setVisible(true);
    }

    public void playMusic(URL url) {
        sound.setfile(url);
        sound.play();
        sound.loop();
    }
}