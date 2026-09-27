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
        try {
            while (true) {
                //연결 대기중
                Socket socket = serverSocket.accept();
                System.out.println("연결됨");
                // 통신을 위한 새로운 스레드 생성
                new Thread(new ClientHandler(socket)).start();
            }
        }finally {
            serverSocket.close();
        }
    }

}