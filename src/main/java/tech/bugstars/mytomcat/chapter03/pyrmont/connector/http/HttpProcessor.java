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
            HttpRequest request = new HttpRequest(input);
            HttpResponse response = new HttpResponse(output);
            response.setRequest(request);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
