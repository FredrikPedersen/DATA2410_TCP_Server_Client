import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiClientServerTCP {

    private ServerSocket serverSocket;


    /**
     * Starter en serversocket som lytter etter klienter som ønsker å koble til.
     * Dersom en klient kobler seg til aksepterer den klienten og starter en ny ClientHandler på en ny tråd.
     * @param port porten det skal lyttes på
     */
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop(); //kalles kun dersom en IOException forekommer
        }
    }

    /**
     * Metode for å stoppe serveren.
     */
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
                out = new PrintWriter(clientSocket.getOutputStream(), true); //printWriter for å skrive meldinger til klienten
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //bufferedReader til å lese meldinger fra klienten
                InetAddress ip = clientSocket.getInetAddress();
                int cp = clientSocket.getPort();
                System.out.println("Connection Established with " + ip + ":" + cp);
                System.out.println("Waiting for query...");

                String inputLine, outputLine;

                while((inputLine = in.readLine()) != null) {
                    System.out.println("Requested location is " + inputLine + ". Generating answer...");
                    outputLine = URLReader.reader(inputLine); //kaller URL-leseren med klientens input
                    System.out.println("Sending answer to " + ip + ":" + cp);
                    out.println(outputLine); //sender returverdien fra URLreader til klienten
                    System.out.println("Answer sent to " + ip + ":" + cp);
                }

                in.close();
                out.close();
                clientSocket.close(); //Stenger leseren, skriveren og socketen når forespørselen er utført

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
