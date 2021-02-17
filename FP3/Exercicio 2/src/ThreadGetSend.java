import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadGetSend extends Thread {
    private Socket socket = null;
    PrintWriter out = null;

    public ThreadGetSend(Socket socket) {
        super("ThreadGetSend");
        this.socket = socket;
    }

    public void run() {
        super.run();
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromUser;

            while ((fromUser = stdIn.readLine()) != null) {
                out.println(fromUser);
                System.out.println("Me: " + fromUser);

                if (fromUser.equals("Bye")) {
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
