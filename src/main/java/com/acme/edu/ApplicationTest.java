package com.acme.edu;

/**
 * Created by Java_5 on 18.07.2019.
 */
public class ApplicationTest {
    public static void main(String[] args) {
        LoggerController LC = new LoggerController(new LogSaver());

        LC.log(new IntCommand(5));
        LC.log(new IntCommand(6));
        LC.log(new CharCommand('c'));
    }
}
