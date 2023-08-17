package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bezahlen")
public class TicketBezahlungServlet extends ParkhausServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {//print parking house
        request.getRequestDispatcher("ticketBezahlung.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String body = getBody(request);
        String[] params = body.split(",");
        String event = params[0];

        if (("ticketID=" + event.substring(9, 13)).equals(event.substring(0, 13))) { //checks if event is "leave"
            getPersistentParkhaus().
                    payTicket(event.substring(9, 13)); //pay ticket function
            request.setAttribute("Beleg", getPersistentParkhaus(). //tell dispatcher to store beleg
                    getParkplaetze(). //return parkplaetze array
                    get(getPersistentParkhaus(). //return ticket
                    getTicketIndex(event.substring(9, 13))). // with id from web layer
                    getState(). //return state of this ticket
                    toString(getPersistentParkhaus() //return the to string method of the state (Beleg)
                    .getActualTime())); //time is needed for calculation of the price
            getPersistentParkhaus().
                    leave(event.substring(9, 13)); //leave parkinghouse function
        }
        System.out.println(event);
        request.getRequestDispatcher("ticketBezahlung.jsp").forward(request, response);
    }
}
