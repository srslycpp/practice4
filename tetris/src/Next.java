//tutaj zle z next klockiem

import java.awt.*;
import java.util.Random;

public class Next extends AConvas {

    byte klocek;
    private Random los = new Random();

    Next() {
        super((byte) 100, (byte) 100);
        losujKlocek();
    }

    public void losujKlocek() {
        klocek = (byte) (los.nextInt(6) + 1);
    }

    private void drukKostka(byte x, byte y, byte k) {
        grafika.setColor(Klocki.KOLOR[k]);
        grafika.fillRect(x * Klocki.SIZE, y * Klocki.SIZE, Klocki.SIZE, Klocki.SIZE);
        grafika.setColor(Color.BLACK);
        grafika.drawRect(x * Klocki.SIZE, y * Klocki.SIZE, Klocki.SIZE - 1, Klocki.SIZE - 1);// klocek z kostek
    }

    private void drukKlocek() {
        grafika.setColor(Klocki.KOLOR[0]);
        grafika.fillRect(0, 0, 4 * Klocki.SIZE, 4 * Klocki.SIZE);
        for (byte tx = 0; tx < 4; tx++) {
            for (byte ty = 0; ty < 4; ty++) {
               // if (Tetris.plansza.klocek.tab[tx][ty]) drukKostka(tx, ty, (byte) (klocek + 1));
                if (Klocki.KLOCKI[klocek][tx][ty]) drukKostka(tx, ty, (byte) (klocek + 1));
            }
        }
    }

    @Override
    public void drawImage() {
        drukKlocek();
    }
}
