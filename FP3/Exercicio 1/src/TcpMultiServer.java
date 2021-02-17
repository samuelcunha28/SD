import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

public class TcpMultiServer {

    private static LinkedList<WorkerThread> chat = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        int port = 2048;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Cloud not listen on port: " + port + ".");
            System.exit(-1);
        }

        while (listening) {
            WorkerThread wt = new WorkerThread(serverSocket.accept());
            wt.start();
            chat.add(wt);
        }
        serverSocket.close();
    }

    public static void sendBroadcast(String text, WorkerThread excludeChat) {
        for (WorkerThread aChat : chat) {
            if (aChat != excludeChat) {
                aChat.sendMessage(text);
            }
        }
    }
}


/*
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
 */