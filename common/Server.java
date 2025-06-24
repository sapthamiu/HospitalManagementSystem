package common;
import java.net.*;
import java.io.*;
public class Server
{
	public String send, receive;
	public ServerSocket ss;
	public Socket s;
	public PrintStream ps;
	public BufferedReader br;
	public BufferedReader inp;
	public Server() throws IOException
	{
		ss = new ServerSocket(5001);
		s = ss.accept();
		System.out.println("Connection established");
		ps = new PrintStream(s.getOutputStream());
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		inp = new BufferedReader(new InputStreamReader(System.in));
	}
	public void sendMessage(String message) throws IOException
	{
		send = message;
		if(send != null)
			ps.println(send);
		else
			ps.println("Could not send message");
	}
	public String receiveMessage() throws IOException
	{
		receive = br.readLine();
		if(receive == null){
			System.out.println("Conversation ended by Patient");
			endServer();
		}
		return receive;

	}
	public void endServer() throws IOException
	{
		ps.close();
		br.close();
		inp.close();
		s.close();
		ss.close();
		System.exit(0);
	}
}