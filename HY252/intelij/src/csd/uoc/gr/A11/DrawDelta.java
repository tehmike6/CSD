package csd.uoc.gr.A11;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class DrawDelta {
    public static int flag = 0;

    public static void drawHgraphics(int L) {
        Frame f = new Frame("Ζωγραφίζοντας το Δ") {
            public void paint(Graphics g) {
                QuadCurve2D q = new QuadCurve2D.Float();
                Graphics2D g2 = (Graphics2D) g;
                g2.draw(new Line2D.Double(50, 300, 200, 50)); // a line
                g2.draw(new Line2D.Double(50, 300, 350, 300)); // a line
                g2.draw(new Line2D.Double(200, 50, 350, 300)); // a line
                q.setCurve(350, 300, 380, 400, 400, 300);
                g2.draw(q);
            }
        };
        f.setSize(400, 400);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid number of arguments! Program exits...");
            return;
        }

        int L = Integer.parseInt(args[1]);

        if ((L < 3) || (L > 20)) {
            System.out.println("Length out of range! Program exits...");
            return;
        }

        if (args[0].compareTo("c") == 0) {
            //Print delta to console using stars (*).
            System.out.println(giveDelta(L));
        } else if (args[0].compareTo("w") == 0) {
            UIManager.put("OptionPane.messageFont", new Font("Lucida Console", Font.BOLD, 14));

            JOptionPane.showMessageDialog(null,
                    giveDelta(L),
                    "Output Window",
                    JOptionPane.INFORMATION_MESSAGE);

        } else if (args[0].compareTo("f") == 0) {
            try {
                FileWriter newFile = new FileWriter("Delta.html");
                newFile.write("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\"/>\n" +
                        "</head>\n" +
                        "<body><font size=\"" + L + "\">Δ with font size =" + L + "</font></body>\n" +
                        "</html>");
                newFile.close();
            } catch (Exception e) {
                System.out.println(e);
                System.exit(-1);
            }
        } else if (args[0].compareTo("g") == 0) {
            drawHgraphics(L);
        } else {
            System.out.println("Unknown input! Program exits...");
            System.exit(-1);
        }
        if(flag == 1){
            return;
        }
        args[1] = String.valueOf(L-1);
        main(args);
        //Get a new Length input
        flag = 1;
        if(!args[0].contentEquals("w")) {
            Scanner in = new Scanner(System.in);
            L = in.nextInt();
            args[1] = String.valueOf(L);
        }else{
            L = Integer.parseInt(JOptionPane.showInputDialog("Give me a number ",4));
            args[1] = String.valueOf(L);
        }
        main(args);
    }

    private static String giveDelta(int l) {
        String delta="";
        for (int i=1; i<=l; i++)
        {
            for (int j = i; j < l; j++) {
                delta+=' ';
            }
            for (int k = 1; k <= (2*i -1) ;k++) {
                if( k==1 || i == l || k==(2*i-1)) {
                    delta+='*';
                }
                else {
                    delta+=' ';
                }
            }
            delta+='\n';
        }

        return delta;
    }
}