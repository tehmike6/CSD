package csd.uoc.gr.A13;

import org.jfugue.Player;

public class MusicMaker{
    static int[] concat(int[] a, int[] b){
        int[] c = new int[a.length + b.length];
        for(int i = 0; i < a.length + b.length; i++){
            if(i < a.length){
                c[i] = a[i];
                continue;
            }
            c[i] = b[i - a.length];
        }
        return c;
    }

    static int[] expandHappy(int[] input){
        int[] c = new int[input.length*3];
        for(int i = 0; i < input.length*3; i = i+3){
            c[i] = input[i/3];
            c[i+1] = input[i/3] + 4;
            c[i+2] = input[i/3] + 7;
        }
        return c;
    }

    static int[] expandSad(int[] input){
        int[] c = new int[input.length*3];
        for(int i = 0; i < input.length*3; i = i+3){
            c[i] = input[i/3];
            c[i+1] = input[i/3] + 3;
            c[i+2] = input[i/3] + 7;
        }
        return c;
    }

    static void playArray(int a[]){
        String s = "";
        for(int i = 0; i < a.length; i++){
            s += "[" + a[i] + "] ";
        }
        Player p = new Player();
        System.out.println(s);
        p.play(s);
    }
    public static void main(String[] args) {
        int seed1[] = {80, 80};
        int seed2[] = {80, 92, 104};
        int seed3[] = {70,70,74,77,77};

        playArray(seed1);
        playArray(expandHappy(seed1));
        playArray(expandSad(seed1));
        playArray(expandHappy(seed2));
        playArray(concat(seed1,
                concat(expandHappy(seed3),expandSad(seed2))));

    }

}