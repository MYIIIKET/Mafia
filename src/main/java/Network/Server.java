package Network;


import GameLogic.GameLogic;
import Players.ConcretePlayers.ConcreteCivilianPlayers.Citizen;
import com.sun.xml.internal.txw2.output.DataWriter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class Server extends Thread {
    private static final int port = 8080;
    private static ServerSocket serverSocket;
    private static boolean isServerOn = false;

    private static Map<Integer, Socket> sockets = new TreeMap<>();
    private static Map<Integer, BufferedReader> bufferedReaderMap = new TreeMap<>();
    private static Map<Integer, PrintWriter> printWriterMap = new TreeMap<>();
    private static Map<Integer, Thread> clientThreadsMap = new TreeMap<>();

    private static int totalConnections = sockets.size();


    private static Server server = new Server();

    private Server() {
    }

    public static int getTotalConnections() {
        return totalConnections;
    }

    public static void startServer() throws IOException {
        serverSocket = new ServerSocket(port);
        isServerOn = true;
        server.start();
    }

    public static void stopServer() {
        isServerOn = false;
    }

    public void run() {
        ClientWaiter clientWaiter = new ClientWaiter();
        clientWaiter.setDaemon(true);
        addSocketToList(getClientSocket());
        clientWaiter.start();
        while (isServerOn) {
            System.out.println("Waiting for next client...");
            if (clientWaiter.isAlive()) {
                addSocketToList(getClientSocket());
            } else {
                System.out.println("Time is out");
                break;
            }
        }
    }

    private Socket getClientSocket() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    private void addSocketToList(Socket socket) {
        String name;
        int hash;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            name = in.readLine();
            hash = name.hashCode();
            sockets.put(hash, socket);
            bufferedReaderMap.put(hash, new BufferedReader(new InputStreamReader(socket.getInputStream())));
            printWriterMap.put(hash, new PrintWriter(new OutputStreamWriter(socket.getOutputStream())));
            ClientProcessing clientProcessing = new ClientProcessing(name,
                    bufferedReaderMap.get(hash),
                    printWriterMap.get(hash));
            clientProcessing.setDaemon(true);
            clientThreadsMap.put(hash, clientProcessing);
            clientProcessing.start();
            System.out.println("Client: " + name + " " + sockets.get(hash) + " connected");
            sendInfo(name + " connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
        totalConnections = sockets.size();
    }

    private void sendInfo(String text) {
        for (Map.Entry<Integer, PrintWriter> entry : printWriterMap.entrySet()) {
            entry.getValue().println(text);
        }
    }


}
