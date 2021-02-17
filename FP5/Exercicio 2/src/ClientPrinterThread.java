import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientPrinterThread extends Thread {
    private MulticastSocket socket;
    private DatagramPacket packet;

    public ClientPrinterThread() {
        super("ClientPrinterThread");

        try {
            // assign multicast socket to port
            this.socket = new MulticastSocket(4446);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        super.run();

        try {
            InetAddress group = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(group);

            while (true) {
                // get the packet with the incoming messages
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // print the incoming message
                String received = new String(packet.getData());
                System.out.println("" + received);

                // if the message return is "bye" client is closed
                if (received.contains("Bye")) {
                    break;
                }
            }
            socket.leaveGroup(group);
            socket.close();
        } catch (IOException e) {
            System.out.println("Sessão Encerrada");
        }
    }
}
