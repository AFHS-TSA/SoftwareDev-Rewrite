package main.java.app;

public class SpacedRep {
    static double time1 = 0;
    static double time2 = .041;
    static double memStrength = 1;
    static int n = 0;
    static int rN = 0;
    static int d = 0;

    public static void main (String[] args) {
    }

    public SpacedRep() {

    }

    public static int SM2() {
        d = 0;
        n = 0;
        for (; n < 1;) {
            double exp1 = (-time1 / (memStrength));
            double exp2 = (-time2 / (memStrength));
            double y1 = Math.exp(exp1);
            double y2 = Math.exp(exp2);
            if (y1 - y2 > .3) {
                System.out.println("Study");
                memStrength++;
                time2 = .041;
                n++;
                //d+=1;
            } else {
                System.out.println("Relax");
                d++;
                time2 += .041;
            }
        }


        rN = n;
        memStrength++;
        return d;
    }
}
