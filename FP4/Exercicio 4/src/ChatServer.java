import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;

public class ChatServer {

    private static LinkedList<ProcessClient> chat = new LinkedList<>();
    private static ServerSocket serverSocket = null;
    protected static ArrayList<String> messages = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Runnable waitingConnections = () -> {
            int port = 2048;
            boolean listening = true;

            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.err.println("Cloud not listen on port: " + port + ".");
                System.exit(-1);
            }

            try {
                while (listening) {
                    ProcessClient wt = new ProcessClient(serverSocket.accept());
                    wt.start();
                    chat.add(wt);
                }
                serverSocket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        };
        new Thread(waitingConnections).start();

        Runnable sendingMessages = () -> {
            while (true) {
                if (!messages.isEmpty()) {
                    String text = messages.get(0);
                    for (ProcessClient aChat : chat) {
                        aChat.sendMessage(text);
                    }
                    messages.remove(text);
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        new Thread(sendingMessages).start();
    }
}


/*
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
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
 */