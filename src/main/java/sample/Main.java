package sample;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<String> exec(String[] cmd) throws Exception {
        ProcessBuilder builder = new ProcessBuilder(cmd);
        Process proc = builder.start();
        InputStream in = proc.getInputStream();
        InputStream err = proc.getErrorStream();
        Scanner sin = new Scanner(in);
        Scanner serr = new Scanner(err);

        String results[] = {"", ""};
        while (sin.hasNext()) results[0] += sin.nextLine();
        while (serr.hasNext()) results[1] += serr.nextLine();

        return Arrays.asList(results);
    }

    public static void main(String[] args) throws Exception {
        String cmd1[] = {"java", "-version"};
        String cmd2[] = {"mysql", "-V"};
        System.out.println(exec(cmd1));
        System.out.println(exec(cmd2));
    }
}