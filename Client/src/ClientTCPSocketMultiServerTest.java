import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTCPSocketMultiServerTest {

    @Test
    public void givenClient1_whenServerResponds_thenCorrect() {
        ClientTCPSocket client1 = new ClientTCPSocket();
        client1.startConnection("127.0.0.1", 5555);
        String msg1 = client1.sendMessage("hello");
        String msg2 = client1.sendMessage("world");
        String terminate = client1.sendMessage(".");

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "Goodbye");
    }

    @Test
    public void givenClient2_whenServerResponds_thenCorrect() {
        ClientTCPSocket client2 = new ClientTCPSocket();
        client2.startConnection("127.0.0.1", 5555);
        String msg1 = client2.sendMessage("hello");
        String msg2 = client2.sendMessage("world");
        String terminate = client2.sendMessage(".");

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "Goodbye");
    }


}
