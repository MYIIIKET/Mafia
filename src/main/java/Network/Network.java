package Network;


import java.io.*;
import java.net.Socket;

public abstract class Network {

    protected static InputStream inputStream;
    protected static OutputStream outputStream;

    protected static DataInputStream dataInputStream;
    protected static DataOutputStream dataOutputStream;

    protected static Socket socket;

    protected abstract void createSocket() throws IOException;

    protected void createIOStream() throws IOException {
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    protected void createDataIOStram() {
        dataInputStream = new DataInputStream(inputStream);
        dataOutputStream = new DataOutputStream(outputStream);
    }

}
