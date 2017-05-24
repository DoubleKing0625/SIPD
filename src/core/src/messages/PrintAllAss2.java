package messages;

import java.net.Socket;
import java.util.ArrayList;

import tools.Constants;
import tools.IOStreams;
import tools.Tools;
import beans.Association2;
import beans.User;
import command.PrintAllAssociation1Command;
import command.PrintAllAssociation2Command;

//import command.GetAllRulesCommand;
/**
 * PrintAllAss2 is able to print all association2
 * @author PENG, HUANG
 */

public class PrintAllAss2
{	
	public static ArrayList<Association2> printAllAss2(User user)
	{

		/**
		 * Establishes the connection with the TCELL and send to file with the store message.
		 * @param fileToSendPath the path of the file to send
		 * @param user the addressee user
		 */
		ArrayList<Association2> result = null;
		
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
			
			PrintAllAssociation2Command printAllAssociation2Cmd= new PrintAllAssociation2Command(Constants.CMD_Print_ALL_ASSOCIATION2);

			//send command
			stream.getOutputStream().writeObject(printAllAssociation2Cmd);
			
			//recieve status from the server
			int status = stream.getInputStream().readInt();
			//System.out.println(status);
			Tools.interpretStatus( status );
			
			result=(ArrayList<Association2>) stream.getInputStream().readObject();
			
			
			
			if(result.size()>0)
			{	
				for(Association2 each : result)
				{
					System.out.println("IDGlobal: " + each.getAssociation2ID()+", IdTag: "+ each.getAssociation2TagId()+", IdUser: "+each.getAssociation2UserId());
				}
				//System.out.println(result.size());
			}
			else
			{
				//System.out.println(result.size());
				System.out.println("Association2 table empty");
			}
			//System.out.println(result.size());

			
			
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