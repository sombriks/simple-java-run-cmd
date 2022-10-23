package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunCmdTest {

    @Test
    public void shouldRunEcho() {
        RunCmd cmd = new RunCmd("echo", "hello world").exec();
        Assertions.assertEquals("hello world", cmd.getOutResult());
    }

    @Test
    public void shouldRunJavaVersion() {
        RunCmd cmd = new RunCmd("java", "-version");
        Assertions.assertFalse(cmd.isExecuting());
        cmd.exec();
        Assertions.assertNotEquals("", cmd.getErrorResult());
        Assertions.assertFalse(cmd.isExecuting());
    }

    @Test
    public void shouldFailUnknownProcess() {
        RunCmd cmd = new RunCmd("unknown.exec").exec();
        Assertions.assertTrue(cmd.getExpectionResult().contains("IOException"));
    }

}
