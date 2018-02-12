public class Klocek {
    boolean[][] tab = new boolean[4][4];
    private boolean[][] tabE = new boolean[4][4];
    byte akKlocek;

    Klocek() {
       this.setKlocek((byte) 0);
    }
    public void setKlocek(byte k){
        this.akKlocek = k;
        for(byte x=0; x<4; x++){
            for(byte y=0; y<4; y++)
                this.tab[y][x] = Klocki.KLOCKI[this.akKlocek][x][y];
        }
    }
    public void obrot() {
        byte x;
        byte y;
        for(x = 0; x < 4; x++) for(y = 0; y < 4; y++) this.tabE[x][y] = this.tab[x][y];
        for(x = 0; x < 4; x++) for(y = 0; y < 4; y++) this.tab[3-y][x] = this.tabE[x][y];
    }

    public void cofnijObrot() {
        for(byte x = 0; x < 4; ++x) {
            for(byte y = 0; y < 4; ++y) {
                this.tab[x][y] = this.tabE[x][y];
            }
        }

    }
}
