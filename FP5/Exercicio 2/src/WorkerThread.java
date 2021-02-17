import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkerThread extends Thread {
    private Socket socket = null;
    BufferedReader in = null;

    public WorkerThread(Socket socket) {
        super("WorkerThread");
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

            String message = null;

            //while message from client is not null
            while((message = in.readLine()) != null){
                System.out.println("Mensagem do Cliente: " + message);

                //add datetime to the message
                LocalDateTime now = LocalDateTime.now();
                String text = "" + dtf.format(now) + " " + socket.getLocalAddress().toString() + ":" + message;

                //send message for all clients
                Server.sendMessage(text);

                //if the client sends bye, the thread for this client closes
                if (message.equals("Bye")) {
                    break;
                }
            }

            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}