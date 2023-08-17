package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@WebServlet(urlPatterns = "/buchung")
public class TicketBuchungServlet extends ParkhausServlet { //main servlet
    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { //print parking house
        request.setAttribute("oeffnungszeiten", getPersistentParkhaus().getOpeningstime(new Date()));
        request.getRequestDispatcher("/ticketBuchung.jsp").forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String body = getBody(request);System.out.println(body);
//        String[] params = body.split(",");
//        String event = params[0];
//        if (("ticketType=" + event.charAt(11) + "&selectedOption=enter&control=Auswahl").equals(event)) { //checks if event is "enter"
//            request.setAttribute("Beleg", getPersistentParkhaus().getTicket(event.charAt(11))); //enter parking house function
//
//        }
        String ticketTyp=request.getParameter("ticketType");
        String email=request.getParameter("email");
        if(!ticketTyp.equals("T")&&!ticketTyp.equals("K")&&!ticketTyp.equals("F")&&!ticketTyp.equals("B")||email.equals("")){
            request.setAttribute("warning","Ticket Typ ungültig oder Email leer!");
            doGet(request,response);
        } else{
            request.setAttribute("erfolg","Eingabe gültig!");
            request.setAttribute("Beleg", getPersistentParkhaus().getTicket(ticketTyp.charAt(0)));
            getPersistentParkhaus().getLastBookedTicket(ticketTyp.charAt(0)).setEmail(email);//füge das Email zu dem entsprechenden Ticket

            doGet(request,response);
        }

    }


}
