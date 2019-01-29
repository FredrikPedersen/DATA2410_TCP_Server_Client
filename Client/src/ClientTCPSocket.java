import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCPSocket {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void executeCommands(String IP, int port){
        startConnection(IP, port);
        System.out.println("Please tell us where you want to know what time it is");
        Scanner userInput = new Scanner(System.in);
        String searchForLocation = userInput.nextLine();
        System.out.println("\nFetched time from server is:\n\n" + sendMessage(searchForLocation));
        stopConnection();
    }

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg) {
        try {
            out.println(msg);

            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stopConnection(){
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
