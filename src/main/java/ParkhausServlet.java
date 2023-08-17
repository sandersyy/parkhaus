

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/parkhaus")
public class ParkhausServlet extends HttpServlet { //main servlet
    private Parkhaus parkhaus;

    public Parkhaus getParkhaus() {
        return parkhaus;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        parkhaus = new Parkhaus(100);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException{ //print parking house
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        float sum = parkhaus.getSum(); //return sum of all ticket prices in parking house at this time

        out.println("<html><body>");
        out.println("Die Summe des Tagesumsatzes: " + sum + "<br>");

        float average = parkhaus.getAverage(); //return average of all ticket prices in parking house at this time

        out.println("Der durchschnittliche Tagesumsatz: " + average + "<br>");

        out.println("Aktuell freie Plätze beträgt: " + parkhaus.getfreiplatz() + "<br>");//return the actual free parkings places

        out.println("Aktuell freie Frauenparkplätze: " + parkhaus.getFreeSpecialSlots('F') + "<br>");

        out.println("Aktuell freie Behindertenparkplätze: " + parkhaus.getFreeSpecialSlots('B') + "<br>");

        out.println("Aktuell freie Kinderparkplätze: " + parkhaus.getFreeSpecialSlots('K') + "<br>");

        for(Auto auto : parkhaus.getParkplaetze()){ //print all cars in the parking house
            out.println(auto.toString() + "<br>");
        }
        out.println("</body></html>");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = getBody( request );
        String[] params = body.split(",");
        String event = params[0];


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        if(("ticketType=" + event.charAt(11) + "&selectedOption=enter&control=Auswahl").equals( event )) { //checks if event is enter
            out.println(parkhaus.getTicket(event.charAt(11))); //enter parking house function
        }
        else if("selectedOption=leave&control=Auswahl".equals( event )){ //checks if event is leave
            parkhaus.payTicket(); //leave parking house function
            out.println("Sie haben das Parkhaus verlassen.");
        }


    }

    private static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
    }

}
