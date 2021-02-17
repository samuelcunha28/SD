import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWriterThread extends Thread {
    private Socket socket;
    PrintWriter out = null;

    public ClientWriterThread() {
        super("ThreadGetSend");

        try {
            //connect the socket to localhost server
            this.socket = new Socket("127.0.0.1", 2048);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        super.run();

        try {
            //get the user input to reader
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            while ((userInput = stdIn.readLine()) != null) {
                //send the user input to socket
                out.println(userInput);

                if (userInput.equals("Bye")) {
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}