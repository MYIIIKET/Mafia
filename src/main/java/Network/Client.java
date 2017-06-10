package Network;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class Client extends Network {
    private int serverPort;
    private InetAddress ipAddress;


    public Client() {
    }

    public void connectToServer(String address, int serverPort) throws IOException {
        this.ipAddress = InetAddress.getByName(address);
        this.serverPort = serverPort;
        System.out.println("Connecting to server...");
        createSocket();
        System.out.println("Connected");
        createStream();
    }

    protected void createSocket() throws IOException {
        socket = new Socket(ipAddress, serverPort);
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (socket.isConnected()) {
            try {
                dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }
    }
}
