package tech.bugstars.mytomcat.chapter02;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;


public class Response implements ServletResponse {
    private Request request;
    private OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void sendStaticResource() {
        File file = new File(Constants.WEB_ROOT, request.getUri());
        if (file.exists() && file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                output.write("HTTP/1.1 200 ok\r\n".getBytes());
                output.write("Content-Type: text/html\r\n".getBytes());
                output.write(("Content-Length: " + file.length() + "\r\n").getBytes());
                output.write("\r\n".getBytes());
                byte[] bytes = new byte[2048];
                int length;
                while((length = fis.read(bytes)) != -1){
                    output.write(bytes,0,length);
                }
                //output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            try {
                output.write(errorMessage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public OutputStream getOutput() {
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        /* autoFlush 表示println会刷新输出，print不会刷新 */
        PrintWriter printWriter = new PrintWriter(output, true);
        return printWriter;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentLengthLong(long len) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
