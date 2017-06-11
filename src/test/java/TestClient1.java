import Network.Client;
import Network.Network;

import java.io.IOException;

public class TestClient1 {
    public static void main(String[] args) throws IOException {
        Client client = new Client("MYIIIKET");
        client.connectToServer("127.0.0.1", 8080);
    }
}
