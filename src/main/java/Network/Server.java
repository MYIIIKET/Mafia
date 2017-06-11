package Network;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class Server extends Thread {
    private static final int port = 8080;
    private static ServerSocket serverSocket;
    private static boolean isServerOn = false;

    private static Map<Integer, Socket> sockets = new TreeMap<Integer, Socket>();
    private static Map<Integer, BufferedReader> bufferedReaderMap = new TreeMap<Integer, BufferedReader>();
    private static Map<Integer, PrintWriter> printWriterMap = new TreeMap<Integer, PrintWriter>();
    private static Map<Integer, Thread> clientThreadsMap = new TreeMap<Integer, Thread>();

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        totalConnections = sockets.size();
    }

    private class ClientProcessing extends Thread {

        private String name;
        private BufferedReader reader;
        private PrintWriter writer;

        private String receivedMessage = null;
        private String sendMessage = "Received";

        public ClientProcessing(String name, BufferedReader reader, PrintWriter writer) {
            this.name = name;
            this.reader = reader;
            this.writer = writer;
        }

        public void run() {
            while (true) {
                try {
                    System.out.println(reader.readLine());
                } catch (IOException e) {
                    System.exit(1);
                }
            }
        }

        private class Reader extends Thread {
            public void run() {
                try {
                    receivedMessage = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void write(String text) {
            writer.println(text);
            writer.flush();
        }
    }
}
