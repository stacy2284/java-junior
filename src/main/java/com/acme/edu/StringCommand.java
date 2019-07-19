package com.acme.edu;

import java.util.Objects;

public class StringCommand extends Command{
    private String message;

    public int numberOfIdenticalStr;

    public StringCommand(String message) {
        this.message = message;
        this.numberOfIdenticalStr = 1;
        this.state = "String";
    }

    public String getMessage(){
        return this.message;
    }

    public String toString() {
        if (numberOfIdenticalStr == 1) {
            return("string: " + message);
        }
        return("string: " + message + " (x" + numberOfIdenticalStr + ")");
    }

    public void accumulate(Command prevCommand) {
        if (prevCommand instanceof StringCommand && !isDifferentString(prevCommand)) {
            this.numberOfIdenticalStr += ((StringCommand)prevCommand).numberOfIdenticalStr;
        }
    }

    private boolean isDifferentString(Command prevCommand) {
        return !Objects.equals(prevCommand.getMessage(), this.getMessage());
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return isDifferentString(prevCommand);
    }
}
