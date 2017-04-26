package com.github.shmvanhouten.lesson5;

import java.io.*;

public class HelloWorld {
    public static void main(String[] args) {

//        writeAFile();
        readAFile();
        readFromTerminalInput();
        int number = new Integer(4);
        pi(number);

        int smallNumber = 6563;
        long largeNumber = smallNumber;
        short extraSmall = (short) smallNumber;

        boolean wantFruit = true;

        String s = wantFruit ? "Appel" : "MarsBar";

        if (wantFruit) {
            s = "Appel";
        } else {
            s = "MarsBar";
        }




    }

    public static void pi (Integer i) {
        System.out.println(i);
    }

    private static void readFromTerminalInput() {
        InputStream in = System.in;
        InputStreamReader isr = new InputStreamReader(in);
        try(BufferedReader reader = new BufferedReader(isr)){
            String readLine = reader.readLine();
            while(readLine != null){
                System.out.println(readLine);
                readLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] readAFile() {
        File file = new File("/home/sjoerd/Documents","sjoerd.txt");
        try(FileInputStream fis = new FileInputStream(file);BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int read = fis.read();
            System.out.println(read);


            String readLine = reader.readLine();
            // fileInputStream was dus niet nodig!

            while(readLine != null){
                System.out.println(readLine);
                readLine = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeAFile() {
        File file = new File("/home/sjoerd/Documents","sjoerd.txt");
        try (FileOutputStream fos = new FileOutputStream(file); PrintWriter pw = new PrintWriter(fos)){
            file.createNewFile();
            fos.write("Hello World!\n".getBytes());
            pw.println("why doesn't world say anything back?" + System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}