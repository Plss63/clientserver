package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client implements Runnable {
    static private ObjectInputStream input;
    static private ObjectOutputStream output;
    private Socket connection;
    private String name;

    public static void main(String[] args) {
        System.out.println("This is client");
        new Thread(new Client("UserOne")).start();

    }

    public Client(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println("connecting...");

                connection = new Socket(InetAddress.getByName("127.0.0.1"), 4565);
                System.out.println("connect true");
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                send(name);
                System.out.println((String) input.readObject());
            output.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void send(Object object) {
        try {
            output.flush();
            output.writeObject(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
