package org.example;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Hello world!
 *
 */
public class App extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
       doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String total = request.getParameter("total");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World say Hello!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello World say Hello!</h1>");
        out.println(total);


        out.println("<a href=\"index.jsp\">return</a>");
        out.println("</body>");
        out.println("</html>");

    }
}
