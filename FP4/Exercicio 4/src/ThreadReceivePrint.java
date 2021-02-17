import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadReceivePrint extends Thread {
    private Socket socket = null;
    BufferedReader in = null;

    public ThreadReceivePrint(Socket socket) {
        super("ThreadReceivePrint");
        this.socket = socket;
    }

    public void run() {
        super.run();
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String fromServer = null;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Message: " + fromServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}