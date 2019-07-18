package com.acme.edu;

public class CharCommand extends Command {
    private char message;

    public String state = "Char";

    public CharCommand(char message) {
        this.message = message;
    }

    public Character getMessage() {
        return this.message;
    }

    public String toString() {
        return ("char: " + message);
    }

    public void accumulate(Command prevCommand) {
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return true;
    }
}
