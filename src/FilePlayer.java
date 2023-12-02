import java.io.File;
import java.util.concurrent.CountDownLatch;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FilePlayer {
    public void play(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }

    }

    public static final CountDownLatch startLatch = new CountDownLatch(1);

    public static void playNotes(String... notes) {
        try {
            startLatch.await(); 

            for (String note : notes) {
                playSingleNote(note);
                System.out.println(note);
                Thread.sleep(500); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void playSingleNote(String note) {
        try {
            String filePath = "src/Sounds/"+ note + ".wav"; 
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.drain();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void playTwinkleTwinkle() {
        playNotes("do", "do", "sol", "sol", "la", "la", "sol", "fa", "fa", "mi", "mi", "re", "re", "do",
                "sol", "sol", "fa", "fa", "mi", "mi", "re", "sol", "sol", "fa", "fa", "mi", "mi", "re",
                "do", "do", "sol", "sol", "la", "la", "sol", "fa", "fa", "mi", "mi", "re", "re", "do");
    }

    
}
