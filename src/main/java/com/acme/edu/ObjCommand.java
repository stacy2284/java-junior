package com.acme.edu;

/**
 * Created by Java_5 on 19.07.2019.
 */
public class ObjCommand extends Command {
    private Object message;

    public ObjCommand(Object message) {
        this.message = message;
        this.state = "Obj";
    }

    public Object getMessage() {
        return this.message;
    }

    public String toString() {
        return ("reference: " + message);
    }

    public void accumulate(Command prevCommand) {
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return true;
    }
}
