# Simple Java Run Cmd

![GitHub Workflow Status (with branch)](https://img.shields.io/github/actions/workflow/status/sombriks/simple-java-run-cmd/maven.yml?branch=main&label=maven%20build)
[![Available on maven central](https://img.shields.io/maven-central/v/io.github.sombriks/simple-run-cmd)](https://central.sonatype.dev/artifact/io.github.sombriks/simple-run-cmd/0.1.0)
![license](https://img.shields.io/github/license/sombriks/simple-java-run-cmd)

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
