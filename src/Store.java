import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 Project 4 -- Store Class

 Store class represents a retail store and encapsulates information such as the store's name,
 the seller who owns it, a list of purchasers, and a list of products available in the store.

 @author  Adeetya, Jordan, Keegan, Natalie, and Robert, Lab Section 39

 @version November 13th, 2023


 */

public class Store implements Serializable {
    private String name;
    private final Seller seller;
    private ArrayList<Purchaser> purchaserList;
    private ArrayList<Product> productList;

    // constructor
    public Store(String name, Seller seller, ArrayList<Purchaser> purchaserList, ArrayList<Product> productList) {
        this.name = name;
        this.seller = seller;
        this.purchaserList = purchaserList;
        this.productList = productList;
    }

    // allows a seller to edit their store's product details
    public static void editProducts(Store selectedStore, Scanner scanner) {

        System.out.printf("Here are the products held within %s\n", selectedStore.getName());
        Store.printStoreProducts(selectedStore);

        while (true) {

            System.out.println("""
                    What would you like to do? (#)
                    1. Edit a product
                    2. Add products
                    3. Return to edit a store""");
            int editProductChoice = Market.validatePromptInput(3, scanner);

            switch (editProductChoice) {

                case 1 -> {


                    Store.printStoreProducts(selectedStore);

                    System.out.println("Which product would you like to edit? (#)");
                    int productChoice = Market.validatePromptInput(selectedStore.getProducts().size(), scanner);
                    Product selectedProduct = selectedStore.getProducts().get(productChoice - 1);

                    System.out.println("""
                                    What Would You Like to Edit About This Product?
                                    1. Name
                                    2. Price
                                    3. Quantity Available
                                    4. Description""");
                    int productEditChoice = Market.validatePromptInput(4, scanner);

                    switch (productEditChoice) {

                        case 1 -> { // Name


                            System.out.printf("Current product name: %s\n", selectedProduct.getName());
                            System.out.println("What would you like the new product name to be? ");
                            String newProductName = scanner.nextLine();
                            selectedProduct.setName(newProductName);
                            System.out.println("Product name successfully changed!");


                        }

                        case 2 -> { // Price


                            System.out.printf("Current product price: $%.2f\n", selectedProduct.getPrice());
                            System.out.println("What would you like the new product price to be? ");
                            double newProductPrice = Market.validateDouble(scanner);
                            selectedProduct.setPrice(newProductPrice);
                            System.out.println("Product price successfully changed!");


                        }

                        case 3 -> { // Quantity available


                            System.out.printf("Current quantity available: %d\n", selectedProduct.getQuantity());
                            int newQuantityAvailable = Market.validatePromptInput(Integer.MAX_VALUE, scanner);
                            selectedProduct.setQuantity(newQuantityAvailable);
                            System.out.println("Quantity available successfully changed!");


                        }

                        case 4 -> { // Description


                            System.out.printf("Current product description: %s\n", selectedProduct.getDescription());
                            System.out.println("What would you like the new product description to be?");
                            String newDescription = scanner.nextLine();
                            selectedProduct.setDescription(newDescription);
                            System.out.println("Product description successfully changed!");


                        }

                    }

                }

                case 2 -> {


                    ArrayList<Product> productsToAdd = Seller.returnProducts(selectedStore, scanner);

                    for (Product product : productsToAdd) {

                        selectedStore.getProducts().add(product);

                    }

                    System.out.println("Products successfully added to store!");


                }

                case 3 -> {

                    return;

                }
            }

        }

    }

    private static void printStoreProducts(Store store) {

        int productCount = 1;

        for (Product product : store.getProducts()) {

            System.out.printf("%d. Product Name: %s, Price: $%.2f, Quantity Available: %d, Description: %s\n", productCount, product.getName(), product.getPrice(), product.getQuantity(), product.getDescription());
            productCount++;

        }

    }

    public static void printSellerStores(Seller seller) {

        int counter = 1;

        for (Store store : seller.getStores()) {

            System.out.printf("%d. %s\n", counter, store.getName());
            counter++;

        }

    }

    // returns the amount of products sold at a store
    public int getProductsSold() {
        int totalProductsSold = 0;

        for (Product product : this.getProducts()) {
            totalProductsSold += product.getQuantitySold();

        }

        return totalProductsSold;
    }

    // returns a list of stores sorted by amount of products sold
    public static ArrayList<Store> sortByProductsSold(ArrayList<Store> allStores, String sortType) {
        Comparator<Store> productsSoldComparator = Comparator.comparingInt(Store::getProductsSold);

        ArrayList<Store> sortedStores = allStores;

        switch (sortType) {
            case "high" -> {


                sortedStores.sort(Collections.reverseOrder(productsSoldComparator));
                return sortedStores;


            }
            case "low" -> {


                sortedStores.sort(productsSoldComparator);
                return sortedStores;


            }
            default -> {


                return sortedStores;


            }

        }

    }

    // returns the name of the store
    public String getName() {
        return this.name;
    }

    // returns the name of the seller owning store
    public Seller getSellerOwner() {
        return seller;
    }

    // returns a list of purchasers at a store
    public ArrayList<Purchaser> getPurchasers() {
        return this.purchaserList;
    }

    // returns a list of products
    public ArrayList<Product> getProducts() {
        return this.productList;
    }

    // modifies the list of products in the store
    public void setProducts(ArrayList<Product> productList) {
        this.productList = productList;
    }

    // modifies the name of the store
    public void setName(String name) {
        this.name = name;
    }

    // equals method to check if two stores are equal to each other
    public boolean equals(Store store) {
        return this.getName().equals(store.getName()) && this.getSellerOwner().equals(store.getSellerOwner());
    }

}

