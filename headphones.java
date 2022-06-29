import javax.sound.sampled.AudioInputStream;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JSlider;

public class headphones {
    Clip clip;
    float previousVolume = 0;
    float currentVolume = 0;
    FloatControl fc;
    boolean mute = false;

    public void setfile(URL url) {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(sound);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {

        }
    }

    public void play() {
        fc.setValue(currentVolume);
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void volumeUp() {

    }

    public void volumeDown() {
        currentVolume -= 1.0f;
        System.out.println("current Volume:" + currentVolume);
        if (currentVolume < -80.0f) {
            currentVolume = -80.0f;
        }
        fc.setValue(currentVolume);
    }

    public void volumeMute(JSlider slider) {
        if (mute == false) {
            previousVolume = currentVolume;
            System.out.println("current Volume:" + currentVolume);
            currentVolume = -80.0f;
            fc.setValue(currentVolume);
            mute = true;
            slider.setValue(slider.getMinimum());
        } else if (mute == true) {
            currentVolume = previousVolume;
            slider.setValue((int) currentVolume);
            System.out.println("current volume:" + currentVolume);
            fc.setValue(currentVolume);
            mute = false;
        }

    }
}