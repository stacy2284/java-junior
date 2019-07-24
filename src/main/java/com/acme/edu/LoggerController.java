package com.acme.edu;

import com.acme.edu.Command.Command;

public class LoggerController {

    private LogSaver saver;

    private Command prevCommand = null;

    public LoggerController(LogSaver saver) {
        this.saver = saver;
    }

    public void flush() throws NothingToFlushException {
        if (prevCommand==null) {
            throw new NothingToFlushException("Can't flush before logging");
        }
        saver.save(prevCommand.toString());
        prevCommand = null;
    }

    public void log(Command command) {
        boolean shouldFlushNow = ((prevCommand != null) && ((command.isChanged(prevCommand) || command.shouldFlushNow(prevCommand))));
        if (shouldFlushNow) {
            saver.save(prevCommand.toString());
            prevCommand = null;
        }
        command.accumulate(prevCommand);
        prevCommand = command;
    }
}
