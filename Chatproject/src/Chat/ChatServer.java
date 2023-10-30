package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//1. 서버 소켓과 클라이언트 소켓이 같은 포트에 연결한다.
//2. 클라이언트가 접속할 때까지 락을 걸어준다.(accept 함수를 사용 : 내부적으로 while문이 돌고 있다.)
//3. OS의 상시 감시자 리스너에 의해 클라이언트의 접속을 확인한다.(accept 함수 호출)
//4. 클라이언트가 접속하면 Thread를 새로 생성하여 서브 소켓과 연결하고 서버 소켓과의 연결은 끊는다.
//5. 클라이언트와 소켓에 각각 Buffer를 달아 서로 통신할 수 있게 만든다.
//6. 서버 소켓은 또 다른 클라이언트의 통신을 받을 수 있게 된다.
public class ChatServer {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(9999); // 클라 소켓과 연결 대기 (포트번호 9999)
		Socket socket = serverSocket.accept();
		List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>());
		while(true) {	// 무한루프로 클라 연결 대기
			
		System.out.println("접속:"+ socket);
		ChatThread chatThread = new ChatThread(socket, outList);
		chatThread.start();	//챗쓰레드 스타트 (클라와 통신을 위한 쓰레드 시작)
		}
	}

}
class ChatThread extends Thread{	//쓰레드 상속받아야함
	
	private Socket socket;
	private List<PrintWriter> outList;
	private PrintWriter out;
	private BufferedReader in;
	
	
	public ChatThread(Socket socket, List<PrintWriter> outList) {	//생성자 만들기
		this.socket = socket;
		this.outList = outList;
		
		try {
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())); //클라한테 써서 보내줄거
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 클라에게서 읽어올거
			outList.add(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {	//쓰레드는 Runnable타입이어야하며 반드시 run 함수를 가지고 있어야 함.
		// 1.소켓으로부터 읽어들일 수 있는 객체를 얻는다.(현재 연결된 클라에게 쓰는 객체.)
		// 2. 소켓에게 쓰기 위한 객체를 얻는다.
		
		// 3. 클라에서 보낸 메시지를 읽는다.
		// 4. 접속된 모든 클라에게 메시지를 보낸다.(현재 접속된 모든 클라에게 쓸 수 있는 객체가 필요.)
		String line = null;
		try {
			while((line = in.readLine()) != null) {
				for(int i=0; i< outList.size();i++) {//접속한 모든 클라에게 메시지를 전송함.
					PrintWriter o = outList.get(i);
					o.println(line);
					o.flush();
				}
				
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {						//finally에는 무조건 실행해야는 코드를 넣어준다 //접속이 끊어 졌을 때
			try {
			outList.remove(out);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}