package Network;


import java.io.*;
import java.net.*;


public class Client extends Thread {
    private final String name;
    private int serverPort;
    private InetAddress ipAddress;
    private Socket socket;
    private boolean isServerOn = false;

    private Client client = this;

    private BufferedReader in;
    private PrintWriter out;

    public Client(String name) {
        this.name = name;
    }


    public void connectToServer(String address, int serverPort) {
        System.out.println("Connecting to server...");
        createSocket(address, serverPort);
        createStream();
        sendInfo();
        System.out.println("Connected");
        startClient();
    }

    private void sendInfo() {
        write(name);
    }

    private void write(String text) {
        out.println(text);
        out.flush();
    }

    private void startClient() {
        client.start();
    }

    private void createSocket(String address, int port) {
        try {
            ipAddress = InetAddress.getByName(address);
            serverPort = port;
            socket = new Socket(ipAddress, serverPort);
            isServerOn = true;
        } catch (IOException e) {
            System.out.println("Server not responding");
            System.exit(1);
        }
    }

    private void createStream() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (isServerOn) {
            try {
                Thread.sleep(1000);
                write("Hello from " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
