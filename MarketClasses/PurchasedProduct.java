import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PurchasedProduct implements Serializable {

    private Product purchasedProduct;
    private int amountPurchased;

    public PurchasedProduct(Product product, int amountPurchased) {
        this.purchasedProduct = new Product(product.getName(), product.getPrice(), product.getQuantity(), product.getDescription(), product.getStore());
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
