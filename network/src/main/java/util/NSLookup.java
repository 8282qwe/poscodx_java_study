package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String text = "";
            while (true) {
                System.out.print("> ");
                if ((text = scanner.nextLine()).equalsIgnoreCase("exit")) break;
                InetAddress[] inetAddresses = InetAddress.getAllByName(text);

                for (InetAddress inetAddress : inetAddresses) {
                    System.out.printf("%s : %s%n", inetAddress.getHostName(), inetAddress.getHostAddress());
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("error: " + e);
        }
    }
}
