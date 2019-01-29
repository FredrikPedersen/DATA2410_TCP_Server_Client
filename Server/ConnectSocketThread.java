package Connection;

import URLReading.URLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class ConnectSocketThread extends Thread{

    private Socket connectSocket;

    public ConnectSocketThread(Socket socket) {
        this.connectSocket = socket;
    }

    @Override
    public void run() {
        try(
        PrintWriter out = new PrintWriter(connectSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));
        )
        {

            InetAddress ip = connectSocket.getInetAddress();
            int cp = connectSocket.getPort();
            System.out.println("Connection Established with " + ip + ":" + cp);
            System.out.println("Waiting for query...");
            String input, output;

            while ((input = in.readLine()) != null) {
                System.out.println("Requested location is: " + input + ". Generating answer...");
                output = URLReader.reader(input);
                System.out.println("Sending answer to" + ip + ":" + cp);
                out.println(output);
                System.out.println("Answer sent! to " + ip + ":" + cp);
            }

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }
}
