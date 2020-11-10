import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class WorkerThread extends Thread{

    public static ArrayList<Socket> Clientes = new ArrayList<>();

    private Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    public WorkerThread(Socket socket) {
        super("WorkerThread");
        this.socket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

            while(socket.getInputStream() != null){
                Clientes.add(socket);
                out.println("Connected");
                ProcessClient pc = new ProcessClient(socket);
                pc.start();
                break;
            }

            /*
            out.close();
            in.close();
            socket.close();
            */
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}