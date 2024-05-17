package tech.bugstars.mytomcat.chapter03.pyrmont.connector.http;

import java.io.OutputStream;

public class HttpResponse {

    private HttpRequest request;
    private OutputStream output;

    public HttpResponse(OutputStream output) {
        this.output = output;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }
}
