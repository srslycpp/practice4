import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AConvas  extends Canvas {
    BufferedImage image;
    Graphics2D grafika;

    AConvas(short sze, short wys){
        super();
        setSize(sze, wys);
        image = new BufferedImage(sze, wys, BufferedImage.TYPE_INT_RGB);
        grafika = (Graphics2D) image.getGraphics();
    }
    public abstract void drawImage();

    private void naEkran(){
    Graphics g = getGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    }
    public void run(){
        drawImage();
        naEkran();
    }
}
