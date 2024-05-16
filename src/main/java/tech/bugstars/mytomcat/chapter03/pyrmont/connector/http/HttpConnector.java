package tech.bugstars.mytomcat.chapter03.pyrmont.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable {

    boolean stopped;
    private String scheme = "http";

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stopped){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                continue;
            }

        }
    }

    public String getScheme() {
        return scheme;
    }
}
