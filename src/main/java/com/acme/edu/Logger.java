package com.acme.edu;


import com.acme.edu.Command.*;

public class Logger {
    private static LoggerController LC = new LoggerController(new LogConsoleSaver());

    public static void flush(){
      try {
            LC.flush();
        }
        catch(NothingToFlushException e) {
            e.printStackTrace();
        }
    }

//  public static void log(int[] message) {
//
//  }

    public static void log(int message) {
        LC.log(new IntCommand(message));
    }

    public static void log(byte message) {
        LC.log(new ByteCommand(message));
    }

    public static void log(char message) {
        LC.log(new CharCommand(message));
    }

    public static void log(String message) {
        try {
            LC.log(new StringCommand(message));
        } catch (NullMessageException e) {
            e.printStackTrace();
        }
    }

    public static void log(boolean message) {
        LC.log(new BoolCommand(message));
    }

    public static void log(Object message) {
        try {
            LC.log(new ObjCommand(message));
        } catch (NullMessageException e) {
            e.printStackTrace();
        }
    }

    public static void log(int[] message) {
        LC.log(new IntArrayCommand(message));
    }

    public static void log(String ... message) {
        LC.log(new StringVarargCommand(message));
    }

}





