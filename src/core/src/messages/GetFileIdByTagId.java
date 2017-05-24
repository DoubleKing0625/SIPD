package messages;

import java.net.Socket;

import java.util.ArrayList;

import tools.Constants;
import tools.IOStreams;
import tools.Tools;

import beans.User;

import command.GetFileIdByTagIdCommand;



/**

 * GetFileIdByTagId is able to get all the file id with the same given Tag

 * @author PENG HUANG

 */

public class GetFileIdByTagId {

	

	public static ArrayList<Integer> getFileIdByTagId(int IdTag, User myInfo) {

		/**

		 * getFile

		 * @param IdTag

		 * @param myInfo

		 */



	    ArrayList<Integer> result=new ArrayList<Integer>();



		try {

			/* socket connection to my TCELL */

			Socket socket = new Socket(myInfo.getTCellIP(), myInfo.getPort());

			IOStreams stream = new IOStreams(socket);



			/* get the file from the TCELL */

			GetFileIdByTagIdCommand getFileIdByTagIdCmd = new GetFileIdByTagIdCommand(

					Constants.CMD_GET_FILE_ID_BY_TAG_ID, IdTag);

			stream.getOutputStream().writeObject(getFileIdByTagIdCmd);
			
			int status = stream.getInputStream().readInt();
            Tools.interpretStatus( status );
                      
			result = (ArrayList<Integer>) stream.getInputStream().readObject();

			

			stream.close();

			socket.close();

	

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;

	}

}


