public class main {
    public static void main(String[] args) {
        ClientTCPSocket client = new ClientTCPSocket();
        client.executeCommands("10.253.14.41",5555);
    }
}
