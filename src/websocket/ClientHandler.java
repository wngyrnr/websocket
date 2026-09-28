package websocket;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientHandler implements Runnable{
    // 현재 접속중인 client 정보 저장
    private static final List<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    //serverSocket에서 accept 대기중 client가 연결시 run코드 실행
    @Override
    public void run() {
        try {
            // 메시지를 보내고 받기 위해 inputstream,outputstream 생성
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String clientmsg;
            // clients에 실행중인 Thread추가
            clients.add(this);
            while ((clientmsg = in.readLine()) != null) {
                System.out.println("client :" + clientmsg + socket.getLocalSocketAddress());
                broadCast(clientmsg);
                if (clientmsg.equals("quit")) {
                    System.out.println("bye");
                    broadCast(clientmsg);
                    // 종료시 clients에 현재 종료되는 Thread 삭제
                    clients.remove(this);
                    break;
                }
                send(clientmsg);
            }
            out.close();
            in.close();

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public void broadCast(String message){
        for(ClientHandler c: clients){
            if(c == this) continue;
            c.send(message);
        }
    }

    public synchronized void send(String message){
        out.println(message);
    }
}
