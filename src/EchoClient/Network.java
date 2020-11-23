package EchoClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8189;

    private Socket socket;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    private final String host;
    private final int  port;



    public Network (String host, int port) {
        this.host = host;
        this.port = port;
    }
    public Network(){
        this(SERVER_HOST,SERVER_PORT);
    }

    public boolean connect(){
        try {
            socket = new Socket(SERVER_HOST,SERVER_PORT);

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        }
        catch (IOException e){
            System.out.println("No connection");
            e.printStackTrace();
            return false;
        }
    }
    public void close(){

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public DataOutputStream getDataOutputStream () {
        return dataOutputStream;
    }

    public DataInputStream getDataInputStream () {
        return dataInputStream;
    }

    public void waitMessega (Controller viewController) {
        Thread threadWait = new Thread(() -> {
            try{
                while (true){
                    String message = dataInputStream.readUTF();
                    viewController.appentMassege(message);
                }
            } catch (IOException e){
                e.printStackTrace();
                System.out.println("Error connection.");
            }
        });
        threadWait.setDaemon(true);
        threadWait.start();
    }
}