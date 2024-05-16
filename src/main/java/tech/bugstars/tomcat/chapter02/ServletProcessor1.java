package tech.bugstars.tomcat.chapter02;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class ServletProcessor1 {
    public void process(Request request, Response response) {
        String servletName = request.getUri().substring(request.getUri().lastIndexOf("/") + 1);
        URLClassLoader urlClassLoader = null;
        try {
            /*URL[] urls = new URL[1];
            File classpath = new File(this.getClass().getResource("/").getPath());
            URLStreamHandler urlStreamHandler = null;
            String repository = new URL("file", null, classpath.getCanonicalPath() + File.separator).toString();
            // urlStreamHandler 明明是 null 为什么还要创建变量，这样做是为了确定构造函数，否则，编译器就无法知道该调用哪个构造函数了
            urls[0] = new URL(null, repository, urlStreamHandler);
            urlClassLoader = new URLClassLoader(urls);*/
            File classpath = new File(this.getClass().getResource("/").getPath());
            URL url = new URL("file:"+classpath);
            urlClassLoader = new URLClassLoader(new URL[]{url});
        } catch (IOException e) {
            e.printStackTrace();
        }
        servletName = "tech.bugstars.tomcat.chapter02." + servletName;
        Class myClass = null;
        try {
            myClass = urlClassLoader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Servlet servlet = null;
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request, response);
        } catch (InstantiationException | IOException | ServletException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
