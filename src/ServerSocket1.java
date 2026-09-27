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

// broadcast
// 생성된 ClientHandler정보를 가지고 있어야함.
// 어떤 자료구조를 사용해 clientHandler정보를 가지고 있어야하는가
// 일단 방은 하나로 잡고 시작 후 다음에 방을 분리함
// 예제에서는 concurrentHashMap를 사용해 방정보 + clientHandler를 사용함
// 필요조건
//1. 각 방에 대해 방정보 + client의 정보를 가지고 있어야함
//client들이 메시지를 보낼 때 해당하는 방에 있는 다른 client들에게 메시지를 보내주기 위해
//
//2. 어떤 자료구조를 사용할지 정해야함
//방 + client정보를 담기 위해 방을 key로 하고 client를 value로 하는 map구조를 사용
//3. 메시지를 보낼 때 내 메시지는 어떻게 처리할건지
//
//4. 메시지가 왔을 때 어떻게 해당 방에 있는 client들을 하나씩 꺼내 메시지를 보내줘야함.
//5. client가 접속을 종료할 때 해당 client의 정보를 지워야함.