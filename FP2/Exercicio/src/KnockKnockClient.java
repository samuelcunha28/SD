import java.io.*;
import java.net.*;

public class KnockKnockClient {

    public static void main(String[] args) throws IOException {
        String hostName = "localhost";

        Socket KKSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            KKSocket = new Socket(hostName, 7);
            out = new PrintWriter(KKSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(KKSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromUser;
        String fromServer;

        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equals("Bye."))
                break;
            System.out.print("Client: ");
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                out.println(fromUser);
            }
        }

        out.close();
        in.close();
        stdIn.close();
        KKSocket.close();

    }
}
