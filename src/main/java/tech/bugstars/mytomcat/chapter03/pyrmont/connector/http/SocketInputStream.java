package tech.bugstars.mytomcat.chapter03.pyrmont.connector.http;

import java.io.IOException;
import java.io.InputStream;

public class SocketInputStream extends InputStream {
    protected InputStream is;
    protected byte[] buf;

    public SocketInputStream(InputStream is,int bufSize) {
        this.is = is;
        this.buf = new byte[bufSize];
    }

    @Override
    public int read() throws IOException {
        return is.read();
    }

    public HttpRequestLine readRequestLine(){
        return null;
    }

    public HttpHeader readHeader(){
        return null;
    }
}
