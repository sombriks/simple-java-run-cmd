package sample;

import java.io.InputStream;
import java.util.Scanner;

public class RunCmd {

    private String[] cmd;
    private String outResult = "";
    private String errorResult = "";
    private String expectionResult = "";

    private boolean executing;

    public RunCmd(String... cmd) {
        this.cmd = cmd;
    }

    public RunCmd exec() {
        try {
            cleanResults();
            executing = true;

            Process proc = new ProcessBuilder(cmd).start();

            InputStream in = proc.getInputStream();
            Scanner sin = new Scanner(in);

            InputStream err = proc.getErrorStream();
            Scanner serr = new Scanner(err);

            while (sin.hasNext()) outResult += " " + sin.nextLine();
            while (serr.hasNext()) errorResult += " " + serr.nextLine();

            outResult = outResult.trim();
            errorResult = errorResult.trim();

        } catch (Exception ex) {
            expectionResult += ex;
        } finally {
            executing = false;
        }
        return this;
    }

    public void cleanResults() {
        outResult = "";
        errorResult = "";
        expectionResult = "";
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
}
