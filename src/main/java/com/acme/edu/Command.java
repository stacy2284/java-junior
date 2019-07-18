package com.acme.edu;

/**
 * Created by Java_5 on 17.07.2019.
 */
public abstract class Command {
    public String state;

    public boolean isChanged(Command prevCommand) {
        return !this.state.equals(prevCommand.state);
    }

    public abstract String toString();

    public abstract void accumulate(Command prevCommand);

    public abstract boolean isOverflowed(Command prevCommand);
    public abstract Object getMessage();
}
