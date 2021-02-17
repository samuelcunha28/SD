import java.io.IOException;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) {
        Socket socket = null;
        String hostName = "localhost";
        int portNumber = 2048;

        try {
            socket = new Socket(hostName, portNumber);
            ThreadGetSend threadGetSend = new ThreadGetSend(socket);
            threadGetSend.start();
            ThreadReceivePrint threadReceivePrint = new ThreadReceivePrint(socket);
            threadReceivePrint.start();
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
