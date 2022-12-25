package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RunSyncCmdTest {

    @Test
    public void shouldRunEcho() {
        SyncRunCmd cmd = new SyncRunCmd("echo", "hello world").exec();
        Assertions.assertEquals("hello world" + System.lineSeparator(), cmd.getOutResult());
    }

    @Test
    public void shouldRunJavaVersion() {
        SyncRunCmd cmd = new SyncRunCmd("java", "-version");
        Assertions.assertFalse(cmd.isExecuting());
        cmd.exec();
        Assertions.assertNotEquals("", cmd.getErrorResult());
        Assertions.assertFalse(cmd.isExecuting());
    }

    @Test
    public void shouldFailUnknownProcess() {
        SyncRunCmd cmd = new SyncRunCmd("unknown.exec").exec();
        Assertions.assertTrue(cmd.getExpectionResult().contains("IOException"));
        Assertions.assertEquals(-1, cmd.getExitValue());
    }

    @Test
    public void shouldNotHangUp() {
        SyncRunCmd cmd = new SyncRunCmd(100, "sleep", "10").exec();
        Assertions.assertTrue(-1 == cmd.getExitValue() || 143 == cmd.getExitValue());
    }

    @Test
    public void shouldRunScript() {
        SyncRunCmd cmd = new SyncRunCmd("sh", "src/test/resources/test-scripts/print-to-10.sh").exec();
        Assertions.assertEquals("", cmd.getErrorResult());
        String expected = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .map(n -> n + System.lineSeparator())
                .collect(Collectors.joining());
        Assertions.assertEquals(expected, cmd.getOutResult());
    }

    @Test
    public void shouldGuessTherightNumber() {
        SyncRunCmd cmd = new SyncRunCmd(1000, "sh", "src/test/resources/test-scripts/guess-number.sh").exec("7");
        Assertions.assertEquals("", cmd.getErrorResult());
        Assertions.assertTrue(cmd.getOutResult().indexOf("yes it is 7!") > -1);
        // unreliable
        // Assertions.assertEquals(0, cmd.getExitValue());
    }

    @Test
    public void shouldPassMultipleInteractiveInputs() {
        String args[] = {"sh", "src/test/resources/test-scripts/multiple-inputs.sh"};
        SyncRunCmd cmd = new SyncRunCmd(1000, args).exec("7", "5");
        Assertions.assertTrue(cmd.getOutResult().indexOf("7 is bigger") > -1);

    }

}
