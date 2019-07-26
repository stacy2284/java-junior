package com.acme.edu;

/**
 * Created by Java_5 on 26.07.2019.
 */

import com.acme.edu.Command.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        String inputLine;

        LoggerController serverLogger = null;

        try {
            serverLogger = new LoggerController(new LogFileSaver("test.txt"));
        } catch (FileException e) {
            e.printStackTrace();
        }

        try (final ServerSocket serverSocket = new ServerSocket(666)) {
            while (true) {
                Socket client = serverSocket.accept();

                try (BufferedReader in =
                             new BufferedReader(
                                     new InputStreamReader(
                                             new BufferedInputStream(
                                                     client.getInputStream())));
                     final BufferedWriter out =
                             new BufferedWriter(
                                     new OutputStreamWriter(
                                             new BufferedOutputStream(
                                                     client.getOutputStream())))) {

                    while (true) {
                        inputLine = in.readLine();
                        String[] commandAndMessage = inputLine.split(" ");
//                        out.write(">>> " + commandAndMessage[0]);
                        //out.newLine();
                        //out.flush();

                        switch(commandAndMessage[0]) {
                            case ("int"): {
                                int intMessage =  Integer.valueOf(commandAndMessage[1]);
                                serverLogger.log(new IntCommand(intMessage));
                                break;
                            }
                            case ("byte"): {
                                byte byteMessage =  Byte.valueOf(commandAndMessage[1]);
                                serverLogger.log(new ByteCommand(byteMessage));
                                break;
                            }
                            case ("char"): {
                                if (commandAndMessage[1].length() > 1) {
                                    throw new IOException("Illegal char value.");
                                }
                                char charMessage =  commandAndMessage[1].charAt(0);
                                serverLogger.log(new CharCommand(charMessage));
                                break;
                            }
                            case ("string"): {
                                serverLogger.log(new StringCommand(commandAndMessage[1]));
                                break;
                            }
                            case ("close"): {
                                serverLogger.close();
                                break;
                            }
                            default: {
                               throw new IOException("Illegal operation type");
                            }
                        }



                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FileException e) {
                    e.printStackTrace();
                } catch (NullMessageException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
