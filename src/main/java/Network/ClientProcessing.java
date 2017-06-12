package Network;


import java.io.BufferedReader;
import java.io.PrintWriter;

public class ClientProcessing extends Thread {

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