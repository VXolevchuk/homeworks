package ua.kiev.prog.chatrooms;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CreateChatRoomServlet extends HttpServlet {
    private ChatRoomsList list = ChatRoomsList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        ArrayList<String> arr = ChatRoomsList.getChatRooms();
        ChatRoom room = ChatRoom.fromJSON(bufStr);
        if (room != null) {
            if (!arr.contains(room.getName())) {
                room.addParticipant(room.getOwner());
                list.add(room);
            }
            else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }
        else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);
        is.close();

        return bos.toByteArray();
    }

}
