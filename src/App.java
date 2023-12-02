public class App {
    public static void main(String[] args) {
        FilePlayer fp = new FilePlayer();

        Thread thread1 = new Thread(() -> fp.playNotes("do", "mi", "sol", "si", "do-octave"));
        Thread thread2 = new Thread(() ->fp.playNotes("re", "fa", "la", "do-octave"));

        // // Bonus part
        Thread twinkleThread = new Thread(() -> fp.playTwinkleTwinkle());
        
        thread1.start();
        thread2.start();
        twinkleThread.start();

        fp.startLatch.countDown();

        try {
            thread1.join();
            thread2.join();
            twinkleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
