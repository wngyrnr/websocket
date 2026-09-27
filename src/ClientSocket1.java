import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket1 {

    public static void main(String[] args) throws Exception{
        // 소켓 생성
        Socket socket = new Socket("localHost",8181);

        // 메시지를 받기 위해 inputstream 생성
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 메시지를 보내기 위한 outputstream 생성
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // 메시지를 받을 scanner생성
        Scanner sc = new Scanner(System.in);

        //반복해서 메시지를 보낼 로직
        while(true) {
            System.out.print("2. client :");
            String clientmsg = sc.next();
            out.println(clientmsg);
            if(clientmsg.equals("quit")) { // quit를 보내면 그 즉시 while탈출 , 연결끊음
                break;
            }
            // 서버에서 돌아오는 메시지를 받음
            String servermsg = in.readLine();
            System.out.println("responseMsg :" + servermsg);
        }
        // while문 탈출시 모든 자원 반납
        out.close();
        in.close();
        socket.close();
    }


}
