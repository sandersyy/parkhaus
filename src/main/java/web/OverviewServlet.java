package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@WebServlet(urlPatterns = "/overview")
public class OverviewServlet extends ParkhausServlet { //main servlet

    private final String aT = "actualTime";
    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("parkhausInformationen", getPersistentParkhaus().toString());
        getServletContext().setAttribute(aT, convertDateToString(getPersistentParkhaus().getActualTime()));
        request.getRequestDispatcher("overview.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String body = ParkhausServlet.getBody( request );
        String[] params = body.split(",");
        String event = params[0];


        System.out.println(request.getParameter("aktion"));
        request.setAttribute(aT, convertDateToString(getPersistentParkhaus().getActualTime()));


        if(("aktion=plus15Min").equals( event )) { //checks if event is plus 15 minutes
            request.setAttribute(aT, convertDateToString(getPersistentParkhaus().addMinutes(15, getPersistentParkhaus().getActualTime()))); //enter parking house function

        }
        else if(("aktion=plus30Min").equals( event )){ //checks if event is plus 30 minutes
            request.setAttribute(aT, convertDateToString(getPersistentParkhaus().addMinutes(30, getPersistentParkhaus().getActualTime())));

        }
        else if(("aktion=plus60Min").equals( event )){
            request.setAttribute(aT, convertDateToString(getPersistentParkhaus().addMinutes(60, getPersistentParkhaus().getActualTime())));
        }
        else if(("aktion=Reset").equals( event )){
            System.out.println(event);
            resetParkhaus();
            request.setAttribute(aT, convertDateToString(getPersistentParkhaus().getActualTime()));
        }
        else if(("aktion=timewarp").equals( event.substring(0, 15) )){
            Date timeInput = convertStringToDate(event.substring(26));
            if(getPersistentParkhaus().jumpTime(timeInput, getPersistentParkhaus().getActualTime())){
                getPersistentParkhaus().setActualTime(timeInput);
                request.setAttribute(aT, convertDateToString(timeInput));
            }
        }

        request.setAttribute("parkhausInformationen", getPersistentParkhaus().toString());
        request.getRequestDispatcher("overview.jsp").forward(request, response);


    }


}
