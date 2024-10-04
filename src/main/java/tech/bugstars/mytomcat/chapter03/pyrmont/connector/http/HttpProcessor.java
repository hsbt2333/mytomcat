package tech.bugstars.mytomcat.chapter03.pyrmont.connector.http;

import tech.bugstars.mytomcat.chapter03.pyrmont.core.ServletProcessor;
import tech.bugstars.mytomcat.chapter03.pyrmont.core.StaticResourceProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessor {
    private HttpRequest request;
    private HttpResponse response;

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();
            request = new HttpRequest(input);
            response = new HttpResponse(output);
            response.setRequest(request);
            parseRequest(input,output);
            parseHeader(input);

            if(request.getRequestURI().startsWith("/servlet/")){
                ServletProcessor processor = new ServletProcessor();
                processor.process(request,response);
            }else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request,response);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRequest(SocketInputStream input, OutputStream output) {
        input.readRequestLine();
    }

    private void parseHeader(SocketInputStream input) {

    }
}
