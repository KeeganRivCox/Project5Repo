import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Seller extends Account implements Serializable {

    private ArrayList<Store> sellerStores;

    //A Seller constructor with the parameters Account and Store arraylist to create a Seller
    public Seller(Account account, ArrayList<Store> sellerStores) {
        super(account);
        this.sellerStores = sellerStores;
    }

    public Account getAccount() {

        return new Account(this.getName(), this.getEmail(), this.getPassword(), this.getUsername(), this.getRole());

    }

    public int getTotalProductsSold() {

        if (sellerStores.isEmpty()) {
            return 0;
        }

        int totalProductsSold = 0;

        for (Store store : sellerStores) {

            totalProductsSold += store.getProductsSold();

        }

        return totalProductsSold;

    }

    public static ArrayList<Seller> sortByTotalProductsSold(ArrayList<Seller> sellers, String sortType) {

        Comparator<Seller> totalProductsSoldComparator = Comparator.comparingInt(c -> c.getTotalProductsSold());

        ArrayList<Seller> sortedSellers = sellers;

        switch (sortType) {

            case "high" -> {


                sortedSellers.sort(Collections.reverseOrder(totalProductsSoldComparator));
                return sortedSellers;


            }
            case "low" -> {


                sortedSellers.sort(totalProductsSoldComparator);
                return sortedSellers;


            }
            default -> {


                return sortedSellers;


            }

        }

    }

    public Store getStore(Store store) {

        for (Store store1 : this.getSellerStores()) {

            if (store1.equals(store)) {

                return store1;

            }

        }

        return null;

    }

    public boolean equals(Seller seller) {

        return seller.getEmail().equals(this.getEmail());

    }

    public ArrayList<Store> getSellerStores() {
        return sellerStores;
    }

}