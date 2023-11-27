import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 Project 4 -- PurchasedProduct Class

 It encapsulates the details of a purchased product, facilitating serialization for storage or transfer of
 purchase information. Customers can easily access the name, amount purchased, store name,
 and the purchased product itself.

 @author Adeetya, Jordan, Keegan, Natalie, and Robert, Lab Section 39

 @version November 13th, 2023


 */
public class PurchasedProduct implements Serializable {

    private Product purchasedProduct;
    private int amountPurchased;

    public PurchasedProduct(Product product, int amountPurchased) {
        this.purchasedProduct = product;
        this.amountPurchased = amountPurchased;
    }

    public static ArrayList<PurchasedProduct> sortByProductsPurchased(Customer customer, String sortType) {

        Comparator<PurchasedProduct> amountPurchasedComparator = Comparator.comparingInt(PurchasedProduct::getAmountPurchased);

        ArrayList<PurchasedProduct> purchasedProducts = customer.getPreviouslyPurchasedProducts();

        switch (sortType) {
            case "high" -> {


                purchasedProducts.sort(Collections.reverseOrder(amountPurchasedComparator));
                return purchasedProducts;


            }
            case "low" -> {


                purchasedProducts.sort(amountPurchasedComparator);
                return purchasedProducts;


            }
            default -> {


                return purchasedProducts;


            }

        }

    }

    public Product getPurchasedProduct() {
        return purchasedProduct;
    }

    // returns the total amount of a product purchased
    public int getAmountPurchased() {
        return amountPurchased;
    }

    // adds the total amount purchased together
    public void addAmountPurchased(int amountPurchased) {
        this.amountPurchased += amountPurchased;
    }
}
