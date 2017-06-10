import Network.Client;

import java.io.IOException;

public class TestClient {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connectToServer("127.0.0.1", 8080);
    }
}
