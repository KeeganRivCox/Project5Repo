import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Purchaser implements Serializable {
    private String username;
    private String nameOfProductPurchased;
    private int quantityPurchased;
    private double revenueGenerated;


    public Purchaser(String username, String nameOfProductPurchased, int quantityPurchased, double revenueGenerated) {
        this.username = username;
        this.nameOfProductPurchased = nameOfProductPurchased;
        this.quantityPurchased = quantityPurchased;
        this.revenueGenerated = revenueGenerated;
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
