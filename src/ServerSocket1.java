import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket1 {



public static void main(String[] args) throws Exception{
    // TODO Auto-generated method stub

    // 8181포트 열림 연결 대기중
    ServerSocket serverSocket = new ServerSocket(8181);
    System.out.println("연결 대기 중");
    // 연결 대기 중

    Socket socket = serverSocket.accept();
    System.out.println("연결됨");
    new Thread(new ClientHandler(socket)).start();

    BufferedReader in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);



    while(true) {
        String clientmsg = in.readLine();
        System.out.println("client :" + clientmsg);
        out.flush();
        if(clientmsg.equals("quit")) {
            System.out.println("bye");
            break;
        }
        out.println(clientmsg);

    }


    out.close();
    in.close();

    socket.close();
    serverSocket.close();
}

}


class ClientHandler implements Runnable{
    private final Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}