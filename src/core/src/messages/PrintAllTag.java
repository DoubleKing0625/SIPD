package messages;

import java.net.Socket;
import java.util.ArrayList;

import tools.Constants;
import tools.IOStreams;
import tools.Tools;
import beans.Tag;
import beans.User;
import command.PrintAllTagCommand;

/**
 * PrintAllTag is able to print all tags
 * @author PENG, HUANG
 */

public class PrintAllTag
{
	//@SuppressWarnings("unchecked")
	public static ArrayList<Tag> printAllTag(User user)
    {

        /**
         * Establishes the connection with the TCELL and send to file with the store message.
         * @param fileToSendPath the path of the file to send
         * @param user the addressee user
         */
        ArrayList<Tag> result=null;

        try
        {
			/* The socket used to send the file and the messages */
            Socket socket;
			/* Creates a stream socket and connects it to the specified port number at the specified IP address */
            socket = new Socket( user.getTCellIP(), user.getPort() );

			/* Creation of the stream */
            IOStreams stream = new IOStreams( socket );

            PrintAllTagCommand printAllTagCmd= new PrintAllTagCommand(Constants.CMD_PRINT_ALL_TAG);

            //send command
            stream.getOutputStream().writeObject(printAllTagCmd);

            //recieve status from the server
            int status = stream.getInputStream().readInt();
            Tools.interpretStatus( status );
            
            result = (ArrayList<Tag>) stream.getInputStream().readObject();
            
            if(result.size()>0)
            {
            	
                for(Tag each : result)
                {
                    System.out.println( "IdGlobal:" + each.getTagID() + ", TagName: " + each.getTagName());
                    //System.out.println( each.getTagName());
                }
                
            	//System.out.println(result.size());
            }
            else
            {
                System.out.println("Table Tag empty");
            }
           

            //Tools.

            stream.close();
            socket.close();
        }
        catch (Exception ex)
        {
            System.err.println("ERROR : Impossible d'afficher la liste des Tags");
        }

        return result;
    }
}