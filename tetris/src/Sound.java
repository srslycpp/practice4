import java.applet.AudioClip;

public class Sound {
    public boolean isPlay = false;
    private AudioClip clip;

//    Sound(String nazwaPliku) {
//        this.clip = Applet.newAudioClip(Sound.class.getResource(nazwaPliku));
//    }

    public void play() {
        this.isPlay = true;
        (new Thread() {
            public void run() {
                Sound.this.clip.play();
            }
        }).start();
    }

    public void stop() {
        this.isPlay = false;
        this.clip.stop();
    }

    public void petla() {
        this.isPlay = true;
        this.clip.loop();
    }
}
