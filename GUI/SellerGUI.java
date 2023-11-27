import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SellerGUI {

    private JFrame frame = new JFrame("Seller Interface");

    private JPanel panelCont = new JPanel();

    private JPanel panelSearchProduct = new JPanel();

    private JPanel panelSearchStore = new JPanel();

    private JPanel mainScreen = new JPanel();

    private JPanel panelProduct = new JPanel();

    private JPanel panelStore = new JPanel();

    private JTextField productSearchBar = new JTextField(10);

    private JTextField storeSearchBar = new JTextField(10);

    private JButton MainMenuA = new JButton("Back to Main Page");
    private JButton MainMenuB = new JButton("Back to Main Page");

    private JButton MainMenuC = new JButton("Back to Main Page");

    private JButton productButtonSearch = new JButton("Search Product");

    private JButton storeButtonSearch = new JButton("Search Product");

    private JButton buttonsearchProduct = new JButton("View/Search Products");

    private JButton buttonSearchStore = new JButton("View/Search Store");

    private JButton buttonCreateStore = new JButton("Create Store");

    private JButton buttonCreateProduct = new JButton("Create Product");
    private CardLayout cl = new CardLayout();

    public SellerGUI(){

        panelCont.setLayout(cl);

        //Main Screen
        mainScreen.add(buttonsearchProduct);
        mainScreen.add(buttonSearchStore);
        mainScreen.add(buttonCreateProduct);
        mainScreen.add(buttonCreateStore);

        //Search Panel
        panelSearchProduct.add(productSearchBar);
        panelSearchProduct.add(productButtonSearch);
        panelSearchProduct.add(MainMenuA);

        //Search Store
        panelSearchStore.add(storeSearchBar);
        panelSearchStore.add(storeButtonSearch);
        panelSearchStore.add(MainMenuC);


        //Product Panel
        panelProduct.add(MainMenuB);

        //Background Colors for all Panels
        panelSearchProduct.setBackground(Color.WHITE);
        panelSearchStore.setBackground(Color.WHITE);
        mainScreen.setBackground(Color.WHITE);
        panelProduct.setBackground(Color.WHITE);

        //Adding panels to main panelCont
        panelCont.add(panelSearchProduct,"1");
        panelCont.add(mainScreen,"2");
        panelCont.add(panelProduct,"3");
        panelCont.add(panelSearchStore,"4");

        //Initial Starting Panel
        cl.show(panelCont,"2");

        //All Button Action Listeners

        productButtonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setTitle("The Product");
                cl.show(panelCont,"3");
            }
        });
        buttonsearchProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setTitle("Search Product");
                cl.show(panelCont,"1");

            }
        });
        MainMenuA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setTitle("Seller Interface");
                cl.show(panelCont,"2");
            }
        });
        MainMenuB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);
                frame.setTitle("Seller Interface");
                cl.show(panelCont,"2");
            }
        });

        //Finish Setting up initial panel

        frame.add(panelCont);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {



        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {


                new SellerGUI();
            }



        });


    }


}