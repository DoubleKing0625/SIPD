package apps;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;

import tools.Constants;
import api.ClientAPI;
import beans.User;
import beans.Association1;
import beans.FileDesc;
import beans.Tag;
import configuration.Configuration;
import cryptoTools.KeyManager;


public class AppMain 
{
	/**
	 * APP Main
	 * 
	 * @author Majdi Ben Fredj
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int userGID = Integer.parseInt(Configuration.getConfiguration().getProperty("myGID"));
		String tCellIP = Configuration.getConfiguration().getProperty("myIP");
		int port = Integer.parseInt(Configuration.getConfiguration().getProperty("myPort"));
		String pubkey = null;
		User user= null;

		// load user PubKey
		try {
			String KeyPath = Configuration.getConfiguration().getProperty("keyPath");
			KeyManager keygen = new KeyManager();
			String publicKeyPath = KeyPath + Constants.PUB_KEY_PREFIX + userGID + Constants.KEY_EXT;
			PublicKey pubKey = keygen.LoadPublicKey(publicKeyPath, Constants.RSA_ALG);
			pubkey = keygen.PublicKeyToString(pubKey);

			user = new User(userGID, tCellIP, port, pubkey);

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		/*********************************** TEST STOREFILE ****************************/
		//FileDesc test = ClientAPI.storeFile("/home/user/Images/Image.jpeg", user);
		//FileDesc test2 = ClientAPI.storeFile("/home/user/Images/Logo_ENSIIE.png", user);
		//FileDesc test3 = ClientAPI.storeFile("/home/user/Images/NBA_logo.png", user);

		/*********************************** TEST GETFILEDESC **************************/
		//ClientAPI.getFileDesc(user);
		
		/*********************************** TEST READFILE *****************************/
		//ClientAPI.readFile("10|/home/user/SIPD/machines/Client10/TMP/Encrypted_Image.jpeg", user);
		
		/*********************************** TEST ADDTAG *******************************/
		//ClientAPI.addTag("Family", user);
		//ClientAPI.addTag("Work", user);
		
		/*********************************** TEST PrintAllTag **************************/
		//ClientAPI.printAllTag(user);
		
		/*********************************** TEST ADDASS1 ******************************/
		//idFile  user  idTag
		//ClientAPI.addAss1(1, user, 1);
		//ClientAPI.addAss1(2, user, 1);
		//ClientAPI.addAss1(3, user, 2);
		
		/*********************************** TEST PrintAllAss1 *************************/
		//ClientAPI.printAllAss1(user);
		
		/*********************************** TEST AddUser ******************************/
		//ClientAPI.addUser(userGID, tCellIP, port, pubkey, user);
		
		/*********************************** TEST ADDASS2 ******************************/
		//idUser  user  idTag
		//ClientAPI.addAss2(10, user, 1);
		//ClientAPI.addAss2(10, user, 2);
		//ClientAPI.printAllAss2(user);
		//ClientAPI.getFileDescByUserId(10, user);
		
		/*********************************** TEST DeleteAss2 ***************************/
		//idUser  user  idTag
		ClientAPI.deleteAss2(10, user, 2);
		ClientAPI.printAllAss2(user);
		//ClientAPI.getFileDescByUserId(10, user);
		
		/*********************************** TEST PrintAllAss2 *************************/
		//TEST PrintAllAss2
		//ClientAPI.printAllAss2(user);
		
		/*********************************** TEST GetFileDescByUserId ******************/
		//ClientAPI.getFileDescByUserId(10, user);
		
		System.out.println("Dooooone");
		
		/*********************************** FIN ***************************************/
		
	}
}