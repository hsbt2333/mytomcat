package tech.bugstars.mytomcat.chapter03.pyrmont.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessor {

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();
            // request = new
            // response = new

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
