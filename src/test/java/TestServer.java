import Network.Network;
import Network.Server;

import java.io.IOException;

public class TestServer {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer();
    }
}
