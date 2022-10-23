package sample;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.RunnableFuture;

public class RunCmd {

    private String[] cmd;
    private String outResult = "";
    private String errorResult = "";
    private String expectionResult = "";
    private boolean executing;
    private Integer exitValue;

    private long timeOut = 3000L;

    public RunCmd(long timeOut, String... cmd) {
        this.timeOut = timeOut;
        this.cmd = cmd;
    }
    public RunCmd(String... cmd) {
        this.cmd = cmd;
    }

    public synchronized RunCmd exec() {
        try {
            cleanResults();
            executing = true;

            final Process proc = new ProcessBuilder(cmd).start();
            watchDog(proc);

            InputStream in = proc.getInputStream();
            Scanner sin = new Scanner(in);

            InputStream err = proc.getErrorStream();
            Scanner serr = new Scanner(err);

            while (sin.hasNext()) outResult += " " + sin.nextLine();
            while (serr.hasNext()) errorResult += " " + serr.nextLine();

            outResult = outResult.trim();
            errorResult = errorResult.trim();
            exitValue = proc.exitValue();

            proc.destroy();

        } catch (Exception ex) {
            expectionResult += ex;
        } finally {
            executing = false;
        }
        return this;
    }

    private void watchDog(Process proc) {
        new Thread(() -> {
            try {
                Thread.sleep(timeOut);
                if (proc.isAlive())
                    proc.destroy();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void cleanResults() {
        outResult = "";
        errorResult = "";
        expectionResult = "";
        exitValue = null;
    }

    public String getOutResult() {
        return outResult;
    }

    public String getErrorResult() {
        return errorResult;
    }

    public String getExpectionResult() {
        return expectionResult;
    }

    public boolean isExecuting() {
        return executing;
    }

    public Integer getExitValue() {
        return exitValue;
    }
}
