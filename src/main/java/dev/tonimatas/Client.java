package dev.tonimatas;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "0.0.0.0";
        int port = 25555;

        try (Socket socket = new Socket(serverAddress, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            Scanner scanner = new Scanner(System.in);
            String userInput;

            System.out.println("Write your message: ");
            
            while (true) {
                userInput = scanner.nextLine();
                writer.println(userInput);

                String response = reader.readLine();
                System.out.println("Sever response: " + response);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
