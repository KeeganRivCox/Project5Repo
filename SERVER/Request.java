import java.io.*;
import java.net.Socket;

public class Request {

    public static final int CREATE = 0;
    public static final int UPDATE = 1;
    public static final int ACCOUNT = 2;
    public static final int SELLER = 2;
    public static final int CUSTOMER = 3;

    private Socket serverConnection;

    // POST object for sending an account to store to server
    public Request() {

        try {
            serverConnection = new Socket("localhost", 9087);
        } catch (IOException e) {
            System.out.println("Connection error occurred while getting from server...");
        }

    }

    public boolean createAccount(Account account) {

        ObjectOutputStream oos;

        BufferedReader br;

        PrintWriter pw;

        try {

            oos = new ObjectOutputStream(this.serverConnection.getOutputStream());

            br = new BufferedReader(new InputStreamReader(this.serverConnection.getInputStream()));

            pw = new PrintWriter(this.serverConnection.getOutputStream());

            sendServerRequest(CREATE, ACCOUNT, pw);
            pw.flush();

            oos.writeObject(account);

            String confirmation = br.readLine();

            oos.close();
            br.close();
            pw.close();

            return confirmation.equalsIgnoreCase("success");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendServerRequest(int requestType, int accountType, PrintWriter pw) {

        String requestLine = String.format("%d,%d", requestType, accountType);

        pw.println(requestLine);
        pw.flush();

    }

    public static void main(String[] args) {

        if (!new Request().createAccount(new Account("Kyclon", "nageekr@gmail.com", "AstroBoy@1", "seller"))) {

            System.out.println("That account already exists");

        }

    }

}
