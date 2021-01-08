package csd.uoc.gr.A32;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class A21 {
    public static void main(String[] aa) {
        JFrame frame = new JFrame();
        JButton button1 = new JButton("Click me!");
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Hello Java 8!");
                Runnable r1 = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Τρέχω παράλληλα");
                        main(null);
                    }
                };
                new Thread(r1).start();
            }
        });
        frame.add(button1);
        frame.setSize(200, 200);
        frame.setVisible(true);
    } // main
}

class new_A21{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button1 = new JButton("Click me!");
        button1.addActionListener( e -> {
            System.out.println("Hello Java 9!");
            Runnable r1 = () -> {
                System.out.println("Τρέχω παράλληλα");
                main(null);
            };
            new Thread(r1).start();
        });
        frame.add(button1);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}