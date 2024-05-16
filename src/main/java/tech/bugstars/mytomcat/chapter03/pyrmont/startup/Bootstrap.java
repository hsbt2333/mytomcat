package tech.bugstars.mytomcat.chapter03.pyrmont.startup;

import tech.bugstars.mytomcat.chapter03.pyrmont.connector.http.HttpConnector;

public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }
}
