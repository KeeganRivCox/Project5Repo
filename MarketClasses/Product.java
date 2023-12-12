import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Product implements Serializable {
    private String name;
    private String description;
    private int quantity;
    private int quantitySold;
    private double price;
    double revenueGenerated;
    private final Store store;
    private int serialNumber;

    public Product(String name, double price, int quantity, String description, Store store) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.quantitySold = 0;
        this.revenueGenerated = 0;
        this.store = store;
    }

    // sorts the list of products by price
    public static ArrayList<Product> sortByPrice(ArrayList<Product> allProducts, String sortType) {

        Comparator<Product> priceComparator = (c1, c2) -> (int) (c1.price - c2.price);

        ArrayList<Product> sortedProducts = allProducts;

        switch (sortType) {
            case "high" -> {


                sortedProducts.sort(Collections.reverseOrder(priceComparator));
                return sortedProducts;


            }
            case "low" -> {


                sortedProducts.sort(priceComparator);
                return sortedProducts;


            }
            default -> {


                return sortedProducts;


            }
        }
    }

    // sorts the list of products by quantity
    public static ArrayList<Product> sortByQuantity(ArrayList<Product> allProducts, String sortType) {

        Comparator<Product> quantityComparator = Comparator.comparingInt(c -> c.quantity);

        ArrayList<Product> sortedProducts = allProducts;

        switch (sortType) {
            case "high" -> {


                sortedProducts.sort(Collections.reverseOrder(quantityComparator));
                return sortedProducts;


            }
            case "low" -> {


                sortedProducts.sort(quantityComparator);
                return sortedProducts;


            }
            default -> {


                return sortedProducts;


            }

        }

    }

    public static ArrayList<Product> sortByQuantitySold(ArrayList<Product> products, String sortType) {

        Comparator<Product> quantitySoldComparator = Comparator.comparingInt(c -> c.quantitySold);

        ArrayList<Product> sortedProducts = products;

        switch (sortType) {
            case "high" -> {


                sortedProducts.sort(Collections.reverseOrder(quantitySoldComparator));
                return sortedProducts;


            }
            case "low" -> {


                sortedProducts.sort(quantitySoldComparator);
                return sortedProducts;


            }
            default -> {


                return sortedProducts;


            }

        }

    }

    public static ArrayList<Product> sortByRevenueGenerated(ArrayList<Product> products, String sortType) {

        Comparator<Product> revenueGeneratedComparator = Comparator.comparingDouble(c -> c.revenueGenerated);

        ArrayList<Product> sortedProducts = products;

        switch (sortType) {
            case "high" -> {


                sortedProducts.sort(Collections.reverseOrder(revenueGeneratedComparator));
                return sortedProducts;


            }
            case "low" -> {


                sortedProducts.sort(revenueGeneratedComparator);
                return sortedProducts;


            }
            default -> {


                return sortedProducts;


            }

        }

    }

    public static ArrayList<Product> sortByAmountInShoppingCarts(ArrayList<Product> products, String sortType) {

        Comparator<Product> inShoppingCartsComparator = Comparator.comparingDouble(Product::getAmountInShoppingCarts);

        ArrayList<Product> sortedProducts = products;

        switch (sortType) {
            case "high" -> {


                sortedProducts.sort(Collections.reverseOrder(inShoppingCartsComparator));
                return sortedProducts;


            }
            case "low" -> {


                sortedProducts.sort(inShoppingCartsComparator);
                return sortedProducts;


            }
            default -> {


                return sortedProducts;


            }

        }

    }

    public void updateProduct(int quantityToPurchase) {
      
        int quantityAvailable = this.quantity;
        quantityAvailable -= quantityToPurchase;
        this.quantity = quantityAvailable;
        this.revenueGenerated += quantityToPurchase * this.price;
        this.quantitySold += quantityToPurchase;
      
    }

    public int getAmountInShoppingCarts() {

        ArrayList<Customer> allCustomers = new Request().getAllCustomers();

        int amountInShoppingCarts = 0;

        for (Customer customer : allCustomers) {

            for (Product product : customer.getShoppingCart().getProductList()) {

                if (this.getStore().equals(product.getStore()) && this.serialNumber == product.getSerialNumber()) {

                    amountInShoppingCarts++;

                }

            }

        }

        return amountInShoppingCarts;

    }

    // returns the product name
    public String getName() {
        return name;
    }

    // returns the product description
    public String getDescription() {
        return description;
    }

    // returns the quantity of a product
    public int getQuantity() {
        return quantity;
    }

    // returns the price of a product
    public double getPrice() {
        return price;
    }

    // returns the quantity sold of a product
    public int getQuantitySold() {
        return quantitySold;
    }

    // returns the revenue generated from a product
    public double getRevenueGenerated() {
        return this.revenueGenerated;
    }

    // returns the store the product is located at
    public Store getStore() {
        return this.store;
    }

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    // modifies the name of a product
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // modifies the quantity of a product
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // modifies the price of a product
    public void setPrice(double price) {
        this.price = price;
    }


    // Keegan - A method to return if two products are equal
    public boolean equals(Product product) {
        return this.getName().equals(product.getName()) && this.getStore().equals(product.getStore()) && this.getDescription().equals(product.getDescription());
    }
}

