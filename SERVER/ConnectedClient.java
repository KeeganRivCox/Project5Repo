import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ConnectedClient {

    private Socket connection;

    public ConnectedClient(Socket clientConnection) {

        connection = clientConnection;

    }

    // Executing specific task based upon object type sent - synchronized to prevent race events
    public void execute() {

        BufferedReader br;
        ObjectInputStream ois;
        PrintWriter pw;
        int requestType;
        int objectType;

        try {

            br = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));

            ois = new ObjectInputStream(this.connection.getInputStream());

            pw = new PrintWriter(this.connection.getOutputStream());

            String requestLine = br.readLine();

            requestType = determineRequest(requestLine);
            objectType = determineRequestObject(requestLine);

            if (performRequest(requestType, objectType, ois)) {

                pw.println("success");

            } else {

                pw.println("failure");

            }

            pw.flush();
            pw.close();
            br.close();
            ois.close();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }


    }

    private boolean performRequest(int requestType, int objectType, ObjectInputStream ois) {


        switch (objectType) {

            case Request.ACCOUNT -> {

                HashMap<String, Account> accountMap = getAccountHashMap();
                try {
                    Account account = (Account) ois.readObject();
                    assert accountMap != null;
                    if (accountMap.containsKey(account.getEmail())) {
                        return false;
                    } else {
                        accountMap.put(account.getEmail(), account);
                        returnAccountHashMap(accountMap);
                        return true;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }

        }

        return false;

    }

    private void returnAccountHashMap(HashMap<String,Account> accountMap) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Server.ACCOUNT_HASH_MAP))) {

            oos.writeObject(accountMap);

        } catch (IOException e) {

            System.out.println("Error occurred trying to access account hash map...");

        }

    }

    private HashMap<String,Account> getAccountHashMap() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Server.ACCOUNT_HASH_MAP))) {

            return (HashMap<String, Account>) ois.readObject();

        } catch (EOFException e) {

            System.out.println("No account hash map stored yet...");
            return new HashMap<String, Account>();

        } catch (IOException e) {

            System.out.println("Error occurred trying to access account hash map...");
            return null;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private int determineRequestObject(String requestLine) {

        String[] requestData = requestLine.split(",");
        return Integer.parseInt(requestData[1]);

    }

    private int determineRequest(String requestLine) {

        String[] requestData = requestLine.split(",");
        return Integer.parseInt(requestData[0]);

    }

    public void close() {

        try {

            connection.close();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }



}
