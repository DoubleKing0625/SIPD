package messages;



import java.net.Socket;

import java.util.ArrayList;

import java.util.List;



import tools.Constants;

import tools.IOStreams;
import tools.Tools;
import beans.FileDesc;

import beans.User;

import command.GetFileDescByFileIdGlobalCommand;



/**
 * GetFileDescByFileIdGlobal provides the method to ask for the file's description stored on a TC. 
 * A file description consists of a fileGID, type and description.
 * 
 * @author  SHEN LIU
 */

public class GetFileDescByFileIdGlobal

{



	/**

	 * Get the files' descriptions with a specific tag

	 * @return the list of the files' description, as a String

	 */

	

	public static FileDesc getFileDescByFileIdGlobal(User user, int FileIdGlobal) {

		FileDesc fileDesc = new FileDesc() ;

		String TCellIP = user.getTCellIP();

		int TcellPort = user.getPort();



		try {

			Socket socket = new Socket(TCellIP, TcellPort);

			/* Creation of the stream */

			IOStreams stream = new IOStreams(socket);


			/* Send the command */


			GetFileDescByFileIdGlobalCommand getFileDescByFileIdGlobalCmd = new GetFileDescByFileIdGlobalCommand(Constants.CMD_GET_FILE_DESC_BY_FILE_IDGLOBAL, FileIdGlobal);

			stream.getOutputStream().writeObject(getFileDescByFileIdGlobalCmd);

			//System.out.println("status not");
			int status = stream.getInputStream().readInt();
			Tools.interpretStatus( status );
			//System.out.println("status: " + status);

			//if (status == Constants.GET_FILE_DESC_BY_FILE_IDGLOBAL_OK) {

			
			fileDesc = (FileDesc) stream.getInputStream().readObject();

			//System.out.println("Here is the description of the "+ fileDesc.fileGID);

			//System.out.println("FileGID : " + fileDesc.fileGID + " Type : " + fileDesc.type + " Description : " + fileDesc.descr);

			//} else {

				//System.err.println("An error when we return FileDesc by File IdGlobal");

			//}

			stream.close();

			socket.close();


		} catch (Exception ex) {

			System.err.println("An exception occured when we return FileDesc by File IdGlobal");

		}



		return fileDesc;

	}	

}

