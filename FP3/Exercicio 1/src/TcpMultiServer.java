import java.io.IOException;
import java.net.ServerSocket;

public class TcpMultiServer {
    public static void main(String[] args) throws IOException {
        int portNumber = 7;

        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new WorkerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}