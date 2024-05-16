package tech.bugstars.mytomcat.chapter02;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {

    public static final String SHUTDOWN_COMMAND = "/shutdown";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer1 httpServer1 = new HttpServer1();
        httpServer1.await();
    }

    private void await(){
        int port = 8080;
        try(ServerSocket serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"))) {
            while(!shutdown){
                Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                Request request = new Request(input);
                request.parse();
                Response response = new Response(output);
                response.setRequest(request);

                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor1 servletProcessor1 = new ServletProcessor1();
                    servletProcessor1.process(request,response);
                }else {
                    StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                    staticResourceProcessor.process(request,response);
                }
                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
