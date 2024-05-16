package tech.bugstars.tomcat.chapter01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

    public static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        File file = new File(HttpServer.WEB_ROOT, request.getUri());
        if(file.exists()){
            try(FileInputStream fis = new FileInputStream(file);) {
                int ch;
                // 发送HTTP响应头
                output.write("HTTP/1.1 200 OK\r\n".getBytes());
                output.write("Content-Type: text/html\r\n".getBytes()); // 假设getMimeType是一个获取MIME类型的函数
                output.write(("Content-Length: " + file.length() + "\r\n").getBytes()); // 假设文件大小是已知的
                output.write("\r\n".getBytes()); // 空行表示响应头结束
                while((ch = fis.read(bytes,0,BUFFER_SIZE)) != -1){
                    output.write(bytes,0,ch);
                }
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes());
        }
    }
}
