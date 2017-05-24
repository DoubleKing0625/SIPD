package messages;

import java.net.Socket;
import java.util.ArrayList;

import tools.Constants;
import tools.IOStreams;
import tools.Tools;
import beans.Association1;
import beans.User;
import command.PrintAllAssociation1Command;

//import command.GetAllRulesCommand;

/**
 * PrintAllAss1 is able to print all association1
 * @author PENG, HUANG
 */
public class PrintAllAss1
{	
	public static ArrayList<Association1> printAllAss1(User user)
	{

		/**
		 * Establishes the connection with the TCELL and send to file with the store message.
		 * @param fileToSendPath the path of the file to send
		 * @param user the addressee user
		 */
		ArrayList<Association1> result = null;
		
		try 
		{
			/* The socket used to send the file and the messages */
			Socket socket;

			String IP = user.getTCellIP();
			int Port = user.getPort();

			/* Creates a stream socket and connects it to the specified port number at the specified IP address */
			socket = new Socket( IP, Port );

			/* Creation of the stream */
			IOStreams stream = new IOStreams( socket );
			
			PrintAllAssociation1Command printAllAssociation1Cmd= new PrintAllAssociation1Command(Constants.CMD_Print_ALL_ASSOCIATION1);

			//send command
			stream.getOutputStream().writeObject(printAllAssociation1Cmd);
			
			//recieve status from the server
			int status = stream.getInputStream().readInt();
			Tools.interpretStatus( status );
			
			result=(ArrayList<Association1>) stream.getInputStream().readObject();
			
			
			
			if(result.size()>0)
			{	
				for(Association1 each : result)
				{
					System.out.println("Idglobal: " + each.getAssociation1ID()+", IdTag: "+each.getAssociation1TagId()+", IdFile: "+each.getAssociation1FileId());
				}
				//System.out.println(result.size());
			}
			else
			{
				System.out.println("Association1 table empty");
			}			
			
			//Tools.
			
			stream.close();
			socket.close();
		} 
		catch (Exception ex) 
		{
			System.err.println("ERROR : print Association1 has failed");
		}
		
		return result;
	}
}