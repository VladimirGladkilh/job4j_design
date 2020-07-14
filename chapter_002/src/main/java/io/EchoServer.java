package io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    static final String COMMAND_EXIT = "exit";
    static final String COMMAND_HELLO = "hello";
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean run = true;
            while (run) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String command="";
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.indexOf("msg=") > 0) {
                            command = str.split("msg=")[1];
                            command = command.split("HTTP/1.1")[0];
                            if (command.toLowerCase().equals(COMMAND_HELLO)) {
                               command = COMMAND_HELLO;
                            } else {
                                if (command.toLowerCase().equals(COMMAND_EXIT)) {
                                    command = COMMAND_EXIT;
                                    run = false;
                                }
                            }
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(command.getBytes());
                }
            }
        }
    }
}