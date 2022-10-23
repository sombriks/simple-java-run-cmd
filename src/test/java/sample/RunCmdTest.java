package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunCmdTest {

    @Test
    public void shouldRunEcho() {
        RunCmd cmd = new RunCmd("echo", "hello world").exec();
        Assertions.assertEquals("hello world", cmd.getOutResult());
        Assertions.assertEquals(0, cmd.getExitValue());
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
        Assertions.assertNull(cmd.getExitValue());
    }

    @Test
    public void shouldNotHangUp() {
        RunCmd cmd = new RunCmd(200, "sleep", "10").exec();
        Assertions.assertNull(cmd.getExitValue());

    }

}
