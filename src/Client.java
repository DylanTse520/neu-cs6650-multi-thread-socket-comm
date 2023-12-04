// Client.java: A simple client program.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Reading data using readLine
        String input = reader.readLine();
        // Splitting the input line to get the port number
        String[] parts = input.split(" ");
        // The second part should be the host
        String host = parts[1];
        // The third part should be the port number
        String port = parts[2];
        // Open your connection to a server at host and port
        Socket client = new Socket(host, Integer.parseInt(port));
        // Get the output writer associated with the socket
        PrintWriter pr = new PrintWriter(client.getOutputStream());
        // Get the buffered reader
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

        while (!"exit".equalsIgnoreCase(input)) {
            // Reading data from client
            input = reader.readLine();
            // Send data to server
            pr.println(input);
            pr.flush();
            // Get the result from server
            System.out.println("Server replied: " + br.readLine());
        }
        // Close the reader
        reader.close();
    }
}