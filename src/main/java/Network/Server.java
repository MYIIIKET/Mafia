package Network;


import java.io.*;
import java.net.ServerSocket;

public class Server extends Network {
    private static final int port = 8080;
    private static ServerSocket serverSocket;


    private static Server server;

    private Server() throws IOException {
        createSocket();
        System.out.println("Server created");
        waitForClient();
        System.out.println("Client connected");
        createIOStream();
        createDataIOStram();
    }

    public static Server startServer() throws IOException {
        if (server == null) {
            return server = new Server();
        }
        return server;
    }

    protected void createSocket() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    private static void waitForClient() throws IOException {
        socket = serverSocket.accept();
    }


}
