import java.io.IOException;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        Socket socket = null;
        String hostName = "localhost";
        int portNumber = 2048;

        try{
            socket = new Socket(hostName, portNumber);
            ThreadGetSend tgs = new ThreadGetSend(socket);
            tgs.start();
            ThreadReceivePrint trp = new ThreadReceivePrint(socket);
            trp.start();
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}