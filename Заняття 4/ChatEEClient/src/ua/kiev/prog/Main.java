package ua.kiev.prog;

import java.io.IOException;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int o;
		int k = 0;

		try {
			ClientAccount clientAccount = Authorizator.authorizate();
			do {
				System.out.println("Select wanted option");
				o = sc.nextInt();
				switch (o) {
					case 1:
						clientAccount.startChat(); break;
					case 2:
						clientAccount.sendPrivateMessage(); break;
					case 3:
						clientAccount.getUsers(); break;
					case 4:
						clientAccount.getStatus(); break;
					case 5:
						clientAccount.getRoomsList(); break;
					case 6:
						clientAccount.enterChatRoom(); break;
					case 7:
						clientAccount.chatInChatRoom(); break;
					case 8:
						clientAccount.createChatRoom(); break;
					case 9:
						clientAccount.exit(); k = 1; break;
					default:
						System.out.println("You entered missing number. Please, try again!");
				}
			} while (k != 1);

		} catch(IOException ex) {
			ex.printStackTrace();
		}

















	}
}
