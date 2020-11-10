import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

public class ChatServer {
    private static LinkedList<WorkerThread> chat = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int port=2048;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+port+".");
            System.exit(-1);
        }

        while (listening) {
            WorkerThread wt = new WorkerThread(serverSocket.accept());
            wt.start();
        }
        serverSocket.close();
    }
}