package web;

import logic.Parkhaus;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class ParkhausServlet extends HttpServlet {
    final String p = "parkhaus";
    protected ServletContext getApplication(){
        return getServletConfig().getServletContext();
    }

    protected Parkhaus getPersistentParkhaus(){
        Parkhaus parkhaus;
        ServletContext application = getApplication();
        parkhaus = (Parkhaus) application.getAttribute(this.p);
        if(parkhaus == null){
            application.setAttribute(this.p, new Parkhaus(100));
            parkhaus = (Parkhaus) application.getAttribute(this.p);
        }
        return parkhaus;
    }


    protected static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
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
    public static String convertDateToString(Date date){ // konvertiert ein Date objekt in einen String in dem Format YYYY-MM-dd'T'HH:MM
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        return dateFormat.format(date);
    }

    public static Date convertStringToDate(String s){ // konvertiert den String der ein Datum angibt (wie in convertDateToString) in ein Date Objekt

        int year = Integer.parseInt(s.substring(0,4));
        int month = Integer.parseInt(s.substring(5,7));
        int day = Integer.parseInt(s.substring(8,10));
        int hour = Integer.parseInt(s.substring(11,13));
        int minute = Integer.parseInt(s.substring(16)); // 14 statt 16, wenn man das alte Format benutzt

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, --month, day, hour, minute);

        return calendar.getTime();
    }
    protected void resetParkhaus(){
        getApplication().setAttribute(p, new Parkhaus(100));
    }

}
