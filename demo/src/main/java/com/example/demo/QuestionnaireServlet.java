package com.example.demo;

import java.io.IOException;
import javax.jws.Oneway;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;


@WebServlet(name = "Questionnaire", urlPatterns = {"/questionnaire-servlet"})
public class QuestionnaireServlet extends HttpServlet {
    private static HashMap<String, Integer> map = new HashMap<>();

    public void init() {
        map.put("good", 0);
        map.put("bad", 0);
        map.put("dark", 0);
        map.put("green", 0);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/html");

        String mood = req.getParameter("mood");
        String tea = req.getParameter("tea");

        map.put(mood, map.get(mood) + 1);
        map.put(tea, map.get(tea) + 1);

        PrintWriter pw = resp.getWriter();

        pw.println("<html><body>");
        pw.println("<h3> Interviewed was in good mood: " + map.get("good") + " times." +
                "<br> " + "Interviewed was in bad mood: " + map.get("bad") + " times." +
                "<br> " + "Interviewed preferred dark tea: " + map.get("dark") + " times." +
                "<br> " + "Interviewed preferred green tea: " + map.get("green") + " times. </h3");
        pw.println("<h2> Want to answer one more time? </h2>" +
                " <a href=\"questionnaire.jsp\">Questionnaire</a> ");
        pw.println("</body></html>");

    }
    public void destroy(){

    }


}
