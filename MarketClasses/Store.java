import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Store implements Serializable {
    private String name;
    private final Seller seller;
    private ArrayList<Purchaser> purchaserList;
    private ArrayList<Product> productList;
    private int productsSold;
    private Random serialGenerator = new Random();

    // constructor
    public Store(String name, Seller seller, ArrayList<Purchaser> purchaserList, ArrayList<Product> productList) {
        this.name = name;
        this.seller = seller;
        this.purchaserList = purchaserList;
        this.productList = productList;
        this.productsSold = 0;
    }

    public void addProduct(Product product) {

        if (this.getProducts().size() == 1000) {

            JOptionPane.showMessageDialog(null, "You have reached the product limit for a store", "Product Creation", JOptionPane.ERROR_MESSAGE);
            return;

        }

        boolean inUse;
        int serialNumber;

        do {

            inUse = false;

            serialNumber = serialGenerator.nextInt(1000);

            for (Product product1 : this.getProducts()) {

                if (product1.getSerialNumber() == serialNumber) {

                    inUse = true;
                    break;

                }

            }

        } while (inUse);

        product.setSerialNumber(serialNumber);
        this.getProducts().add(product);

    }

    public void removeProduct(Product product) {

        int serialNumber = product.getSerialNumber();

        for (Product product1 : this.getProducts()) {

            if (product1.getSerialNumber() == serialNumber) {

                this.getProducts().remove(product1);
                return;

            }

        }

    }

    // A method to fetch the most fresh versions of a product
    public Product getProduct(Product product) {

        for (Product productInStore : this.getProducts()) {

            if (productInStore.getSerialNumber() == product.getSerialNumber()) {

                return productInStore;

            }

        }

        return null;

    }

    // returns a list of stores sorted by amount of products sold
    public static ArrayList<Store> sortByProductsSold(ArrayList<Store> allStores, String sortType) {
        Comparator<Store> productsSoldComparator = Comparator.comparingInt(Store::getProductsSold);

        ArrayList<Store> sortedStores = allStores;

        switch (sortType) {
            case "high" -> {


                sortedStores.sort(Collections.reverseOrder(productsSoldComparator));
                return sortedStores;


            }
            case "low" -> {


                sortedStores.sort(productsSoldComparator);
                return sortedStores;


            }
            default -> {


                return sortedStores;


            }

        }

    }

    public int getProductsSold() {
        return this.productsSold;
    }

    // returns the name of the store
    public String getName() {
        return this.name;
    }

    // returns the name of the seller owning store
    public Seller getSellerOwner() {
        return seller;
    }

    // returns a list of purchasers at a store
    public ArrayList<Purchaser> getPurchasers() {
        return this.purchaserList;
    }

    // returns a list of products
    public ArrayList<Product> getProducts() {
        return this.productList;
    }

    // modifies the list of products in the store
    public void setProducts(ArrayList<Product> productList) {
        this.productList = productList;
    }

    // modifies the name of the store
    public void setName(String name) {
        this.name = name;
    }

    // equals method to check if two stores are equal to each other
    public boolean equals(Store store) {
        return this.getName().equals(store.getName()) && this.getSellerOwner().equals(store.getSellerOwner());
    }

    public String toString() {

        return this.name;

    }

}

