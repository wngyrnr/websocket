import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket3 {

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        Socket socket = new Socket("localHost",8181);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Thread receiver = new Thread(() -> {
            String line;
            try {
                while ((line = in.readLine()) != null) {
                    System.out.println("\n[3.client :] " + line);
                }
            } catch (IOException e) {
                System.err.println("연결 종료: " + e.getMessage());
            }
        });
        receiver.start();
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("3. client :");
            String clientmsg = sc.next();
            out.println(clientmsg);

            if(clientmsg.equals("quit")) {
                break;
            }


        }

        out.close();
        in.close();
        socket.close();
    }


}
