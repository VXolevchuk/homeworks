package ua.kiev.prog.chatrooms;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EnterChatRoomServlet extends HttpServlet {
    private ChatRoomsList list = ChatRoomsList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        ChatRoom cr = list.getRoom(name);
        if (cr != null) {
            list.getRoom(name).addParticipant(login);
        }
        else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }




}
