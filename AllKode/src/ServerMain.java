import ServerSocket.MultiClientServerTCPSocket;

public class ServerMain {

    public static void main(String[] args) {

        int port = 5555;
        System.out.println("Starting server! Ready for connection!");

        MultiClientServerTCPSocket test = new MultiClientServerTCPSocket();
        test.start(port);

    }
}
