package com.acme.edu;


import java.util.Objects;

public class Logger {
    public static void close(){
        AccumConsoleOut();
        StringConsoleOut();
    }

    public static void flush(){
        accumIntSumm = 0;
        isPrevInt = false;
        accumByteSumm = 0;
        isPrevByte = false;
        numberOfIdenticalStr = 0;
        prevString = null;
    }

    private static int accumIntSumm = 0;
    private static boolean isPrevInt = false;

    private static int accumByteSumm = 0;
    private static boolean isPrevByte = false;

    private static int numberOfIdenticalStr = 0;
    private static String prevString;

    private static void AccumIntConsoleOut() {
        if (isPrevInt) {
            ConsoleOut("primitive: ", Integer.toString(accumIntSumm));
            flush();
        }
    }

    private static void AccumByteConsoleOut() {
        if (isPrevByte) {
            ConsoleOut("primitive: ", Integer.toString(accumByteSumm));
            flush();
        }
    }

    private static void AccumConsoleOut() {
        AccumByteConsoleOut();
        AccumIntConsoleOut();
    }

    private static void smartSumm(int message) {
        int maxDiff = Integer.MAX_VALUE - accumIntSumm;
        int minDiff = Integer.MIN_VALUE - accumIntSumm;
        if (maxDiff >= message && minDiff <= message) {
            accumIntSumm = accumIntSumm + message;
        } else {
            AccumIntConsoleOut();
            accumIntSumm = message;
        }
    }

    private static void smartSumm(byte message) {
        int maxDiff = Byte.MAX_VALUE - accumByteSumm;
        int minDiff = Byte.MIN_VALUE - accumByteSumm;
        if (maxDiff >= message && minDiff <= message) {
            accumByteSumm = accumByteSumm + message;
        } else {
            AccumByteConsoleOut();
            accumByteSumm = message;
        }
    }

    private static void StringConsoleOut() {
        if  (numberOfIdenticalStr == 0) return;
        if (numberOfIdenticalStr == 1) {
            System.out.println(prevString);
        }
        else {
            System.out.println(prevString + " (x" + numberOfIdenticalStr + ")");
        }
        flush();
    }

    private static void ConsoleOut(String type, String message) {
        System.out.println(type + message);
    }

    public static void log(int message) {
        AccumByteConsoleOut();
        StringConsoleOut();
        smartSumm(message);
        isPrevInt = true;
    }

    public static void log(byte message) {
        AccumIntConsoleOut();
        StringConsoleOut();
        smartSumm(message);
        isPrevByte = true;
    }

    public static void log(char message) {
        AccumConsoleOut();
        StringConsoleOut();
        ConsoleOut("char: ", Character.toString(message));
    }

    public static void log(String message) {
        AccumConsoleOut();
        if(Objects.equals(message, prevString)) {
            numberOfIdenticalStr++;
        } else {
            StringConsoleOut();
            prevString = message;
            numberOfIdenticalStr = 1;
        }
        ConsoleOut("string: ", message);
    }

    public static void log(boolean message) {
        StringConsoleOut();
        AccumConsoleOut();
        ConsoleOut("primitive: ", Boolean.toString(message));
    }

    public static void log(Object obj) {
        StringConsoleOut();
        AccumConsoleOut();
        if (obj != null)
            ConsoleOut("reference: ", Objects.toString(obj));
        else
            ConsoleOut("reference: ", "null");
    }
}
