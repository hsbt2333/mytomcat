package tech.bugstars.mytomcat.chapter02;

import tech.bugstars.mytomcat.chapter02.facade.ResponseFacade;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig");
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service");
        if (res instanceof Response) {
            System.out.println("未使用外观模式");
            Response response = (Response) res;
            response.sendStaticResource();
        }else if (res instanceof ResponseFacade) {
            System.out.println("使用外观模式");
            ResponseFacade response = (ResponseFacade) res;
            //response.sendStaticResource();    //调不了
        }
        PrintWriter writer = res.getWriter();
        writer.println("HTTP/1.1 200 OK\r\n");
        writer.println("123456");
        writer.print("654321");
    }

    @Override
    public String getServletInfo() {
        System.out.println("getServletInfo");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
