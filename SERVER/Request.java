import java.io.*;
import java.net.Socket;

public class Request {

    private static final int CREATE_ACCOUNT = 0;
    private static final int GET_ACCOUNT = 1;
    private static final int DELETE_ACCOUNT = 2;

    private Socket serverConnection;
    private ObjectOutputStream requestWriter;
    private ObjectInputStream requestReader;

    // POST object for sending an account to store to server
    public Request() {

        try {

            serverConnection = new Socket("localhost", 9087);

            requestWriter = new ObjectOutputStream(serverConnection.getOutputStream());

            requestReader = new ObjectInputStream(serverConnection.getInputStream());

        } catch (IOException e) {
            System.out.println("Connection error occurred while getting from server...");
        }

    }

    public boolean createAccount(Account account) {

        try {

            requestWriter.writeObject(String.valueOf(CREATE_ACCOUNT));

            requestWriter.writeObject(account);

            String response = (String) requestReader.readObject();

            requestReader.close();
            requestWriter.close();

            if (response.equalsIgnoreCase("success")) {
                return true;
            }

            return false;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Account getAccount(String email) {

        try {

            requestWriter.writeObject(String.valueOf(GET_ACCOUNT));

            requestWriter.writeObject(email);

            Account retrievedAccount = (Account) requestReader.readObject();

            requestWriter.close();
            requestReader.close();

            return retrievedAccount;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {

        Account keegaAccount = new Request().getAccount("nageekr@gmail.com");

        System.out.println(keegaAccount.getEmail());

    }

}
