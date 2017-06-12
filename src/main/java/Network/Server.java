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
        while (isServerOn) {
            System.out.println("Waiting for next client...");
            addSocketToList(getClientSocket());
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
            GameLogic.generatePlayerRoles(10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        totalConnections = sockets.size();
    }

    private class ClientProcessing extends Thread {

        private String name;
        private BufferedReader reader;
        private PrintWriter writer;

        ReaderThread readerThread = new ReaderThread();

        public ClientProcessing(String name, BufferedReader reader, PrintWriter writer) {
            this.name = name;
            this.reader = reader;
            this.writer = writer;
            readerThread.setDaemon(true);
            readerThread.start();
        }

        public void run() {
            while (true) {
                synchronized (this) {
                }
            }
        }

        private class ReaderThread extends Thread {
            public void run() {
                while (true) {
                }
            }
        }
    }
}
