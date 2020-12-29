package csd.uoc.gr.A15;

import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class Encrypt {
    static String inputFileName;
    static int offset;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        inputFileName = args[0];
        offset = Integer.parseInt(args[1]);
        transform();

        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F;
        System.out.println(sec + " seconds");
    }

    static void transform() {
        try {
            FileWriter newFile = new FileWriter("Encrypted" + inputFileName);
            File file = new File(inputFileName);
            byte[] fileData = new byte[(int) file.length()];
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                fileInputStream.read(fileData);
            } catch (FileNotFoundException e) { //Folder not found
                e.printStackTrace();
            } catch (IOException e) { //Wrong input
                e.printStackTrace();
            }

            try (FileOutputStream fos = new FileOutputStream("Encrypted" + inputFileName)) {
                fos.write(fileData);
            }

            newFile.close();


        } catch (Exception e) {
            System.out.println("File opening failed!");
        }


    }
}
