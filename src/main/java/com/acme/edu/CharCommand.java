package com.acme.edu;

/**
 * Created by Java_5 on 18.07.2019.
 */
public class CharCommand extends Command{
    private char message;

    public String state = "Char";

    public CharCommand(char message) {
        this.message = message;
    }

    public Character getMessage() {
        return this.message;
    }

    public String decorate() {
        return("char: ");
    }

    public String toString() {
        return("char: " + message);
    }

    public boolean isOverflowed(Command prevCommand) {
        return true;
    }

    public void accumulate(Command prevCommand) {}
}
