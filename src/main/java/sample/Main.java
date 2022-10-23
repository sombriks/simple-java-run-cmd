package sample;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
       RunCmd cmd  = new RunCmd(args).exec();
       System.out.println(cmd.getOutResult());
       System.out.println(cmd.getErrorResult());
       System.out.println(cmd.getExpectionResult());
    }
}
