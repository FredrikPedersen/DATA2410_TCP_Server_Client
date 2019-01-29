import java.io.IOException;
import java.net.ServerSocket;


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

       /* try (
                ServerSocket serverSocket = new ServerSocket(port);
                Socket connectSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(connectSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));
        ) {
            InetAddress ip = connectSocket.getInetAddress();
            int cp = connectSocket.getPort();
            System.out.println("Connection Established with " + ip + ":" + cp);
            System.out.println("Waiting for query...");
            String input, output;

            while ((input = in.readLine()) != null) {
                System.out.println("Requested location is: " + input + ". Generating answer...");
                output = URLReader.reader(input);
                System.out.println("Sending answer...");
                out.println(output);
                System.out.println("Answer sent!");
            }

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }*/


    }

}