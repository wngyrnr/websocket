public class ThreadTest {

    public static void main(String[] args){

        Thread thread = new Thread(() -> {
            for(int i = 0; i<5; i ++){
                System.out.println("Thread :" + i);
            }
        });
        thread.start();
        // thread.start() : 새로운 thread 시작
        // 새로운 스레드를 위한 전용 메모리 공간 할당
        // thread.run() : 스레드 매서드 실행
        // 새 스레드 생성x main스레드가 직접 run() 코드 실행

        for(int i = 0; i<5; i++){
            System.out.println("main" + i);
        }

    }

}
