package io.github.sombriks.simpleruncmd;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SyncRunCmd {

    private String[] cmd;
    private String outResult = "";
    private String errorResult = "";
    private String expectionResult = "";
    private boolean executing;
    private int exitValue = 0;
    private long timeOut = 0L;

    public SyncRunCmd(long timeOut, String... cmd) {
        this.timeOut = timeOut;
        this.cmd = cmd;
    }

    public SyncRunCmd(String... cmd) {
        this.cmd = cmd;
    }

    public synchronized SyncRunCmd exec(String ...interactions) {
        try {
            cleanResults();
            executing = true;

            final Process proc = new ProcessBuilder(cmd).start();

            InputStream in = proc.getInputStream();
            Scanner sin = new Scanner(in);

            InputStream err = proc.getErrorStream();
            Scanner serr = new Scanner(err);

            PrintWriter input = new PrintWriter(proc.getOutputStream());

            // wait for interactive input to be ready
            proc.waitFor(1L, TimeUnit.SECONDS);
            watchDog(proc);
            for(String interaction : interactions) {
                input.println(interaction);
                input.flush();
            }

            while (sin.hasNext()) {
                outResult += sin.nextLine() + System.lineSeparator();
            }
            while (serr.hasNext()) {
                errorResult += serr.nextLine() + System.lineSeparator();
            }

            exitValue = proc.exitValue();

            proc.destroy();

        } catch (Exception ex) {
            expectionResult += ex;
            exitValue = -1;
        } finally {
            executing = false;
        }
        return this;
    }

    private void watchDog(Process proc) {
        if (timeOut > 0) {
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
    }

    public void cleanResults() {
        outResult = "";
        errorResult = "";
        expectionResult = "";
        exitValue = 0;
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

    public int getExitValue() {
        return exitValue;
    }
}
