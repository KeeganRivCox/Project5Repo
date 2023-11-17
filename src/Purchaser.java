import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 Project 4 -- Purchaser Class

 The Purchaser class facilitates the representation and sorting of purchasers in a marketplace.
 Key functionalities include sorting purchasers based on either quantity purchased or revenue generated,
 with options for ascending or descending order.

 @author Adeetya, Jordan, Keegan, Natalie, and Robert, Lab Section 39

 @version November 13th, 2023

 */

public class Purchaser implements Serializable {
    private String username;
    private String nameOfProductPurchased;
    private int quantityPurchased;
    private double revenueGenerated;

    // Sort prompt for purchaser objects
    private static final String SORT_PROMPT =
            """
            How would you like to sort customer sales?
            1. By quantity purchased - high to low
            2. By revenue generated - high to low
            3. By quantity purchased - low to high
            4. By revenue generated - low to high
            """;


    public Purchaser(String username, String nameOfProductPurchased, int quantityPurchased, double revenueGenerated) {
        this.username = username;
        this.nameOfProductPurchased = nameOfProductPurchased;
        this.quantityPurchased = quantityPurchased;
        this.revenueGenerated = revenueGenerated;
    }

    public static ArrayList<Purchaser> sortPurchasers(Store store, Scanner scanner) {

        System.out.print(SORT_PROMPT); // 1. By quantity purchased - high to low, 2. By revenue generated - high to low, 3. By quantity purchased - low to high, 4. By revenue generated - low to high
        int sortPromptChoice = Market.validatePromptInput(4, scanner);

        switch (sortPromptChoice) {
            case 1 -> { // quantity purchased - high to low


                return Purchaser.sortByQuantityPurchased(store, "high");


            }
            case 2 -> { // revenue generated - high to high


                return Purchaser.sortByRevenueGenerated(store, "high");


            }
            case 3 -> { // quantity purchased - low to high


                return Purchaser.sortByQuantityPurchased(store, "low");


            }
            case 4 -> { // revenue generated - low to high


                return Purchaser.sortByRevenueGenerated(store, "low");


            }

        }

        return store.getPurchasers();

    }

    private static ArrayList<Purchaser> sortByRevenueGenerated(Store store, String sortType) {

        Comparator<Purchaser> revenueGeneratedComparator = (c1, c2) -> (int) (c1.getRevenueGenerated() - c2.getRevenueGenerated());

        ArrayList<Purchaser> sortedPurchasers = store.getPurchasers();

        switch (sortType) {
            case "high" -> {


                sortedPurchasers.sort(Collections.reverseOrder(revenueGeneratedComparator));
                return sortedPurchasers;


            }
            case "low" -> {


                sortedPurchasers.sort(revenueGeneratedComparator);
                return sortedPurchasers;


            }
            default -> {


                return sortedPurchasers;


            }

        }

    }

    private static ArrayList<Purchaser> sortByQuantityPurchased(Store store, String sortType) {

        Comparator<Purchaser> quantityPurchasedComparator = Comparator.comparingInt(Purchaser::getQuantityPurchased);

        ArrayList<Purchaser> sortedPurchasers = store.getPurchasers();

        switch (sortType) {
            case "high" -> {


                sortedPurchasers.sort(Collections.reverseOrder(quantityPurchasedComparator));
                return sortedPurchasers;


            }
            case "low" -> {


                sortedPurchasers.sort(quantityPurchasedComparator);
                return sortedPurchasers;


            }
            default -> {


                return sortedPurchasers;


            }
        }

    }

    public String getUsername() {
        return username;
    }

    // returns the name of the product purchased by this purchaser
    public String getNameOfProductPurchased() {
        return nameOfProductPurchased;
    }

    // returns the quantity purchased of the product purchased
    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    // returns the total revenue generated from the bought product
    public double getRevenueGenerated() {
        return revenueGenerated;
    }

    // modifies the purchaser's username
    public void setUsername(String username) {
        this.username = username;
    }

    // modifies the name of the product purchased
    public void setNameOfProductPurchased(String nameOfProductPurchased) {
        this.nameOfProductPurchased = nameOfProductPurchased;
    }

    // modifies the quantity purchased of a product
    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    // modifies the total revenue generated from a product
    public void setRevenueGenerated(double revenueGenerated) {
        this.revenueGenerated = revenueGenerated;
    }


}
