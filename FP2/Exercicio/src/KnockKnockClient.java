import java.io.*;
import java.net.*;

public class KnockKnockClient {
    public static void main (String[] args) throws IOException{
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            //Usado para estabelecer a ligação
            echoSocket = new Socket("localhost", 7);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput = null;

        //O que o cliente recebe

        String echo;

        while ((echo = in.readLine()) != null) {
            System.out.println("Server: " + echo);

            if(echo.equals("Bye.")){
                break;
            }

            userInput = stdIn.readLine();

            //System.out.println("echo: " + stdIn.readLine());
            //mensagem enviada do cliente para o servidor
            if(userInput != null)
                out.println(userInput);
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class KnockKnockClient {
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("localhost", 7);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Don't know about the host");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        String messageReceived;

        while ((messageReceived = in.readLine()) != null) {
            System.out.println("" + messageReceived);

            if (messageReceived.equals("Bye.")) {
                break;
            }
            if ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
            }
        }
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
 */

/*
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
 */
