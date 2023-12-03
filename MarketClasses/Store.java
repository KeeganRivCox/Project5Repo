import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Store implements Serializable {
    private String name;
    private final Seller seller;
    private ArrayList<Purchaser> purchaserList;
    private ArrayList<Product> productList;
    private int productsSold;

    // constructor
    public Store(String name, Seller seller, ArrayList<Purchaser> purchaserList, ArrayList<Product> productList) {
        this.name = name;
        this.seller = seller;
        this.purchaserList = purchaserList;
        this.productList = productList;
        this.productsSold = 0;
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

}

