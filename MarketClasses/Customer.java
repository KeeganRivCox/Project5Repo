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

    public static void updateShoppingCart(Customer currentCustomer) {

        ArrayList<Product> updatedProducts = new ArrayList<>();

        for (Product product : currentCustomer.getShoppingCart().getProductList()) {

            Seller productSeller = new Request().getSeller(product.getStore().getSellerOwner().getEmail());

            Store productStore = productSeller.getStore(product.getStore());

            Product updatedProduct = productStore.getProduct(product);

            updatedProducts.add(updatedProduct);

        }

        currentCustomer.getShoppingCart().getProductList().clear();

        currentCustomer.getShoppingCart().getProductList().addAll(updatedProducts);

        new Request().updateCustomer(currentCustomer);

    }

    public Account getAccount() {

        return new Account(this.getName(), this.getEmail(), this.getPassword(), this.getUsername(), this.getRole());

    }

    public ArrayList<PurchasedProduct> getPreviouslyPurchasedProducts() {
        return previouslyPurchasedProducts;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

}