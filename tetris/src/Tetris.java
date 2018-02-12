import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tetris extends JPanel implements Runnable {

    static Tetris tetris = new Tetris();
    static JFrame okno = new JFrame("Tetris");
    static Thread watek = new Thread(tetris);
    static Next next = new Next();

    static Plansza plansza = new Plansza();

    boolean start = false;
    short op = 50;
    int linie = 0, punkty = 0;
    static JLabel IPunkty, ILiniie;

    Random los = new Random();

    Tetris() {
        super();
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        start = true;
    }

    public static void main(String[] args) {
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.add(tetris);
        okno.setSize(600, 600);
        okno.setLocationRelativeTo(null);
        okno.setResizable(false);
        IPunkty = new JLabel("0", JLabel.CENTER);
        IPunkty.setForeground(Color.WHITE);
        IPunkty.setBounds(300, 160, 100, 30);
        tetris.add(IPunkty);
        ILiniie = new JLabel("0", JLabel.CENTER);
        ILiniie.setForeground(Color.WHITE);
        ILiniie.setBounds(300, 360, 100, 30);
        tetris.add(ILiniie);

        plansza.setLocation(10, 10);
        tetris.add(plansza);
        next.setLocation(300, 10);
        tetris.add(next);
        okno.setVisible(true);
        watek.start();
    }

    public void run() {
        long wait, startCzas, cyklCzas;
        while (start) {
            startCzas = System.nanoTime();
            next.run();
            plansza.run();

            cyklCzas = System.nanoTime() - startCzas;
            wait = op - cyklCzas / 1000000;
            if (wait <= 0) wait = 3;
            try {
                watek.sleep(op);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(op + " > " + wait);
            }
        }
    }
}
