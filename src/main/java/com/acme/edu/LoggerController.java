package com.acme.edu;

public class LoggerController {

    private LogSaver saver;

    private Command prevCommand = null;

    LoggerController(LogSaver saver) {
        this.saver = saver;
    }

    void close() {
        saver.save(prevCommand.toString());
    }

    void log(Command command) {

        if ((prevCommand != null) && (command.isChanged(prevCommand) || command.shouldFlushNow(prevCommand))) {
            saver.save(prevCommand.toString());
            prevCommand = null;
        }
        command.accumulate(prevCommand);
        prevCommand = command;
    }
}
