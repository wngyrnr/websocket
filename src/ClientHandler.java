import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    //serverSocket에서 accept 대기중 client가 연결시 run코드 실행
    @Override
    public void run() {
        try {
            // 메시지를 보내고 받기 위해 inputstream,outputstream 생성
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            {
                String clientmsg;
                while ((clientmsg = in.readLine()) != null) {
                    System.out.println("client :" + clientmsg + socket.getLocalSocketAddress());
                    if (clientmsg.equals("quit")) {
                        System.out.println("bye");
                        break;
                    }
                    out.println(clientmsg);
                }
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}