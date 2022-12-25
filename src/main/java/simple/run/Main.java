package simple.run;

public class Main {

    public static void main(String[] args) {
       SyncRunCmd cmd  = new SyncRunCmd(args).exec();
       System.out.println(cmd.getOutResult());
       System.out.println(cmd.getErrorResult());
       System.out.println(cmd.getExpectionResult());
    }
}
