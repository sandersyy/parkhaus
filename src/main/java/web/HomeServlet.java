package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/index")
public class HomeServlet extends ParkhausServlet {


    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { //print parking house
        getServletContext().setAttribute("time", convertDateToString(getPersistentParkhaus().getActualTime()));
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
