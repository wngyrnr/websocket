import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        Socket socket = new Socket("localHost",8181);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.print("1. client :");
            String clientmsg = sc.next();
            out.println(clientmsg);
            if(clientmsg.equals("quit")) {
                break;
            }
            String servermsg = in.readLine();
            System.out.println("responseMsg :" + servermsg);
        }

        out.close();
        in.close();
        socket.close();
    }


}
