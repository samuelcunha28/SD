import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProcessClient extends Thread {

    public static ArrayList<String> messages = new ArrayList<>();

    private Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    public ProcessClient(Socket socket) {
        super("ProcessClient");
        this.socket = socket;
    }

    public void run() {
        try {
            //out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                LocalDateTime now = LocalDateTime.now();
                String text = "" + dtf.format(now) + " " + socket.getLocalAddress().toString() + ":" + inputLine;
                messages.add(text);
                Thread.sleep(1000);
                sendMessage();
            }

            out.close();
            in.close();
            socket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage () throws IOException, InterruptedException {
        for (Socket worker: WorkerThread.Clientes){
            PrintWriter out = new PrintWriter(worker.getOutputStream(), true);
            for(int i = 0; i < messages.size(); i++){
                System.out.println(messages.get(i));
                out.println(messages.get(i));
            }
            Thread.sleep(1000);
        }
    }

}