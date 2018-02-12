import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Plansza extends AConvas implements MouseListener, KeyListener {


    final static short SZE = Klocki.SIZE * 10;
    final static short WYS = Klocki.SIZE * 20;  ///

    byte[][] tab = new byte[13][22];    //screen size
    Random los = new Random();

    Klocek klocek = new Klocek();
    byte klocekX, klocekY;

    boolean kUp, kDown, kLeft, kRight;

    short speed, speedMax;
    boolean speedKey;


    Plansza() {
        super(SZE, WYS);
        addKeyListener(this);
        addMouseListener(this);
        for(byte x=0; x<12; x++) {tab[x][0]=1;tab[x][20]=1;} // out of y screen
        for(byte y=0; y<22; y++) {tab[0][y]=1;tab[12][y]=1;}  // out of x screen
        klocekX =4;
        klocekY =0;
        speedMax =20;

    }

    public void drawImage() {
        key();
        drukPLansza();
        cmpPlansza();
       // drukKostka((byte) 3, (byte) 3, (byte) 3);
        drukKlocek(klocekX, klocekY);
        if(speed<speedMax) speed++;else
        {
            speed=0;
            if(isKlocekPlansza(klocekX, (byte)(klocekY+1))) klocekY++; else
            {
               klocekKoniec();
               nowyKlocek();
            }
        }
    }
    public void nowyKlocek() {
        klocekX = 4;
        klocekY = 1;
        speedMax = 10;
        klocek.setKlocek(Tetris.next.klocek);
        Tetris.next.losujKlocek();
    }
    public void klocekKoniec(){
        for(byte xx=0; xx<4;xx++)
            for(byte yy=0; yy<4; yy++)
                if(klocek.tab[xx][yy]) tab[xx+klocekX][yy+klocekY]= (byte)(klocek.akKlocek+1);
        }


    private void drukPLansza() {
        for (byte x = 0; x < 12; x++) {
            for (byte y = 0; y < 22; y++) {
                grafika.setColor(Klocki.KOLOR[tab[x][y]]);
                grafika.fillRect(x * Klocki.SIZE, y * Klocki.SIZE, Klocki.SIZE, Klocki.SIZE);
                grafika.setColor(Color.BLACK);
                if (tab[x][y] > 0) grafika.drawRect(x * Klocki.SIZE, y * Klocki.SIZE, Klocki.SIZE - 1, Klocki.SIZE - 1);
            }
        }
    }

    private void drukKostka(byte x, byte y, byte k) {
        grafika.setColor(Klocki.KOLOR[k]);
        grafika.fillRect(x * Klocki.SIZE, y * Klocki.SIZE, Klocki.SIZE, Klocki.SIZE);
        grafika.setColor(Color.BLACK);
        grafika.drawRect(x * Klocki.SIZE, y * Klocki.SIZE, Klocki.SIZE - 1, Klocki.SIZE - 1);// klocek z kostek
    }

    private boolean isLInia(byte y) {

        for (byte x = 0; x < 10; x++) {
            if (tab[x][y] == 0) return false;
        }
        return true;
    }

    private void setLinia(byte y) {
        for (byte x = 0; x < 10; x++) {
            tab[x][y] = 8;
        }
    }

    private void cmpPlansza() {
        for (byte y = 0; y < 20; y++) {
            if (tab[0][y] == 8) downPlansza(y); //kasowanie się lini
            if (isLInia(y)) setLinia(y);
        }

    }

    //kasowanie się lini
    private void downPlansza(byte y) {
        for (byte dy = y; dy > 0; dy--) {
            for (byte x = 0; x < 10; x++) tab[x][dy] = tab[x][dy - 1];
        }
        for (byte x = 0; x < 10; x++) tab[x][0] = 0;
    }

    public void drukKlocek(byte x, byte y) {
        for (byte tx = 0; tx < 4; tx++) {
            for (byte ty = 0; ty < 4; ty++) {
                if (klocek.tab[tx][ty]) drukKostka((byte) (x + tx), (byte) (y + ty), (byte) (klocek.akKlocek + 1));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        tab[e.getX() / Klocki.SIZE][e.getY() / Klocki.SIZE] = 3;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == e.VK_UP) kUp = true;
        if (k == e.VK_DOWN) kDown = true;
        if (k == e.VK_LEFT) kLeft = true;
        if (k == e.VK_RIGHT) kRight = true;
    }

    @SuppressWarnings("static-access")
    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == e.VK_UP) kUp = false;
        if (k == e.VK_DOWN) kDown = false;
        if (k == e.VK_LEFT) kLeft = false;
        if (k == e.VK_RIGHT) kRight = false;
    }

    private boolean isKlocekPlansza(byte x, byte y) {
        for (byte xx = 0; xx < 4; xx++)
            for (byte yy = 0; yy < 4; yy++)
                if (klocek.tab[xx][yy] && tab[xx + x][yy + y] > 0) return false;
        return true;
    }

    private boolean isLiniaKlocekX(byte x) {
        for (byte y = 0; y < 4; y++) {
            if (klocek.tab[x][y]) return false;
        }
        return true;
    }


    private boolean moveLeft() {
        if (klocekX == 0 && !isLiniaKlocekX((byte)0)) return false; else
        if (klocekX == -1 && !isLiniaKlocekX((byte)1)) return false; else
        if (klocekX == -2 && !isLiniaKlocekX((byte)2)) return false;
        //klocek o klocek
        if (!isKlocekPlansza((byte) (klocekX - 1), klocekY)) return false;
        return true;
    }

    private boolean moveRight() {
        if (klocekX == 6 && !isLiniaKlocekX((byte)3)) return false; else
        if (klocekX == 7 && !isLiniaKlocekX((byte)2)) return false; else
        if (klocekX == 8 && !isLiniaKlocekX((byte)1)) return false;
        // klocek o klocek
        if (!isKlocekPlansza((byte) (klocekX + 1), klocekY)) return false;
        return true;
    }

    private void key() {

        if (kUp ) {
            klocek.obrot();
           // sObrot.play();
            //if (!isKlocekPlansza(klocekX, klocekY)) klocek.cofnijObrot();
        }
        if (kLeft && moveLeft()) klocekX--;
        if (kRight && moveRight()) klocekX++;
        if(kDown) speedMax=0;
        // if (kDown && speedMax>0) {speedMax=0;Tetris.punkty+=5;Tetris.lPunkty.setText(String.valueOf(Tetris.punkty));}
    }

}
