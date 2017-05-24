package messages;

import java.net.Socket;

import tools.Constants;
import tools.IOStreams;
import tools.Tools;
import beans.User;

import command.DeleteAssociation2Command;

/**
 * Delete a connection between user and tag
 *
 * @author PENG HUANG 
 *
 */
public class DeleteAss2
{
    public static void deleteAss2(int IdUser, User user, int IdTag )
    {

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
            DeleteAssociation2Command deleteAssociation2Cmd= new DeleteAssociation2Command(Constants.CMD_DELETE_ASSOCIATION2, IdUser, IdTag);

            //send command
            stream.getOutputStream().writeObject(deleteAssociation2Cmd);
            //recieve status from the server
            //System.out.println("Before getting the status");
            int status = stream.getInputStream().readInt();
            //System.out.println("After getting the status");
            Tools.interpretStatus( status );

            //Tools.

            stream.close();
            socket.close();
        }
        catch (Exception ex)
        {
            System.err.println("ERROR : Delete an association2 has failed");
        }
    }
}