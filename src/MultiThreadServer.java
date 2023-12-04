// MultiThreadServer.java: a multi thread server that can build connection with more than one client at the same time

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

    public static void main(String[] args) {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ServerSocket server = null;
        try {
            // Reading data using readLine
            String input = reader.readLine();
            // Splitting the input line to get the port number
            String[] parts = input.split(" ");
            // The second part should be the port number
            String port = parts[1];
            // Register service on port
            server = new ServerSocket(Integer.parseInt(port));
            server.setReuseAddress(true);
            while (!server.isClosed()) {
                // Wait and accept a connection
                Socket client = server.accept();
                // Print client connection
                System.out.println("New client connected: "
                        + client.getInetAddress()
                        .getHostAddress());
                // Create a new client handler
                ClientHandler clientSock
                        = new ClientHandler(client);
                // Create a new thread to handle the client
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // The class that implements runnable to open new thread and handle new clients
    private static class ClientHandler implements Runnable {
        private final Socket client;

        public ClientHandler(Socket socket) {
            this.client = socket;
        }

        public void run() {
            // Get the buffered reader
            BufferedReader br = null;
            // Get the output writer associated with the socket
            PrintWriter pr = null;

            try {
                br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                pr = new PrintWriter(client.getOutputStream());

                String line;
                while ((line = br.readLine()) != null) {
                    // Print client message
                    System.out.printf("Client sent: %s\n", line);
                    // Send data to client
                    pr.printf("Thread %d received: %s\n", Thread.currentThread().getId(), line);
                    pr.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Close everything
            } finally {
                try {
                    if (pr != null) {
                        pr.close();
                    }
                    if (br != null) {
                        br.close();
                        client.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}