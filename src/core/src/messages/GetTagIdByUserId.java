package messages;



import java.net.Socket;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.RETURN;



import tools.Constants;

import tools.IOStreams;
import tools.Tools;
import beans.FileStored;

import beans.User;

import command.Command;

import command.GetFileToShareCommand;

import command.GetTagIdByUserIdCommand;

import command.GetUserCommand;



/**

 * GetTagIdByUserId is able to get all the tag id with the same given userId

 * @author Majdi Ben Fredj,TOKRHNANI Rami

 */

public class GetTagIdByUserId {

	

	public static ArrayList<Integer> getTagIdByUserId(int IdUser, User myInfo) {

		/**

		 * getFile

		 * @param fileGID

		 * @param myInfo

		 */



		ArrayList<Integer> res = new ArrayList<Integer>();



		try {

			/* socket connection to my TCELL */

			Socket socket = new Socket(myInfo.getTCellIP(), myInfo.getPort());

			IOStreams stream = new IOStreams(socket);



			/* get the file from the TCELL */

			Command getTagIdByUserIdCommand = new GetTagIdByUserIdCommand(Constants.CMD_GET_TAG_ID_BY_USER_ID, IdUser);

			stream.getOutputStream().writeObject(getTagIdByUserIdCommand);

			int status = stream.getInputStream().readInt();

			Tools.interpretStatus( status );
			//System.out.println("status: " + status);
			res = (ArrayList<Integer>) stream.getInputStream().readObject();
			//System.out.println("res: " + res);
			

			stream.close();

			socket.close();

	

		} catch (Exception e) {

			e.printStackTrace();

		}

		return res;

	}

}

