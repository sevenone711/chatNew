package Server;


import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    private static final int SERVER_PORT = 8189;


    public static void main(String[] args){

         new EchoServer().startServer(); // Запускаем сервер.

    }

    private void startServer(){
        try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT)){
            System.out.println("Waiting connection");
            Socket clintSocet = serverSocket.accept();
            System.out.println("Connetion true");

            //Слушаем входящие и исходящие потоки
            DataInputStream in = new DataInputStream(clintSocet.getInputStream());
            DataOutputStream out = new DataOutputStream(clintSocet.getOutputStream());

            inMessage(in);
            outMassage(out);



        }
        catch (IOException e) {
            System.out.println("Error connection");
            e.printStackTrace();
        }

    }

    private Thread inMessage (DataInputStream in){
        Thread inputThread = new Thread(() ->
        {
            while (!Thread.currentThread().isInterrupted()){ //Если ловим иссключение прерываем цикл!

                try {
                    String inMassega = in.readUTF();
                    System.out.println("Massage User: " + inMassega);
                    if(inMassega.equals("/And")){
                        break;
                    }
                } catch (IOException e) {
                    System.out.println("Connection close");
                    e.printStackTrace();
                    break;
                }

            }
        }
        );
        inputThread.start();
        return inputThread;
    }
    private void outMassage(DataOutputStream out){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String outMassage = scanner.nextLine();
            try {
                out.writeUTF("Server: " + outMassage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(outMassage.equals("/And")){
                break;
            }
        }
    }

}
