# Simple Java Run Cmd

Simple java library to spawn system processes. Expect to run command line tools
and get some degree of control over input and output.

## Sample usage

```java
public class Example {
    public static void main(String ...args) {
        SyncRunCmd cmd = new SyncRunCmd("echo", "hello world").exec();
        System.out.println(cmd.getOutResult()); // prints hello world
    }    
}
```

## Goals

- [X] Spawn a process
- [X] Get results
- [X] Never hang up (watchdog)
- [X] Pass arguments
- [X] Pass 'interactive' input
- [ ] ~~Run on another thread~~
- [ ] ~~Get PID~~
- [ ] Observe process (out, error, exception, time)
- [ ] Proper logging

## Caveats

- This library intentionally treats inputs and outputs as text. 
  Do not pipe binary data to it, or it will break.
