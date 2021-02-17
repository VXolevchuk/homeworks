package ua.kiev.prog;

import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegistrationServlet extends HttpServlet{
          private UsersMap users = UsersMap.getInstance();

          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
              byte[] buf = requestBodyToArray(req);
              String bufStr = new String(buf, StandardCharsets.UTF_8);
              ClientAccount client = ClientAccount.fromJSON(bufStr);

              if(client != null) {
                  if(!users.containsK(client.getLogin())) {
                      users.add(client.getLogin(), client.getPassword());

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

        return bos.toByteArray();
    }
}
