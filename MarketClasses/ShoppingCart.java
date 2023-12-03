import java.io.Serializable;
import java.util.ArrayList;

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


