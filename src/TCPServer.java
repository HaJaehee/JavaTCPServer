import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
 
public class TCPServer {
	public static void main(String[] args) {	
		
		int port = 10001;
		ServerSocket server;
		try {
			server = new ServerSocket(port);
			while (true) {
				Socket sock = server.accept();
				System.out.println("From: "+sock.getInetAddress());

				BufferedReader in = new BufferedReader(new InputStreamReader(
						sock.getInputStream(),Charset.forName("UTF-8")));
				
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						sock.getOutputStream(),Charset.forName("UTF-8")));
				
				String inputLine = in.readLine();
				System.out.println("Data : " + inputLine);
 
				out.write("OK \n");
				out.flush();
 
				out.close();
				in.close();
				sock.close();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}			

	}
 
}