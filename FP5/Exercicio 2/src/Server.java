import java.io.IOException;
import java.net.*;

public class Server {
    private static DatagramSocket multicastSocket = null;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        multicastSocket = new DatagramSocket(4445);
        int port = 2048;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port + ".");
            System.exit(-1);
        }

        while (listening) {
            WorkerThread wt = new WorkerThread(serverSocket.accept());
            wt.start();
        }
        serverSocket.close();
    }

    public static void sendMessage(String text) {
        //build the message
        byte[] buf = new byte[256];
        String message = text;
        buf = message.getBytes();

        InetAddress group = null;

        //assign group for clients to connect and later bind multicast sockets
        try {
            group = InetAddress.getByName("230.0.0.1");
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }

        //send the package with the message in buf
        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length, group, 4446);

        try {
            multicastSocket.send(packet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
