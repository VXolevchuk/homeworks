package ua.kiev.prog;

import java.io.IOException;
import java.util.Scanner;

public class Authorizator {

    public Authorizator() {
    }

    public static ClientAccount authorizate() throws IOException {
        Scanner sc = new Scanner(System.in);
        ClientAccount client = new ClientAccount();
        System.out.println("Press 1 to registrate or 2 to log in!");
        int i = sc.nextInt();
        if (i == 1) {
            int a = 0;
            do {
                String login;
                String password;
                System.out.println("Enter your login:");
                login = sc.next();
                System.out.println("Enter your password:");
                password = sc.next();
                ClientAccount client1 = new ClientAccount(login, password);
                client = client1;
                a = client.createAccount();
                if (a == 200) { System.out.println("Registration complete!"); }
            } while (a != 200);
             client.login();
        }
        if (i == 2) {
            int b = 0;
            do {
                String login;
                String password;
                System.out.println("Enter your login:");
                login = sc.next();
                System.out.println("Enter your password:");
                password = sc.next();
                ClientAccount client1 = new ClientAccount(login, password);
                client = client1;
                b = client.login();
                System.out.println(b);
                if (b == 200) { System.out.println(); }
            } while (b != 200);
        } else {
            System.out.println("You entered wrong number!");
        }
        return client;
    }

}
