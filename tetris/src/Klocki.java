import java.awt.*;

public class Klocki {
    final static Color[] KOLOR = {Color.BLACK, Color.CYAN, Color.MAGENTA, Color.GREEN, Color.BLUE, Color.LIGHT_GRAY, Color.PINK, Color.YELLOW, Color.ORANGE};
    final static short SIZE = 25;
    final static boolean [][][] KLOCKI =
            {
                    {
                            {false, false, false, false},	//....
                            {true , true , true , false},	//###.
                            {false, false, true , false},	//..#.
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {true , true , true , false},	//###.
                            {false, true , false, false},	//.#..
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {false, false, true , false},	//..#.
                            {true , true , true , false},	//###.
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {true , true , true , true },	//####
                            {false, false, false, false},	//....
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {false, true , true , false},	//.##.
                            {false, true , true , false},	//.##.
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {false, true , true , false},	//.##.
                            {true , true , false, false},	//##..
                            {false, false, false, false}	//....
                    },
                    {
                            {false, false, false, false},	//....
                            {true , true , false, false},	//##..
                            {false, true , true , false},	//.##.
                            {false, false, false, false}	//....
                    }
            };


}
