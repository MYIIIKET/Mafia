package Network;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Network {
    private static int serverPort;
    private static InetAddress ipAddress;


    public Client() {
    }

    public void connectToServer(String address, int serverPort) throws IOException {
        this.ipAddress = InetAddress.getByName(address);
        this.serverPort = serverPort;
        createSocket();
        System.out.println("Connected to server");
        createIOStream();
    }

    protected void createSocket() throws IOException {
        socket = new Socket(ipAddress, serverPort);
    }

}
