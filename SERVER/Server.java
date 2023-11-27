import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{

    public static final int SERVER_PORT = 9087;

    public static final String ACCOUNT_HASH_MAP = new File("accountHashMapData").getAbsolutePath();

    private ServerSocket server;

    public Server() {

        try {
            server = new ServerSocket(SERVER_PORT);
            while (true) connectToClients();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void connectToClients() throws IOException {

        Socket clientSocket = server.accept();

        if (clientSocket.isConnected()) {
            new Thread(() -> {
                System.out.println("Client connected");
                ConnectedClient connectedClient = new ConnectedClient(clientSocket);
                connectedClient.execute();
                connectedClient.close();
            }).start();
        }

    }


    public static void main(String[] args) {

        new Server();

    }

}
