public class Main {

    public static void main(String[] args) {

        int port = 5555;
        System.out.println("Starting server! Ready for connection!");

        MultiClientServerTCP test = new MultiClientServerTCP();
        test.start(port);
    }
}
