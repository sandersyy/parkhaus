package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ticket")
public class TicketVerwaltungServlet extends ParkhausServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {//print parking house
        request.getRequestDispatcher("ticketVerwaltung.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("ticketVerwaltung.jsp").forward(request, response);
    }
}
