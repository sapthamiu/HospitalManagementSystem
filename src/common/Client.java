package src.common;
import java.io.*;
import java.net.*;
public class Client
{
	public String send, receive;
	public DataOutputStream dos;
	public Socket s;
	public BufferedReader br;
	public BufferedReader inp;
    public Client() throws IOException
    {
        s = new Socket("localhost", 5001);
		dos = new DataOutputStream(s.getOutputStream());
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		inp = new BufferedReader(new InputStreamReader(System.in));
    }
	public void sendMessage(String message) throws IOException
	{
		send = message;
		if(send == null)
			dos.writeBytes("Could not send message");
		else
			dos.writeBytes(send + "\n");
	}
	public String receiveMessage() throws IOException
	{
		receive = br.readLine();
		if(receive == null){
			System.out.println("Conversation ended by Doctor");
			endClient();
		}
		return receive;
	}
	public void endClient() throws IOException
	{
		dos.close();
		br.close();
		inp.close();
		s.close();
	}
}