package socket;

import java.io.IOException;
import java.net.ServerSocket;

public class Server  {

    private ServerSocket server;


    public static void main(String[] args) {
        System.out.println("This is server");
        Server server = new Server();
        
        server.run();

    }
/**
 * processing clients
 */
    public void run() {

        try {
            server = new ServerSocket(4565, 1);
            while (true) {
                System.out.println("wait connected");
                ClientThread clientThread = new ClientThread(server.accept());
                new Thread(clientThread).start();;

            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}