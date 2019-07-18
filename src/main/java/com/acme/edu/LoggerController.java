package com.acme.edu;

/**
 * Created by Java_5 on 17.07.2019.
 */
public class LoggerController {

    private LogSaver saver;

    private LogState state;

    private Command prevCommand = null;

    LoggerController(LogSaver saver) {
        this.saver = saver;
    }

    void log(Command command) {
        if (command.isChanged(prevCommand) || command.isOverflowed(prevCommand)) {
            saver.save(prevCommand.toString());
            prevCommand = null;
        }
        command.accumulate(prevCommand);
        prevCommand = command;
    }
}
