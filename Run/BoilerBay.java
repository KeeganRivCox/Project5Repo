import javax.swing.*;
import java.util.concurrent.CountDownLatch;

public class BoilerBay {


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new BoilerBay());

    }


    public BoilerBay() {

        new AccountsPanel().display();

    }


}
