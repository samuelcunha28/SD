import java.net.*;
import java.io.*;

public class KnockKnockServer {
    public static void main (String[] args) throws IOException{
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        PrintWriter out = null;
        KnockKnockProtocol knock = new KnockKnockProtocol();
        String knockResponse = "";
        BufferedReader in = null;

        try {
            serverSocket = new ServerSocket(7);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen to port or listening for a connection");
            System.exit(-1);
        }

        try{
            //Iniciar processamento de pedidos
            clientSocket = serverSocket.accept();
            System.out.println("Chegou um cliente! IP Cliente: " + clientSocket.getInetAddress());
        } catch (IOException e){
            System.out.println("Accept failed.");
            System.exit(-1);
        }

        try{
            //Para se obter um objeto do tipo PrintWriter
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            //Para se obter um objeto do tipo BufferedReader
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error");
        }

        //Socket input
        String serverOut = null;
        String clientIn = null;

        serverOut = knock.processInput(null);
        out.println(serverOut);

        //Respostas do cliente
        try{
            while((clientIn = in.readLine()) != null){
                System.out.println("Client: " + clientIn);
                serverOut = knock.processInput((clientIn));
                System.out.println("Server: " + serverOut);
                out.println(serverOut);

                if(serverOut.equals("Bye.")){
                    break;
                }
            }
        }catch(IOException e) {
            System.out.println("Error");
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}


/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class KnockKnockServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            serverSocket = new ServerSocket(7);
        } catch (IOException e) {
            System.out.println("Could not listen on port 7");
            System.exit(1);
        }

        Socket clientSocket = null;
        KnockKnockProtocol knockKnockProtocol = new KnockKnockProtocol();

        try {
            clientSocket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Accept failed");
            System.exit(1);
        }

        try {
            String messageSent = knockKnockProtocol.processInput("");
            out.println(messageSent);
            String messageReceived = null;
            while ((messageReceived = in.readLine()) != null) {
                System.out.println("Mensagem do cliente: " + messageReceived);
                messageSent = knockKnockProtocol.processInput(messageReceived);
                System.out.println("Mensagem enviada: " + messageSent);
                out.println(messageSent);
                if (messageReceived.equals("n")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 */

/*
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
 */