import java.io.Serializable;
import java.util.ArrayList;

/**
 Project 4 -- ShoppingCart

 The ShoppingCart manages a list of products in the shopping cart.

 @author  Adeetya, Jordan, Keegan, Natalie, and Robert, Lab Section 39

 @version November 13th, 2023


 */

public class ShoppingCart implements Serializable {

    private ArrayList<Product> productList;

    // constructor
    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }

    // returns a list of products in the shopping cart
    public ArrayList<Product> getProductList() {
        return productList;
    }

}


