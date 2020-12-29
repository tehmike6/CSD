package csd.uoc.gr.A12;

import org.jfugue.Player;

import javax.swing.*;

class Ping{
    public static void ping(int k){
        MyUtilities.sound(k);
        JOptionPane.showMessageDialog(null,
                k,
                "Ping",
                JOptionPane.INFORMATION_MESSAGE);
        if(k > 1)
            Pong.pong(k-1);
        return;
    }

    public static void main(String[] args) {
        Ping.ping(10);
    }
}

class Pong{
    public static void pong(int k){
        MyUtilities.sound(k);
        JOptionPane.showMessageDialog(null,
                k,
                "Pong",
                JOptionPane.INFORMATION_MESSAGE);
        if(k > 1)
            Ping.ping(k-1);
        return;
    }
    public static void main(String[] args) {

        Pong.pong(10);
    }
}

class MyUtilities{
    static void sound(int k){
        int x = k*12;
        Player p = new Player();
        p.play("["+x+"] ["+(x+2)+"] ["+(x+5)+"] ");

    }
}