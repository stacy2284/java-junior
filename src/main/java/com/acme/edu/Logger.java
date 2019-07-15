package com.acme.edu;


import java.util.Objects;

public class Logger {
    private static void OutDesign(String type, String message) {
        System.out.println(type + message);
    }

    public static void log(int message) {
        OutDesign("primitive: ", Integer.toString(message));
    }

    public static void log(byte message) {
        OutDesign("primitive: ", Byte.toString(message));
    }

    public static void log(char message) {
        OutDesign("char: ", Character.toString(message));
    }
    public static void log(String message) {
        OutDesign("string: ", message);
    }

    public static void log(boolean message) {
        OutDesign("primitive: ", Boolean.toString(message));
    }
    public static void log(Object obj) {
        OutDesign("reference: ", Objects.toString(obj));
    }
}
