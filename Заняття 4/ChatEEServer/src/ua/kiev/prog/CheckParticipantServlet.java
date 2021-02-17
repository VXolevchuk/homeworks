package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckParticipantServlet extends HttpServlet {
    private  ChatRoomsList list = ChatRoomsList.getInstance();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String name = req.getParameter("name");

        ChatRoom cr = list.getRoom(name);
        if (cr != null && cr.isParticipant(login)) {
            resp.setStatus(HttpServletResponse.SC_OK);

        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
