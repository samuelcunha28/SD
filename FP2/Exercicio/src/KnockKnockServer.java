import java.io.*;
import java.net.*;

public class KnockKnockServer {
    public static void main(String[] args) throws IOException {

        int portNumber = 7;

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        PrintWriter out = null;

        try {
            serverSocket = new ServerSocket(portNumber);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine, outputLine;
        KnockKnockProtocol knock = new KnockKnockProtocol();
        outputLine = knock.processInput(null);
        out.println(outputLine);
        System.out.println("Server: " + outputLine);

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Client: " + inputLine);
            outputLine = knock.processInput(inputLine);
            System.out.println("Server: " + outputLine);
            out.println(outputLine);
            if (outputLine.equals("Bye."))
                break;
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}