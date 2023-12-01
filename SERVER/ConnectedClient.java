import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectedClient {

    private final Socket connection;
    private ObjectOutputStream requestWriter;
    private ObjectInputStream requestReader;

    private static final int CREATE_ACCOUNT = 0;
    private static final int GET_ACCOUNT = 1;
    private static final int UPDATE_PASSWORD = 2;
    private static final int DELETE_ACCOUNT = 3;

    public ConnectedClient(Socket clientConnection) {

        connection = clientConnection;

    }

    public synchronized void execute() {

        try {

             requestReader = new ObjectInputStream(connection.getInputStream());
             requestWriter = new ObjectOutputStream(connection.getOutputStream());

             // Stores request sent by request object
             int request = Integer.parseInt(requestReader.readObject().toString());

             switch (request) {

                case CREATE_ACCOUNT -> {

                    serverCreateAccount();

                }

                case GET_ACCOUNT -> {

                    serverGetAccount();

                }

                case UPDATE_PASSWORD -> {

                    serverUpdateAccountPassword();

                }

                 case DELETE_ACCOUNT -> {

                    serverDeleteAccount();

                 }

            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void serverDeleteAccount() throws IOException, ClassNotFoundException {

       HashMap<String, Account> accountHashMap = getAccountHashMap();

       String accountEmail = (String) requestReader.readObject();

       Account removedAccount = accountHashMap.remove(accountEmail);

       updateAccountHashMap(accountHashMap);

       if (removedAccount != null) {

           String accountRole = removedAccount.getRole().toLowerCase();

           switch (accountRole) {

               case "seller" -> {

                   HashMap<String, Seller> sellerHashMap = getSellerHashMap();

                   sellerHashMap.remove(accountEmail);

                   updateSellerHashMap(sellerHashMap);

               }

               case "customer" -> {

                   HashMap<String, Customer> customerHashMap = getCustomerHashMap();

                   customerHashMap.remove(accountEmail);

                   updateCustomerHashMap(customerHashMap);

               }

           }

           requestWriter.writeObject(Boolean.TRUE);

       } else {

           requestWriter.writeObject(Boolean.FALSE);

       }

       requestWriter.close();
       requestReader.close();

    }

    private void serverUpdateAccountPassword() throws IOException, ClassNotFoundException {

        HashMap<String, Account> accountHashMap = getAccountHashMap();

        String accountEmail = (String) requestReader.readObject();

        String newPassword = (String) requestReader.readObject();

        if (accountHashMap.containsKey(accountEmail)) {

            Account retrievedAccount = accountHashMap.get(accountEmail);
            retrievedAccount.setPassword(newPassword);
            accountHashMap.replace(accountEmail, retrievedAccount);
            updateAccountHashMap(accountHashMap);

            requestWriter.writeObject(Boolean.TRUE);

        } else {

            requestWriter.writeObject(Boolean.FALSE);

        }

        requestWriter.close();
        requestReader.close();

    }

    private void serverGetAccount() throws IOException, ClassNotFoundException {

        HashMap<String, Account> accountHashMap = getAccountHashMap();

        String accountEmail = (String) requestReader.readObject();

        requestWriter.writeObject(accountHashMap.getOrDefault(accountEmail, null));

        requestWriter.close();
        requestReader.close();

    }

    private void serverCreateAccount() throws IOException, ClassNotFoundException {

       HashMap<String, Account> accountHashMap = getAccountHashMap();

       Account accountToCreate = (Account) requestReader.readObject();

       String accountEmail = accountToCreate.getEmail();

       String accountRole = accountToCreate.getRole().toLowerCase();

       // Sends failure message if map already has an account with same email
       if (accountHashMap.containsKey(accountEmail)) {

           requestWriter.writeObject("failure");

       } else {

           accountHashMap.put(accountEmail, accountToCreate);
           updateAccountHashMap(accountHashMap);

           switch (accountRole) {

               case "seller" -> {

                   HashMap<String, Seller> sellerHashMap = getSellerHashMap();

                   sellerHashMap.put(accountEmail, new Seller(accountToCreate, new ArrayList<>()));

                   updateSellerHashMap(sellerHashMap);

               }

               case "customer" -> {

                   HashMap<String, Customer> customerHashMap = getCustomerHashMap();

                   customerHashMap.put(accountEmail, new Customer(accountToCreate, new ArrayList<>(), new ShoppingCart()));

                   updateCustomerHashMap(customerHashMap);

               }

           }

           requestWriter.writeObject("success");

       }

       requestWriter.close();
       requestReader.close();

    }

    private static HashMap<String, Customer> getCustomerHashMap() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Server.CUSTOMER_HASH_MAP))) {

            HashMap<String, Customer> customerHashMap = (HashMap<String, Customer>) ois.readObject();

            return customerHashMap;

        } catch (EOFException e) {

            return new HashMap<String, Customer>();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void updateCustomerHashMap(HashMap<String, Customer> customerHashMap) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Server.CUSTOMER_HASH_MAP))) {

            oos.writeObject(customerHashMap);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static HashMap<String, Seller> getSellerHashMap() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Server.SELLER_HASH_MAP))) {

            HashMap<String, Seller> sellerHashMap = (HashMap<String, Seller>) ois.readObject();

            return sellerHashMap;

        } catch (EOFException e) {

            return new HashMap<String, Seller>();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void updateSellerHashMap(HashMap<String, Seller> sellerHashMap) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Server.SELLER_HASH_MAP))) {

            oos.writeObject(sellerHashMap);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static HashMap<String, Account> getAccountHashMap() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Server.ACCOUNT_HASH_MAP))) {

            HashMap<String, Account> accountHashMap = (HashMap<String, Account>) ois.readObject();

            return accountHashMap;

        } catch (EOFException e) {

            return new HashMap<String, Account>();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void updateAccountHashMap(HashMap<String, Account> accountHashMap) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Server.ACCOUNT_HASH_MAP))) {

            oos.writeObject(accountHashMap);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void close() {

        try {
            connection.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {

        getAccountHashMap();

    }

}
