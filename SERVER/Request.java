import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

public class Request {

    // Variables to be used when checking account creation
    public static final int ACCOUNT_CREATED = 0;
    public static final int EMAIL_TAKEN = 1;
    public static final int USERNAME_TAKEN = 2;

    // All available methods
    private static final int CREATE_ACCOUNT = 0;
    private static final int GET_ACCOUNT = 1;
    private static final int GET_ALL_ACCOUNTS = 2;
    private static final int UPDATE_PASSWORD = 3;
    private static final int DELETE_ACCOUNT = 4;
    private static final int GET_SELLER = 5;
    private static final int GET_ALL_SELLERS = 6;
    private static final int UPDATE_SELLER = 7;
    private static final int GET_CUSTOMER = 8;
    private static final int GET_ALL_CUSTOMERS = 9;
    private static final int UPDATE_CUSTOMER = 10;

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

    // Returns 0 if account successfully created
    // Returns 1 if email is already taken
    // Returns 2 if username is already taken
    // Returns 3 if an unexpected error occurred
    public int createAccount(Account account) {

        try {

            requestWriter.writeObject(String.valueOf(CREATE_ACCOUNT));

            requestWriter.writeObject(account);

            String response = (String) requestReader.readObject();

            requestReader.close();
            requestWriter.close();

            switch (response) {

                case "success" -> {
                    return 0;
                }

                case "email" -> {
                    return 1;
                }

                case "username" -> {
                    return 2;
                }

            }

            return 3;

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

    public ArrayList<Account> getAllAccounts() {

        try {

            requestWriter.writeObject(String.valueOf(GET_ALL_ACCOUNTS));

            ArrayList<Account> allAccounts = (ArrayList<Account>) requestReader.readObject();

            requestReader.close();
            requestWriter.close();

            return allAccounts;

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

    public Seller getSeller(String sellerEmail) {

        try {

            requestWriter.writeObject(String.valueOf(GET_SELLER));

            requestWriter.writeObject(sellerEmail);

            Seller retrievedSeller =  (Seller) requestReader.readObject();

            requestWriter.close();
            requestReader.close();

            return retrievedSeller;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Seller> getAllSellers() {

        try {

            requestWriter.writeObject(String.valueOf(GET_ALL_SELLERS));

            ArrayList<Seller> retrievedSellers = (ArrayList<Seller>) requestReader.readObject();

            requestReader.close();
            requestWriter.close();

            return retrievedSellers;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean updateSeller(Seller seller) {

        try {

            requestWriter.writeObject(String.valueOf(UPDATE_SELLER));

            requestWriter.writeObject(seller);

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

//        ArrayList<Account> allAccounts = new Request().getAllAccounts();
//
//        for (Account account : allAccounts) {
//
//            new Request().deleteAccount(account.getEmail());
//
//        }




    }

}
