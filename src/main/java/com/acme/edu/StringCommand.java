package com.acme.edu;

import java.util.Objects;

public class StringCommand extends Command{
    private String message;

    private int numberOfIdenticalStr;

    public String state = "String";

    public StringCommand(String message) {
        this.message = message;
        numberOfIdenticalStr = 0;
    }

    public String getMessage(){
        return this.message;
    }

    public String toString() {
        return("string: " + message);
    }

    public void accumulate(Command prevCommand) {
    }

    private boolean isDifferentString(Command prevCommand) {
        return !Objects.equals(prevCommand.getMessage(), this.getMessage());
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return isDifferentString(prevCommand);
    }
}
