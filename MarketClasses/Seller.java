import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Seller extends Account implements Serializable {

    private ArrayList<Store> sellerStores;

    //A Seller constructor with the parameters Account and Store arraylist to create a Seller
    public Seller(Account account, ArrayList<Store> sellerStores) {
        super(account);
        this.sellerStores = sellerStores;
    }

    public ArrayList<Store> getSellerStores() {
        return sellerStores;
    }

}