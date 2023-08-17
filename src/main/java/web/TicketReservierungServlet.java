package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/reservierung")
public class TicketReservierungServlet extends ParkhausServlet{
    final String reserveTime = "reservationTime";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {//print parking house
        request.setAttribute(reserveTime, convertDateToString(getPersistentParkhaus().getActualTime()) );
        request.getRequestDispatcher("ticketReservierung.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String body = ParkhausServlet.getBody( request );
        String[] params = body.split(",");
        String event = params[0];
        System.out.println(event);
        request.setAttribute(reserveTime, convertDateToString(getPersistentParkhaus().getActualTime()));

        if("aktion=reservieren".equals(event.substring(0, 18))){
            System.out.println(event);
            Date timeInput = convertStringToDate(event.substring(29));
            if(getPersistentParkhaus().jumpTime(timeInput, getPersistentParkhaus().getActualTime())){
                request.setAttribute(reserveTime, convertDateToString(timeInput));
                System.out.println("Reservierung erfolgreich");

                request.setAttribute("Beleg", getPersistentParkhaus().reserveTicket(timeInput));
            }
        }

        if("aktion=stornieren".equals(event.substring(14))){
            System.out.println(event);
                request.setAttribute("Beleg", getPersistentParkhaus().cancelTicket(event.substring(9, 13)));
            }




        request.getRequestDispatcher("ticketReservierung.jsp").forward(request, response);
    }
}
