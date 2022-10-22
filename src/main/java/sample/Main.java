package sample;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<String> exec(String[] cmd) {
        String results[] = {"", ""};
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            Process proc = builder.start();
            InputStream in = proc.getInputStream();
            InputStream err = proc.getErrorStream();
            Scanner sin = new Scanner(in);
            Scanner serr = new Scanner(err);

            while (sin.hasNext()) results[0] += " " + sin.nextLine();
            while (serr.hasNext()) results[1] += " " + serr.nextLine();

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return Arrays.asList(results);
    }

    public static void main(String[] args) throws Exception {
        String cmd1[] = {"java", "-version"};
        String cmd2[] = {"mysql", "-V"};
        String cmd3[] = {"notepad.exe"};
        String cmd4[] = {"ls"};
        System.out.println(exec(cmd1));
        System.out.println(exec(cmd2));
        System.out.println(exec(cmd4));
        System.out.println(exec(cmd3));
    }
}