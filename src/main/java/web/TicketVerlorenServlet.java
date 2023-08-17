package web;

import logic.Ticket.ParkhausTicket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/verloren")
public class TicketVerlorenServlet extends ParkhausServlet { //main servlet
    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { //print parking house
        request.setAttribute("oeffnungszeiten", getPersistentParkhaus().getOpeningstime(new Date()));
        request.getRequestDispatcher("/ticketVerloren.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String email=request.getParameter("email");
        if(!getPersistentParkhaus().lostTicketExist(email)){
            request.setAttribute("warning","Ticket mit dieser Email-adresse nicht gefunden!");
            doGet(request,response);
        } else{
            ParkhausTicket t= getPersistentParkhaus().findLostTicket(email);
            request.setAttribute("erfolg","ticket gefunden! Ihre TicketID lautet :"+t.getId()+"<br> bitte merken!");
           // request.setAttribute("id", t.getId());
            doGet(request,response);
        }

    }


}
