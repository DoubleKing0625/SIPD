package messages;

import java.net.Socket;

import tools.Constants;
import tools.IOStreams;
import tools.Tools;
import beans.User;

import command.AddAssociation1Command;

/**
 * Add a connection between file and tag
 *
 * @author PENG HUANG 
 *
 */
public class AddAss1
{
    public static void addAss1(int IdFile, User user, int IdTag )
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
            AddAssociation1Command addAssociation1Cmd= new AddAssociation1Command(Constants.CMD_ADD_ASSOCIATION1, IdFile, IdTag);

            //send command
            stream.getOutputStream().writeObject(addAssociation1Cmd);
            //recieve status from the server
            int status = stream.getInputStream().readInt();
            Tools.interpretStatus( status );

            //Tools.

            stream.close();
            socket.close();
        }
        catch (Exception ex)
        {
            System.err.println("ERROR : Add Association1 has failed");
        }
    }
}