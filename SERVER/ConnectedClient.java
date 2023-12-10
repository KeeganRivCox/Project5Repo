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
    private static final int GET_ALL_ACCOUNTS = 2;
    private static final int UPDATE_PASSWORD = 3;
    private static final int DELETE_ACCOUNT = 4;
    private static final int GET_SELLER = 5;
    private static final int GET_ALL_SELLERS = 6;
    private static final int UPDATE_SELLER = 7;
    private static final int GET_CUSTOMER = 8;
    private static final int GET_ALL_CUSTOMERS = 9;
    private static final int UPDATE_CUSTOMER = 10;
    private static final int UPDATE_MESSAGES = 11;
    private static final int GET_MESSAGES = 12;



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

                 case GET_ALL_ACCOUNTS -> {

                    serverGetAllAccounts();

                 }

                case UPDATE_PASSWORD -> {

                    serverUpdateAccountPassword();

                }

                 case DELETE_ACCOUNT -> {

                    serverDeleteAccount();

                 }

                 case GET_SELLER -> {

                    serverGetSeller();

                 }

                 case GET_ALL_SELLERS -> {

                    serverGetAllSellers();

                 }

                 case UPDATE_SELLER -> {

                    serverUpdateSeller();

                 }

                 case GET_CUSTOMER -> {

                    serverGetCustomer();

                 }

                 case GET_ALL_CUSTOMERS -> {

                    serverGetAllCustomers();

                 }

                 case UPDATE_CUSTOMER -> {

                    serverUpdateCustomer();

                 }
                 case UPDATE_MESSAGES -> {

                    serverUpdateMessages();

                 }
                 case GET_MESSAGES -> {

                    serverGetMessages();

                 }

            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void serverGetMessages() throws IOException {

        HashMap<String, ArrayList<String>> accountMessages = getAccountMessagesHashMap();

        requestWriter.writeObject(accountMessages);

        requestWriter.close();
        requestReader.close();

    }

    private void serverUpdateMessages() throws IOException, ClassNotFoundException {

        HashMap<String, ArrayList<String>> accountMessagesHashMap = getAccountMessagesHashMap();

        Account sender = (Account) requestReader.readObject();

        Account receiver = (Account) requestReader.readObject();

        String sentMessage = (String) requestReader.readObject();

        if (accountMessagesHashMap.containsKey(sender.getEmail()) && accountMessagesHashMap.containsKey(receiver.getEmail())) {

            String senderFormattedMessage = String.format("s,%s,%s,%s", receiver.getRole().toUpperCase(), receiver.getUsername(), sentMessage);

            String recieverFormattedMessage = String.format("r,%s,%s,%s", sender.getRole().toUpperCase(), sender.getUsername(), sentMessage);

            accountMessagesHashMap.get(sender.getEmail()).add(0,senderFormattedMessage);
            accountMessagesHashMap.get(receiver.getEmail()).add(0, recieverFormattedMessage);

            updateAccountMessagesHashMap(accountMessagesHashMap);

            requestWriter.writeObject(Boolean.TRUE);

        } else {

            requestWriter.writeObject(Boolean.FALSE);

        }

        requestWriter.close();
        requestReader.close();

    }

    //    private static final int GET_CUSTOMER = 8;
//    private static final int GET_ALL_CUSTOMERS = 9;
//    private static final int UPDATE_CUSTOMER = 10;

    private void serverUpdateCustomer() throws IOException, ClassNotFoundException {

        HashMap<String, Customer> customerHashMap = getCustomerHashMap();

        Customer retrievedCustomer = (Customer) requestReader.readObject();

        String customerEmail = retrievedCustomer.getEmail();

        if (customerHashMap.containsKey(customerEmail)) {

            customerHashMap.replace(customerEmail, retrievedCustomer);

            updateCustomerHashMap(customerHashMap);

            requestWriter.writeObject(Boolean.TRUE);

        } else {

            requestWriter.writeObject(Boolean.FALSE);

        }

        requestWriter.close();
        requestReader.close();

    }

    private void serverGetAllCustomers() throws IOException {

        HashMap<String, Customer> customerHashMap = getCustomerHashMap();

        ArrayList<Customer> allCustomers = new ArrayList<>(customerHashMap.values());

        requestWriter.writeObject(allCustomers);

        requestWriter.close();
        requestReader.close();

    }

    private void serverGetCustomer() throws IOException, ClassNotFoundException {

        HashMap<String, Customer> customerHashMap = getCustomerHashMap();

        String customerEmail = (String) requestReader.readObject();

        if(!customerHashMap.containsKey(customerEmail)) {

            requestWriter.writeObject(null);

            requestWriter.close();
            requestReader.close();

            return;

        }

        Customer retrievedCustomer = customerHashMap.get(customerEmail);

        requestWriter.writeObject(retrievedCustomer);

        requestReader.close();
        requestWriter.close();

    }

    private void serverUpdateSeller() throws IOException, ClassNotFoundException {

        HashMap<String, Seller> sellerHashMap = getSellerHashMap();

        Seller retrievedSeller = (Seller) requestReader.readObject();

        String sellerEmail = retrievedSeller.getEmail();

        if (sellerHashMap.containsKey(sellerEmail)) {

            sellerHashMap.replace(sellerEmail, retrievedSeller);

            updateSellerHashMap(sellerHashMap);

            requestWriter.writeObject(Boolean.TRUE);

        } else {

            requestWriter.writeObject(Boolean.FALSE);

        }

        requestWriter.close();
        requestReader.close();

    }

    private void serverGetAllSellers() throws IOException {

        HashMap<String, Seller> sellerHashMap = getSellerHashMap();

        ArrayList<Seller> allSellers = new ArrayList<>(sellerHashMap.values());

        requestWriter.writeObject(allSellers);

        requestWriter.close();
        requestReader.close();

    }

    private void serverGetSeller() throws IOException, ClassNotFoundException {

        HashMap<String, Seller> sellerHashMap = getSellerHashMap();

        String retrievedSellerEmail = (String) requestReader.readObject();

        requestWriter.writeObject(sellerHashMap.getOrDefault(retrievedSellerEmail, null));

        requestWriter.close();
        requestReader.close();

    }

    private void serverDeleteAccount() throws IOException, ClassNotFoundException {

       HashMap<String, Account> accountHashMap = getAccountHashMap();

       String accountEmail = (String) requestReader.readObject();

       Account removedAccount = accountHashMap.remove(accountEmail);

       updateAccountHashMap(accountHashMap);

       if (removedAccount != null) {

           String accountRole = removedAccount.getRole().toLowerCase();
           HashMap<String, ArrayList<String>> accountMessages = getAccountMessagesHashMap();
           accountMessages.remove(accountEmail);
           updateAccountMessagesHashMap(accountMessages);

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

    private void serverGetAllAccounts() throws IOException, ClassNotFoundException {

        HashMap<String, Account> accountHashMap = getAccountHashMap();

        ArrayList<Account> allAccounts = new ArrayList<>(accountHashMap.values());

        requestWriter.writeObject(allAccounts);

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
       HashMap<String, ArrayList<String>> messagesMap = getAccountMessagesHashMap();


       Account accountToCreate = (Account) requestReader.readObject();

       String accountEmail = accountToCreate.getEmail();

       String accountUsername = accountToCreate.getUsername();

       String accountRole = accountToCreate.getRole().toLowerCase();

       // Sends failure message if map already has an account with same email
       if (accountHashMap.containsKey(accountEmail)) {

           requestWriter.writeObject("email");

       } else if (!usernameAvailable(accountUsername)) {

           requestWriter.writeObject("username");

       } else {

           accountHashMap.put(accountEmail, accountToCreate);
           updateAccountHashMap(accountHashMap);

           messagesMap.put(accountEmail, new ArrayList<String>());
           updateAccountMessagesHashMap(messagesMap);

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

    private static boolean usernameAvailable(String username) {

        HashMap<String, Account> accountHashMap = getAccountHashMap();

        ArrayList<Account> allAccounts = new ArrayList<>(accountHashMap.values());

        for (Account account : allAccounts) {

            if (account.getUsername().equals(username)) {

                return false;

            }

        }

        return true;

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

    private static HashMap<String, ArrayList<String>> getAccountMessagesHashMap() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Server.MESSAGES_HASH_MAP))) {

            HashMap<String, ArrayList<String>> accountMessagesHashMap = (HashMap<String, ArrayList<String>>) ois.readObject();

            System.out.println("return found map");
            return accountMessagesHashMap;

        } catch (EOFException e) {

            System.out.println("return new map");
            return new HashMap<>();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void updateAccountMessagesHashMap(HashMap<String, ArrayList<String>> messagesHashMap) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Server.MESSAGES_HASH_MAP))) {

            oos.writeObject(messagesHashMap);
            System.out.println("updated");

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

        HashMap<String, ArrayList<String>> messagesMap = getAccountMessagesHashMap();
        //messagesMap.remove("nageekr@gmail.com");
        System.out.println(messagesMap);
        updateAccountMessagesHashMap(messagesMap);
        System.out.println(getAccountHashMap());

    }



}
