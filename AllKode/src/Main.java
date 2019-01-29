import URLReading.URLReader;

public class Main {

    public static void main(String[] args) throws Exception {
        int port = 5555;
        System.out.println("Starting server! Ready for connection");

        try(
                ServerSocket serverSocket = new ServerSocket(port);
        )
        {
            while(true) {
                ConnectSocketThread connectSocketThread = new ConnectSocketThread(serverSocket.accept());
                //OR   new ConnectSocketThread(serverSocket.accept()).start();
                connectSocketThread.start();
            }
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
}
}