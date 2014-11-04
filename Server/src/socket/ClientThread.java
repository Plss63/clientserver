package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread implements Runnable{
    private final Socket connection;

    public ClientThread(Socket socket) {
        this.connection = socket;
    }

    @Override
    public void run() {
        try{
        ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
        output.writeObject("Hello: " + (String) input.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
