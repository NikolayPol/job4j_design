package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String resp;
                    for (String str = in.readLine();
                         str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("/?msg=Exit")) {
                            server.close();
                        }
                        if (str.contains("/?msg")) {
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.flush();
                            out.write("Hello, dear friend.\r\n".getBytes());
                            out.flush();
                            resp = str.split("=")[1];
                            out.write(resp.getBytes(StandardCharsets.UTF_8));
                            out.flush();
                        }
                    }
                }
            }
        }
    }
}
