package Chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;

public class ChatClient {


	public static void main(String[] args) throws Exception {
		String name = args[0];
		args[0] = "park"; 
		Socket socket = new Socket("127.0.0.1", 9999);
		
		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream())); //클라한테 써서 보내줄거
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 클라에게서 읽어올거
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // 클라에게서 읽어올거
		
		String line = null;
		InputThread inputThread = new InputThread(in);
		inputThread.start();
		
		
		while((line = keyboard.readLine()) != null) {
			out.println(name + ":" + line);
			out.flush();
		}
		
	}
}
class InputThread extends Thread{
	BufferedReader in = null;
	public InputThread(BufferedReader in) {
		this.in = in;
	}
	@Override
	public void run() {
		try {
			String line = null;
			while((line = in.readLine())!=null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
