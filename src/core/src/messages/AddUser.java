package messages;

import java.net.Socket;


import tools.Constants;
import tools.IOStreams;
import tools.Tools;
import beans.Association1;
import beans.User;

import command.AddUserCommand;

/**
 * add a user in the User table
 * 
 * @author PENG HUANG 
 * 
 */

public class AddUser {
	public static User addUser(int userGID, String tcellIP, int tcellPort, String pubKey, User user)
	{

		User res = new User();
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
			
			AddUserCommand addUserCmd= new AddUserCommand(Constants.CMD_ADD_USER, userGID, tcellIP, tcellPort, pubKey);
			//System.out.println(addTagCmd.getTagName());
			
			//send command
			stream.getOutputStream().writeObject(addUserCmd);
			
			//recieve status from the server
			int status = stream.getInputStream().readInt();
			//int status = stream.getInputStream().readInt();
			Tools.interpretStatus( status );
			
			res = (User) stream.getInputStream().readObject();
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
