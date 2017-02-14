import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
public class TCPServer implements Runnable{
 
	private ServerSocket server = null;
 
	public TCPServer(int port) {
		try {
			System.out.println("server port : "+port);
			server = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Server Socket Open error..............!!");
			System.err.println(port+" 포트는 이미 사용중 입니다.");
			System.exit(1); // 종료
		}
	}
 
	@Override
	public void run() {
 
		while (true) {
			try {
				Socket socket = server.accept();
				System.out.println("From: "+socket.getInetAddress());

				OutputStream out = socket.getOutputStream();
 

				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String line = in.readLine();
				System.out.println("Data : " + line);
 
				out.write("OK \n".getBytes());
				out.flush();
 
				out.close();
				in.close();
				socket.close();
 
			} catch (Exception e) {
				System.err.println(e);
			}			
 
		}
	}
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
 
		Thread server = new Thread(new TCPServer(10000));
		server.start();
	}
 
}