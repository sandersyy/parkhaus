package web;

import logic.Rezension;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/rezensionen")
public class RezensionenServlet extends ParkhausServlet {
    final String rez = "rezensionen";
    @Override
    public void init() throws ServletException {
        super.init();
    }
    protected ArrayList<Rezension> getPersistentRezension(){
        ArrayList<Rezension> rezensionen;
        rezensionen = (ArrayList<Rezension>) getApplication().getAttribute(rez);
        if(rezensionen == null){
            getApplication().setAttribute(rez, new ArrayList<>());
            rezensionen = (ArrayList<Rezension>) getApplication().getAttribute(rez);
        }
        return rezensionen;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {//print parking house

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        request.setAttribute("gesamtPunkte", String.format("%.1f",getPersistentRezension().stream().mapToDouble(x -> x.getPunkte()).average().orElse(0.0)));
        if(getPersistentRezension() != null){
            request.setAttribute("bewertungen",getPersistentRezension());
            request.setAttribute("gesamt",getPersistentRezension().size());
        }
        out.println("</body></html>");
        request.getRequestDispatcher("rezensionen.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String body = ParkhausServlet.getBody( request );
        String[] params = body.split(",");
        String event = params[0];
        String[] splitEvent = event.split("&");
        String mergeString = "";
        String message;

        if((splitEvent[0]+"&"+splitEvent[1]+"&"+splitEvent[2]).equals(event)) {

            if((!(splitEvent[2].substring(10).equals("")) && (splitEvent[1].charAt(splitEvent[1].length() - 1) != '='))){

                String[] filterPlus = splitEvent[2].substring(10).split("\\+");
                message = "Danke für Ihr Feedback! :)";

                for(int i = 0; i < filterPlus.length; i++){

                    mergeString += filterPlus[i] + " ";
                }
                getPersistentRezension().add(new Rezension(Integer.parseInt(String.valueOf(splitEvent[1].charAt(splitEvent[1].length()-1))),mergeString,splitEvent[0].substring(9)));
                request.setAttribute("message",message);
            }else{
                message = "Punkte und Bewertung müssen angeben werden";
                request.setAttribute("message",message);
            }
            request.getRequestDispatcher("formularRezension.jsp").forward(request, response);
        }
    }
}
