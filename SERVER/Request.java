import java.io.*;
import java.net.Socket;

public class Request {

    private static final int CREATE_ACCOUNT = 0;
    private static final int GET_ACCOUNT = 1;
    private static final int UPDATE_PASSWORD = 2;
    private static final int DELETE_ACCOUNT = 3;

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

    // createAccount
    // getAccount
    // updateAccountPassword
    // deleteAccount
    // getSeller
    // getAllSellers
    // getCustomer
    // getAllCustomers
    // updateSeller
    // updateCustomer

    public boolean createAccount(Account account) {

        try {

            requestWriter.writeObject(String.valueOf(CREATE_ACCOUNT));

            requestWriter.writeObject(account);

            String response = (String) requestReader.readObject();

            requestReader.close();
            requestWriter.close();

            return response.equalsIgnoreCase("success");

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

    public boolean updateAccountPassword(String accountEmail, String newPassword) {

        try {

            requestWriter.writeObject(String.valueOf(UPDATE_PASSWORD));

            requestWriter.writeObject(accountEmail);

            requestWriter.writeObject(newPassword);

            Boolean response = (Boolean) requestReader.readObject();

            requestReader.close();
            requestWriter.close();

            return response;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean deleteAccount(String accountEmail) {

        try {

            requestWriter.writeObject(String.valueOf(DELETE_ACCOUNT));

            requestWriter.writeObject(accountEmail);

            Boolean response = (Boolean) requestReader.readObject();

            requestReader.close();
            requestWriter.close();

            return response;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    // Test account line: "Keegan", "nageekr@gmail.com", "AstroBoy@1", "Kyclon", "seller"

    public static void main(String[] args) {



    }

}
