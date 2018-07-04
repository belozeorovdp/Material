package nio_p2p;

import nio_p2p.server.Server;

public class Main {
    public static void main(String[] args) throws Exception {
        new Server(16161).run();
    }
}
