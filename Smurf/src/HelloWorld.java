/*
 * Just some testing of Jetty with Netapp API.
 * 
 * Author: Geoff Golliher (brokenway@gmail.com)
 * 
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import netapp.manage.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HelloWorld extends AbstractHandler {
  public void handle(String target, Request baseRequest,
      HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    NaElement nae;
    NaElement enae;
    NaServer s;
    String Res1 = "";
    String Res2 = "";

    try {
      // Initialize connection to server, and
      // request version 1.3 of the API set
	
      s = new NaServer("192.168.1.112", 1, 9);
      s.setStyle(NaServer.STYLE_LOGIN_PASSWORD);
      s.setAdminUser("root", "XXXXXXX");

      // Invokes ONTAPI API to get the ONTAP
      // version number of a filer
      nae = new NaElement("system-get-version");
      enae = s.invokeElem(nae);
      Res1 = "Hello!  Filer version is ";
      Res2 = enae.getChildContent("version");
      s.close();
    } catch (Exception e) {
      System.err.println(e.toString());
      System.exit(1);
    }
    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);
    baseRequest.setHandled(true);
    response.getWriter().println("<h1>Hello World</h1>");
    response.getWriter().println("<h3>" + Res1 + "</h3>");
    response.getWriter().println("<h3>" + Res2 + "</h3>");
  }

  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);
    server.setHandler(new HelloWorld());

    server.start();
    server.join();
  }
}