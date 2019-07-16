package com.acme.edu;


import java.util.Objects;

public class Logger {
    public static void close(){
        AccumConsoleOut(Types.CLOSE);
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

    enum Types {
        INT,
        BYTE,
        STRING,
        CHAR,
        BOOL,
        OBJ,
        CLOSE
    }

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

    private static void AccumConsoleOut(Types type) {
        if (type != Types.INT) {
            AccumIntConsoleOut();
        }
        if (type != Types.BYTE) {
            AccumByteConsoleOut();
        }
        if (type != Types.STRING) {
            StringConsoleOut();
        }
    }

    private static void smartSumm(int message) {
        int diff = 0;
        boolean isOverflowed;
        if (accumIntSumm >= 0) {
            diff =  Integer.MAX_VALUE - accumIntSumm;
            isOverflowed = diff < message;
        }
        else {
            diff =  Integer.MIN_VALUE - accumIntSumm;
            isOverflowed = diff > message;
        }
        if (!isOverflowed) {
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
        AccumConsoleOut(Types.INT);
        smartSumm(message);
        isPrevInt = true;
    }

    public static void log(byte message) {
        AccumConsoleOut(Types.BYTE);
        smartSumm(message);
        isPrevByte = true;
    }

    public static void log(char message) {
        AccumConsoleOut(Types.CHAR);
        ConsoleOut("char: ", Character.toString(message));
    }

    public static void log(String message) {
        AccumConsoleOut(Types.STRING);
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
        AccumConsoleOut(Types.BOOL);
        ConsoleOut("primitive: ", Boolean.toString(message));
    }

    public static void log(Object obj) {
        AccumConsoleOut(Types.OBJ);
        if (obj != null)
            ConsoleOut("reference: ", Objects.toString(obj));
        else
            ConsoleOut("reference: ", "null");
    }
}
