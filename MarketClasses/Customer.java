import java.io.*;
import java.util.ArrayList;

public class Customer extends Account implements Serializable {

    private ArrayList<PurchasedProduct> previouslyPurchasedProducts;
    private ShoppingCart shoppingCart;

    // Keegan - Made an account constructor
    public Customer(Account account, ArrayList<PurchasedProduct> previouslyPurchasedProducts, ShoppingCart shoppingCart) {
        super(account);
        this.previouslyPurchasedProducts = previouslyPurchasedProducts;
        this.shoppingCart = shoppingCart;
    }

    public ArrayList<PurchasedProduct> getPreviouslyPurchasedProducts() {
        return previouslyPurchasedProducts;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

}