package ClientSocket;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClientTCPSocketTests {

    @Test
    public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorret() {
        ClientTCPSocket client = new ClientTCPSocket();
        client.startConnection("127.0.0.1", 5555);
        String response = client.sendMessage("hello server");
        assertEquals("hello client", response);
    }
}
