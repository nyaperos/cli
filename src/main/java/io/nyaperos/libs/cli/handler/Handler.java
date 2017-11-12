package io.nyaperos.libs.cli.handler;

import java.io.PrintStream;

public interface Handler {
    public boolean canHandle(String...args);
    public void handle(PrintStream out, String... args);   
}
