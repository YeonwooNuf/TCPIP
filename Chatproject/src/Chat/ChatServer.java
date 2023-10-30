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


//1. ���� ���ϰ� Ŭ���̾�Ʈ ������ ���� ��Ʈ�� �����Ѵ�.
//2. Ŭ���̾�Ʈ�� ������ ������ ���� �ɾ��ش�.(accept �Լ��� ��� : ���������� while���� ���� �ִ�.)
//3. OS�� ��� ������ �����ʿ� ���� Ŭ���̾�Ʈ�� ������ Ȯ���Ѵ�.(accept �Լ� ȣ��)
//4. Ŭ���̾�Ʈ�� �����ϸ� Thread�� ���� �����Ͽ� ���� ���ϰ� �����ϰ� ���� ���ϰ��� ������ ���´�.
//5. Ŭ���̾�Ʈ�� ���Ͽ� ���� Buffer�� �޾� ���� ����� �� �ְ� �����.
//6. ���� ������ �� �ٸ� Ŭ���̾�Ʈ�� ����� ���� �� �ְ� �ȴ�.
public class ChatServer {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(9999); // Ŭ�� ���ϰ� ���� ��� (��Ʈ��ȣ 9999)
		Socket socket = serverSocket.accept();
		List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>());
		while(true) {	// ���ѷ����� Ŭ�� ���� ���
			
		System.out.println("����:"+ socket);
		ChatThread chatThread = new ChatThread(socket, outList);
		chatThread.start();	//ê������ ��ŸƮ (Ŭ��� ����� ���� ������ ����)
		}
	}

}
class ChatThread extends Thread{	//������ ��ӹ޾ƾ���
	
	private Socket socket;
	private List<PrintWriter> outList;
	private PrintWriter out;
	private BufferedReader in;
	
	
	public ChatThread(Socket socket, List<PrintWriter> outList) {	//������ �����
		this.socket = socket;
		this.outList = outList;
		
		try {
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())); //Ŭ������ �Ἥ �����ٰ�
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Ŭ�󿡰Լ� �о�ð�
			outList.add(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {	//������� RunnableŸ���̾���ϸ� �ݵ�� run �Լ��� ������ �־�� ��.
		// 1.�������κ��� �о���� �� �ִ� ��ü�� ��´�.(���� ����� Ŭ�󿡰� ���� ��ü.)
		// 2. ���Ͽ��� ���� ���� ��ü�� ��´�.
		
		// 3. Ŭ�󿡼� ���� �޽����� �д´�.
		// 4. ���ӵ� ��� Ŭ�󿡰� �޽����� ������.(���� ���ӵ� ��� Ŭ�󿡰� �� �� �ִ� ��ü�� �ʿ�.)
		String line = null;
		try {
			while((line = in.readLine()) != null) {
				for(int i=0; i< outList.size();i++) {//������ ��� Ŭ�󿡰� �޽����� ������.
					PrintWriter o = outList.get(i);
					o.println(line);
					o.flush();
				}
				
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {						//finally���� ������ �����ؾߴ� �ڵ带 �־��ش� //������ ���� ���� ��
			try {
			outList.remove(out);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}