package Network;


import java.io.*;
import java.net.Socket;

public abstract class Network implements Runnable {

    protected InputStream inputStream;
    protected OutputStream outputStream;

    protected DataInputStream dataInputStream;
    protected DataOutputStream dataOutputStream;

    protected Socket socket;

    protected Thread thread;

    protected abstract void createSocket() throws IOException;

    protected void createIOStream() throws IOException {
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    protected void createDataIOStream() {
        dataInputStream = new DataInputStream(inputStream);
        dataOutputStream = new DataOutputStream(outputStream);
    }

    protected void createStream() throws IOException {
        createIOStream();
        createDataIOStream();
    }

    protected void disconnect() {
        dataInputStream = null;
        inputStream = null;
        dataOutputStream = null;
        outputStream = null;
        socket = null;
    }


}
