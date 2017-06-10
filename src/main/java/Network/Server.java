package Network;


import java.io.*;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Server extends Network {
    private static final int port = 8082;
    private ServerSocket serverSocket;
    private boolean isServerOn = false;

    Messenger messenger;

    List<SocketAddress> clientList = new ArrayList<SocketAddress>();

    public Server() throws IOException {
        createSocket();
        createMessenger();
        toggleServer();
    }

    private void toggleServer() {
        isServerOn = !isServerOn;
    }

    public void startServer() {
        thread = new Thread(this);
        thread.start();
    }

    public void stopServer() {
        toggleServer();
    }

    protected void createSocket() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    private void createMessenger() {
        messenger = new Messenger();
        Thread thread = new Thread(messenger);
        thread.setDaemon(true);
        thread.start();
    }

    private void waitForClient() throws IOException {
        socket = serverSocket.accept();
    }

    private void addClientToList() {
        clientList.add(socket.getRemoteSocketAddress());
    }

    public void run() {
        while (isServerOn) {
            try {
                System.out.println("Wait for client...");
                waitForClient();
                System.out.println("Client connected");
                createStream();
                addClientToList();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Messenger implements Runnable {

        private void sendMessageToClients() throws IOException {
            for (int i = 0; i < clientList.size(); i++) {
                dataOutputStream.writeUTF("Hello " + clientList.get(i));
            }
        }

        public void run() {
            while (true) {
                try {
                    sendMessageToClients();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
