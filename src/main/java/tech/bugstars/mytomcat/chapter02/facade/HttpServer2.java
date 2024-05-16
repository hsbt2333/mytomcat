package tech.bugstars.mytomcat.chapter02.facade;

import tech.bugstars.mytomcat.chapter02.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 外观模式 让 servlet 安全使用 request 和 response
 * 因为ServletProcessor 中传入进去的是自定义实现的 request 和 response
 * 调用service时，会向上强转成 ServletRequest 和 ServletResponse
 * 但如果 Servlet 里面对这两个对象强转回 Request 和 Response，就会访问这两个类的公共方法
 * 所以用外观模式进行了一层封装
 */
public class HttpServer2 {
    public static final String SHUTDOWN_COMMAND = "/shutdown";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer2 httpServer2 = new HttpServer2();
        httpServer2.await();
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
                    ServletProcessor2 servletProcessor2 = new ServletProcessor2();
                    servletProcessor2.process(request,response);
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
