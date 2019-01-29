import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiClientServerTCP {

    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                InetAddress ip = clientSocket.getInetAddress();
                int cp = clientSocket.getPort();
                System.out.println("Connection Established with " + ip + ":" + cp);
                System.out.println("Waiting for query...");

                String inputLine, outputLine;

                while((inputLine = in.readLine()) != null) {
                    System.out.println("Requested location is " + inputLine + ". Generating answer...");
                    outputLine = URLReader.reader(inputLine);
                    System.out.println("Sending answer to" + ip + ":" + cp);
                    out.println(outputLine);
                    System.out.println("Answer sent to " + ip + ":" + cp);

                    if(".".equals(inputLine)) {
                        out.println("Goodbye");
                        break;
                    }
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();

            }catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
