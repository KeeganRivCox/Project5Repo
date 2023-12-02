import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends Account implements Serializable {

    private ArrayList<PurchasedProduct> previouslyPurchasedProducts;
    private ShoppingCart shoppingCart;

    public Customer(String name, String email, String username, ArrayList<PurchasedProduct> previouslyPurchasedProducts, ShoppingCart shoppingCart) {
        super(name, email, username);
        this.previouslyPurchasedProducts = previouslyPurchasedProducts;
        this.shoppingCart = shoppingCart;
    }


    // Keegan - Made an account constructor
    public Customer(Account account, ArrayList<PurchasedProduct> previouslyPurchasedProducts, ShoppingCart shoppingCart) {
        super(account);
        this.previouslyPurchasedProducts = previouslyPurchasedProducts;
        this.shoppingCart = shoppingCart;
    }

}