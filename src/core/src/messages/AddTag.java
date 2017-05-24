package messages;

import java.net.Socket;
import java.util.ArrayList;

import tools.Constants;
import tools.IOStreams;
import tools.Tools;
import beans.Association1;
import beans.Tag;
import beans.User;

import command.AddTagCommand;

/**
 * add a tag in the Tag table
 * 
 * @author PENG HUANG 
 * 
 */

public class AddTag {
	public static Tag addTag(String nomTag, User user )
	{

		Tag res = new Tag();
		/**
		 * Establishes the connection with the TCELL and send to file with the store message.
		 * @param fileToSendPath the path of the file to send
		 * @param user the addressee user
		 */
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
			
			AddTagCommand addTagCmd= new AddTagCommand(Constants.CMD_ADD_TAG, nomTag);
			//System.out.println(addTagCmd.getTagName());
			
			//send command
			stream.getOutputStream().writeObject(addTagCmd);
			
			//recieve status from the server
			int status = stream.getInputStream().readInt();
			//int status = stream.getInputStream().readInt();
			Tools.interpretStatus( status );
			
			res = (Tag) stream.getInputStream().readObject();
			//Tools.
			stream.close();
			socket.close();			
		} 
		catch (Exception ex) 
		{
			System.err.println("ERROR : add tag has failed");
		}
		
		return res;
	}


}
