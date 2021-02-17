package ua.kiev.prog.users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class LoginServlet extends HttpServlet {
        private UsersMap users = UsersMap.getInstance();
        private ActiveUsersMap act = ActiveUsersMap.getInstance();

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            byte[] buf = requestBodyToArray(req);
            String bufStr = new String(buf, StandardCharsets.UTF_8);
            ClientAccount client = ClientAccount.fromJSON(bufStr);

            String pass;

            if(client != null) {
                pass = users.get(client.getLogin());
                if(pass.equals(client.getPassword())) {
                    act.setStatus(client.getLogin(), true);
                    resp.setStatus(HttpServletResponse.SC_OK);
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }

            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
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
