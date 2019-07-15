package com.acme.edu;


import java.util.Objects;

public class Logger {
    private static int summ=0;
    private static void ConsoleOut(String type, String message) {
        System.out.println(type + message);
    }

    public static void log(int message) {
        summ = summ + message;
        ConsoleOut("primitive: ", Integer.toString(message));
    }

    public static void log(byte message) {
        ConsoleOut("primitive: ", Byte.toString(message));
    }

    public static void log(char message) {
        ConsoleOut("char: ", Character.toString(message));
    }

    public static void log(String message) {
        ConsoleOut("summ: ", Integer.toString(summ));
        summ = 0;
        ConsoleOut("string: ", message);
    }

    public static void log(boolean message) {
        ConsoleOut("primitive: ", Boolean.toString(message));
    }

    public static void log(Object obj) {
        if (obj != null)
            ConsoleOut("reference: ", Objects.toString(obj));
        else
            ConsoleOut("reference: ", "null");
    }
}
