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

        ArrayList<Account> allAccounts = new Request().getAllAccounts();

       for (Account account : allAccounts) {

           new Request().deleteAccount(account.getEmail());

        }

    new Request().createAccount(new Account("Keegan", "nageekr@gmail.com", "AstroBoy@1", "Kyclon", "seller"));

        //new Request().createAccount(new Account("Keegan", "nageekr@gmail.com", "AstroBoy@1", "Kyclon", "seller"));
        ArrayList<Purchaser> purchasersOne = new ArrayList<>();
        ArrayList<Product> productsOne = new ArrayList<>();

        ArrayList<Purchaser> purchaserTwo = new ArrayList<>();
        ArrayList<Product> productsTwo = new ArrayList<>();

        ArrayList<Purchaser> purchaserThree = new ArrayList<>();
        ArrayList<Product> productsThree = new ArrayList<>();

        Store storeOne = new Store("Store One", new Request().getSeller("nageekr@gmail.com"), purchasersOne, productsOne);
        Store storeTwo = new Store("Store Two", new Request().getSeller("nageekr@gmail.com"), purchaserTwo, productsTwo);
        Store storeThree = new Store("Store Three", new Request().getSeller("nageekr@gmail.com"), purchaserThree, productsThree);



        Product productOne = new Product("Product One", 3.99, 10, "description", storeOne);
        Product productTwo = new Product("Product Two", 4.99, 8, "description", storeOne);
        Product productThree = new Product("Product Three", 5.99, 9, "description", storeOne);
        Product productFour = new Product("Product Four", 2.99, 7, "description", storeOne);
        Product productFive = new Product("Product Five", 1.99, 6, "description", storeOne);
        Product productSix = new Product("Product Six", 0.99, 6, "description", storeOne);
        Product productSeven = new Product("Product Seven", 6.99, 3, "description", storeOne);
        Product productEight = new Product("Product Eight", 7.99, 2, "description", storeOne);
        Product productNine = new Product("Product Nine", 8.99, 1, "description", storeOne);
        Product productTen = new Product("Product Ten", 9.99, 4, "description", storeOne);



        Product productEleven = new Product("Product Eleven", 3.99, 10, "description", storeTwo);
        Product productTwelve = new Product("Product Twelve", 4.99, 8, "description", storeTwo);
        Product productThirteen = new Product("Product Thirteen", 5.99, 9, "description", storeTwo);
        Product productFourteen = new Product("Product Fourteen", 2.99, 7, "description", storeTwo);


        Product productFifteen = new Product("Product Fifteen", 1.99, 6, "description", storeThree);
        Product productSixteen = new Product("Product Sixteen", 0.99, 6, "description", storeThree);
        Product productSeventeen = new Product("Product Seventeen", 6.99, 3, "description", storeThree);
        Product productEighteen = new Product("Product Eighteen", 7.99, 2, "description", storeThree);
        Product productNineteen = new Product("Product Nineteen", 8.99, 1, "description", storeThree);
        Product productTwenty = new Product("Product Twenty", 9.99, 4, "description", storeThree);


        //Product productEleven = new Product("Product Eleven", 10.99, 4, "description", storeTwo);
        // Products


        storeOne.getProducts().add(productOne);
        storeOne.getProducts().add(productTwo);
        storeOne.getProducts().add(productThree);
        storeOne.getProducts().add(productFour);
        storeOne.getProducts().add(productFive);
        storeOne.getProducts().add(productSix);
        storeOne.getProducts().add(productSeven);
        storeOne.getProducts().add(productEight);
        storeOne.getProducts().add(productNine);
        storeOne.getProducts().add(productTen);


        storeTwo.getProducts().add(productEleven);
        storeTwo.getProducts().add(productTwelve);
        storeTwo.getProducts().add(productThirteen);
        storeTwo.getProducts().add(productFourteen);

        storeThree.getProducts().add(productFifteen);
        storeThree.getProducts().add(productSixteen);
        storeThree.getProducts().add(productSeventeen);
        storeThree.getProducts().add(productEighteen);
        storeThree.getProducts().add( productNineteen);
        storeThree.getProducts().add(productTwenty);

        //storeTwo.getProducts().add(productEleven);



        Seller seller = new Request().getSeller("nageekr@gmail.com");

        seller.getSellerStores().add(storeOne);
        seller.getSellerStores().add(storeTwo);
        seller.getSellerStores().add(storeThree);

        new Request().updateSeller(seller);

        //Seller seller = new Request().getSeller("nageekr@gmail.com");
        ArrayList<Seller> allSellers = new Request().getAllSellers();
        System.out.println(allSellers.get(0).getSellerStores().get(0).getProducts().get(0).getName());


    }



}
