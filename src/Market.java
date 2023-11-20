import java.io.File;
import java.util.Scanner;

/**
 Project 4 -- Market

 The Market class acts as the orchestrator, managing user interfaces, roles, and interactions.
 Interfaces are defined as multiline strings, guiding users through account creation, sign-in, and
 role-specific functionalities. Whether you're a seller or a customer, the Market class tailors your
 experience accordingly. It ensures that your choices are valid, displaying helpful error messages when necessary.

 @author Adeetya, Jordan, Keegan, Natalie, and Robert, Lab Section 39

 @version November 13th, 2023


 */

public class Market {

    // Important file paths to storage files
    public static final String CUSTOMER_DATA = new File("customer_data.txt").getAbsolutePath();
    public static final String CUSTOMER_PURCHASES = new File("customer_purchases.txt").getAbsolutePath();
    public static final String CUSTOMER_MESSAGES = new File("customer_messages.txt").getAbsolutePath();
    public static final String SELLER_DATA = new File("seller_data.txt").getAbsolutePath();

    // Keegan - String that displays at log in
    private static final String LOGIN_INTERFACE = 
            """
            1. Create Account
            2. Sign In
            3. Forgot Password
            4. Delete Account
            5. Exit
            Enter your choice:
            """;


    // Keegan - String that displays when a seller logs in
    private static final String SELLER_INTERFACE = 
            """
            What would you like to do?
            1. List stores
            2. View store statistics
            3. Create store
            4. Edit store
            5. Delete store
            6. View customer messages
            7. Log out
            Enter your choice:
            """;

    //Robert - String that displays when a customer logs in
    private static final String CUSTOMER_INTERFACE = 
            """
            What would you like to do?
            1. See all listings
            2. See sellers
            3. Search
            4. My account
            5. Log out
            Enter your choice:
            """;

    //main method for the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account account; // Initialize account

        while (true) {

            boolean exit = false; // Used for exiting out of program

            do {

                account = null;
                System.out.print(LOGIN_INTERFACE); // 1. Create Account, 2. Sign In, 3. Forgot Password, 4. Delete Account, 5. Exit
                int loginPromptChoice = validatePromptInput(5, scanner);

                switch (loginPromptChoice) {

                    case 1 -> Account.createAccount(scanner);

                    case 2 -> account = Account.signInLoop(scanner);

                    case 3 -> Account.forgotPassword(scanner);

                    case 4 -> Account.deleteAccount(scanner);

                    case 5 -> {

                        System.out.println("Exiting the application.");
                        exit = true;

                    }

                }

            } while (account == null && !exit);

            if (exit) {

                break; // If user selects exit then exit the loop and end program

            } else {

                if (account.isAuthenticatedSeller()) { // Account role check to assign correct interface

                    Seller userSeller = Seller.fetchSeller(account); // Retrieve stored seller data
                    workingSellerInterface(userSeller, scanner);

                }

                if (account.isAuthenticatedCustomer()) { // Account role check to assign correct interface

                    Customer userCustomer = Customer.fetchCustomer(account); // Retrieve stored customer data
                    workingCustomerInterface(userCustomer, scanner);

                }

            }

        }
    }

    // the customer interface
    private static void workingCustomerInterface(Customer customer, Scanner scanner) {

        Customer.updateCustomerData(customer);

        while(true) {

            System.out.print(CUSTOMER_INTERFACE); // 1. See All Listings, 2. See Sellers, 3. Search, 4. My Account, 5. Log Out
            int customerInterfaceChoice = Market.validatePromptInput(5, scanner);

            switch (customerInterfaceChoice) {

                case 1 -> { // See all listings


                    Customer.listings(customer, scanner);
                    Customer.updateCustomerData(customer);


                }

                case 2 -> { // See sellers


                     Customer.seeSellersLoop(customer, scanner);
                     Customer.updateCustomerData(customer);


                }

                case 3 -> { // Search


                    Customer.searchForProducts(customer, scanner);
                    Customer.updateCustomerData(customer);


                }

                case 4 -> { // My account


                    Customer.myAccount(customer, scanner);
                    Customer.updateCustomerData(customer);


                }

                case 5 -> { // Log out


                    Customer.updateCustomerData(customer);
                    System.out.printf("Logging user %s out...\n", customer.getUsername());
                    return;


                }

            }

        }

    }

    // seller interface
    private static void workingSellerInterface(Seller seller, Scanner scanner) {

        Seller.updateSellerData(seller);

        while(true) {

            System.out.print(SELLER_INTERFACE); //  1. List Stores, 2. View Store Statistics, 3. Create Store, 4. Edit Store, 5. Delete Store, 6. View customer messages 7. Log out
            int sellerInterfaceChoice = validatePromptInput(7, scanner);

            switch(sellerInterfaceChoice) {

                case 1 -> Seller.listDetailedStores(seller); // List stores

                case 2 -> Seller.storeStatistics(seller, scanner); // View store statistics

                case 3 -> Seller.createStore(seller, scanner); // Create store

                case 4 -> { // Edit store


                    Seller.editStore(seller, scanner);
                    Seller.updateSellerData(seller);


                }

                case 5 -> { // Delete store


                    Seller.deleteStore(seller, scanner);
                    Seller.updateSellerData(seller);


                }

                case 6 -> Seller.viewCustomerMessages(seller); // View customer messages

                case 7 -> { // Log out


                    Seller.updateSellerData(seller);
                    System.out.printf("Logging user %s out...\n", seller.getUsername());
                    return;


                }

            }

        }

    }


    // A validation input to check if user input is either yes or no
    public static String validateYesOrNo(Scanner scanner) {

        while (true) {

            String yesOrNo = scanner.nextLine();

            if (yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no")) {

                return yesOrNo.toLowerCase();

            } else {

                System.out.println("Invalid choice. Please choose yes or no");

            }

        }

    }

    // A validate input check to see if input is an int and in range specified from 0 - #
    public static int validatePromptInput(int range, Scanner scanner) {

        while (true) {

            String input = scanner.nextLine();
            int intInput;

            try {

                intInput = Integer.parseInt(input);

                if (intInput > 0 && intInput <= range) {

                    return intInput;

                } else {

                    System.out.println("Choice out of range. Please try again.");

                }

            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Please try again.");

            }

        }

    }

    // A validate input method to check if input is a double and greater than 0
    public static double validateDouble(Scanner scanner) {

        while (true) {

            String input = scanner.nextLine();
            double doubleInput;

            try {

                doubleInput = Double.parseDouble(input);

                if (doubleInput > 0) {

                    return doubleInput;

                } else {

                    System.out.println("Choice can not be 0");

                }

            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Please try again.");

            }

        }

    }

}