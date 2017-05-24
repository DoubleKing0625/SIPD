package dao;

import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.inria.database.QEPng;

import test.jdbc.Tools;
import beans.Association1;
import beans.Association2;
import beans.FileDesc;
import beans.MyInfo;
import beans.Tag;
import beans.User;
import configuration.Configuration;



/**
 * 
 * @author Majdi Ben Fredj
 * 
 *  * the TCell DB Manager.

 */
public class TcellDAOToken extends Tools{
	private static int fileIdGlobal = 1;
	private static int tagIdGlobal = 1;
	private static int association1IdGlobal = 1;
	private static int association2IdGlobal = 1;

	private static TcellDAOToken instance = null;
	
	
	// Renvoyer une instance de TcellDAOToken
		public static TcellDAOToken getInstance(boolean ignoreLoadQEP) {
			/**
			 * TcellDAOToken Creates a TCELL_DB instance
			 * @param ignoreLoadQEP
			 */

			if (instance == null) {
				synchronized (TcellDAOToken.class) {
					if (instance == null) {
						instance = new TcellDAOToken(ignoreLoadQEP);
					}
				}
			}
			return instance;
		}

	//Load JDBC driver, get a connexion, without installing execution plans
	
	public TcellDAOToken(boolean ignoreLoadQEP) {
		try {
			out = new PrintWriter(java.lang.System.out);
		    init();
			openConnection(Configuration.getConfiguration().getProperty("dbPath"), null);
			
			((org.inria.jdbc.Connection) db).bypassInitialization();
			
			Class<?>[] executionPlans = new Class[] { QEP.class };
			QEPng.loadExecutionPlans(TCell_QEP_IDs.class, executionPlans);
			if(!ignoreLoadQEP){
				QEPng.installExecutionPlans(db);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	public void DropTables() {
		/**
		 * Drop the tables of DB.
		 */
		try {
			Desinstall_DBMS_MetaData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void CreateTables() {
		/**
		 * Creates the tables in DB.
		 * 
		 */
		try {
			String schema = Schema.META;
			Install_DBMS_MetaData(schema.getBytes(),0);

			// load and install QEPs
			Class<?>[] executionPlans = new Class[] { QEP.class };
			QEPng.loadExecutionPlans(TCell_QEP_IDs.class, executionPlans);
			QEPng.installExecutionPlans(db);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserts a file's description
	 * 
	 * @param string
	 *            the global id of the file
	 * @param fileID
	 *            the file id of the file
	 * @param sKey
	 *            the secret key used to encrypt the file
	 * @param iv
	 *            the initialization vector
	 * @param type
	 *            the type of the file (store or share)
	 * @param descr
	 *            the description of the file
	 * @throws Exception
	 */
	public FileDesc insertFileDesc(String fileGID, String fileID, String sKey, String iv, String type, String descr) {

		FileDesc res = new FileDesc();
		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_insertFileDesc);
			ps.setInt(1, fileIdGlobal++);
			ps.setString(2, fileGID);
			ps.setString(3, fileID);
			ps.setString(4, sKey);
			ps.setString(5, iv);
			ps.setString(6, type);
			ps.setString(7, descr);
			ps.executeUpdate();
			
			res = new FileDesc(fileIdGlobal-1, fileGID, fileID, sKey, iv, type, descr);
			String query= "INSERT INTO File  VALUES ("+ fileGID+","+fileID+","+sKey+","+iv+","+type+","+descr+")";
			System.out.println("Executing query : " + query);			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}
		return res;

	}
	/**
	 * Inserts a user in the User table
	 * @param UserGID the user id
	 * @param TCellIP the TC's IP of the user
	 * @param PubKey the public key of the user
	 * @param tcellPort the TC's PORT of user
	 * @throws Exception
	 */

	public User insertUser(int userGID, String tcellIP, int tcellPort, String pubKey) {
		
		User res = new User();
		
		try {

			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_insertUser);

			ps.setInt(1, userGID);
			ps.setString(2, tcellIP);
			ps.setInt(3, tcellPort);
			
			//convert String to blob
			Blob pubKeyBlob = db.createBlob();
			pubKeyBlob.setBytes(1, pubKey.getBytes());
			ps.setBlob(4, pubKeyBlob);
			
			int a=ps.executeUpdate();
			System.out.println(a + "utilisateur est ajouté");
			String query= "INSERT INTO User  VALUES ("+userGID+","+  tcellIP +","+tcellPort +","+pubKey+")";
			System.out.println("Executing query : " + query);	
			res = new User(userGID, tcellIP, tcellPort, pubKey);


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}
		return res;
	}
	/**
	 * Deletes a file in the db
	 * @param fileGID the fileGID of the file
	 * @throws Exception
	 */
	public void deleteFileDescByFileGid(String fileGID) {
		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_deleteFileDescByFileGid);
			ps.setString(1, fileGID);
			ps.executeUpdate();
			
			String query = "DELETE FROM File WHERE FILEGID ="+ fileGID+")";
			System.out.println("Executing query : " + query);			

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}

	}
	/**
	 * Deletes a user from the db
	 * @param userGID the userGID of the removed user
	 * @throws Exception
	 */
	public void deleteUserByUserGID(int userGID) {
		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_deleteUserByUserGid);

			ps.setInt(1, userGID);
			ps.executeUpdate();
			
			String query = "DELETE FROM User WHERE IdGlobal ="+ userGID+")";
			System.out.println("Executing query : " + query);			

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}

	}
	/**
	 * check a file exist
	 * @param fileGID the fileGID of the file to check
	 * @throws Exception
	 */
	public boolean isFileGIDexists(String fileGID) {
		boolean result = false;

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_isFileGidExists);

			ps.setString(1, fileGID);
			ResultSet rs = ps.executeQuery();
			
			String query = "SELECT IdGlobal, FILEGID FROM File WHERE FILEGID = " + fileGID +")";
			System.out.println("Executing query : " + query);			

			
			if (rs.next()) {
				result = true;
			} else {
				result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}
		return result;
	}
	
	
	
	/**
	 * Select USERGID, TCELLIP, PUBLICKEY from USER WHERE USERGID = 'userGID';
	 * @param userGID the user ID
	 * @return List<IP>
	 *    List of IPs <USERGID, TCELLIP, PUBLICKEY>
	 */
	public User getUserById( int userGID ){
		User user = null;

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getUserById);
			ps.setInt(1, userGID);
			ResultSet rs = ps.executeQuery();
			
			String query = "SELECT IdGlobal, TCELLIP, PUBLICKEY,TCELLPORT FROM USER WHERE IdGlobal = "+ userGID +")";
			System.out.println("Executing query : " + query);			

			
			//Retrieve TCellIP  and PublicKey from USER table in TCellDB
			while (rs.next()) {
				int UserGID =rs.getInt(1);
				String TCellIP = rs.getString(2);
				
				//convert Blob to String
				Blob myPubKeyBlob = rs.getBlob(3);
				byte[] bPubKey = myPubKeyBlob.getBytes(1, (int) myPubKeyBlob.length());
				String pubKey = new String(bPubKey);
				
				int port = rs.getInt(4);
				user = new User(UserGID,TCellIP, port, pubKey);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}

		return user;                      
	}
	
	
	/**
	 * SELECT FILEID, SECRETKEY, IV FROM FILE WHERE FILEGID = 'fileGID';
	 * @param fileGID the file ID
	 * @return FileDesc 
	 *    The File description <FILEID, SECRETKEY, IV>
	 */
	public FileDesc getFileDescByGid(String fileGID) {
		FileDesc result = null;

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getFileDescByGid);
			ps.setString(1, fileGID);
			ResultSet rs = ps.executeQuery();

			String query = "SELECT FILEID, SECRETKEY, IV FROM FILE WHERE FILEGID ="+ fileGID +")";
			System.out.println("Executing query : " + query);			


			while (rs.next()) {
				String fileID = rs.getString(1);
				String sKey = rs.getString(2);
				String iv = rs.getString(3);
				result = new FileDesc(fileGID,fileID, sKey, iv,"","");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);

		}

		return result;
	}
	/**
	 * Select USERGID, TCELLIP, PUBLICKEY from USER ;
	 * 
	 * @return List<IP>
	 *    List of IPs <USERGID, TCELLIP, PUBLICKEY>
	 */
	public List<Integer> getAllUsersGID() {

		List<Integer> result = new ArrayList<Integer>();

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllUsersGid);
			ResultSet rs = ps.executeQuery();
			String query = "SELECT IdGlobal from USER";
			System.out.println("Executing query : " + query);			

			while (rs.next()) {
				result.add(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
			
		}

		return result;

	}


	/**
	 * SELECT FILEID, SECRETKEY, IV FROM FILE;
	 * @param fileGID the file ID
	 * @return FileDesc 
	 *    The File description <FILEID, SECRETKEY, IV>
	 */
	public ArrayList<FileDesc> getAllFiles() {

		ArrayList<FileDesc> result = new ArrayList<FileDesc>();

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllFiles);
			ResultSet rs = ps.executeQuery();
			
			String query = "SELECT FILEGID, TYPE, DESCRIPTION FROM FILE";
			System.out.println("Executing query : " + query);			

			
			while (rs.next() == true) {
				String fileGID = rs.getString(1);
				String type = rs.getString(2);
				String Description = rs.getString(3);
				result.add(new FileDesc(fileGID, "", "", "", type, Description));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
			
		}

		return result;
	}
	
	/**
	 * SELECT IdGlobal, MYTCELLIP, MYTCELLPORT, MYPUBLICKEY, MYPRIVATEKEY FROM MyInfo;
	 * 
	 * @return MyInfo 
	 *    MyInfo <myGid, myTcellIp, myTcellPort, myPubKey, myPrivKey>
	 */
	
	public MyInfo getMyInfo() {
		
		MyInfo myInfo = null;
		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getMyInfo);
			ResultSet rs = ps.executeQuery();

			String query = "SELECT IdGlobal, MYTCELLIP, MYTCELLPORT, MYPUBLICKEY, MYPRIVATEKEY FROM MyInfo";
			System.out.println("Executing query : " + query);

			while (rs.next()) {

				int myGid = rs.getInt(1);
				String myTcellIp = rs.getString(2);
				int myTcellPort = rs.getInt(3);
				
				//convert Blob to String
				Blob myPubKeyBlob = rs.getBlob(4);
				byte[] bPubKey = myPubKeyBlob.getBytes(1, (int) myPubKeyBlob.length());
				String myPubKey = new String(bPubKey);
				
				//convert Blob to String
				Blob myPrivKeyBlob = rs.getBlob(5);
				byte[] bPrivKey = myPrivKeyBlob.getBytes(1, (int) myPrivKeyBlob.length());
				String myPrivKey = new String(bPrivKey);
				
				myInfo = new MyInfo(myGid, myTcellIp, myTcellPort, myPubKey, myPrivKey);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}

		return myInfo;
	}
	/**
	 * Inserts my informations in the MyInfo table
	 * @param myGID the user id
	 * @param myTcellIp the TC's IP of the user
	 * @param myPubKey the public key of the user
	 * @param myPrivKey the private key of the user
	 **/
	public void insertMyInfo(int myGid, String myTcellIp, int myTcellPort, String myPubKey,  String myPrivKey) {

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_insertMyInfo);
			ps.setInt(1, myGid);
			ps.setString(2, myTcellIp);
			ps.setInt(3, myTcellPort);
			
			//convert String to blob
			Blob pubKeyBlob = db.createBlob();
			pubKeyBlob.setBytes(1, myPubKey.getBytes());
			ps.setBlob(4, pubKeyBlob);
			
			//convert String to blob
			Blob privKeyBlob = db.createBlob();
			privKeyBlob.setBytes(1, myPrivKey.getBytes());
			ps.setBlob(5, privKeyBlob);
			
			
			ps.executeUpdate();
			
			String query= "INSERT INTO MyInfo  VALUES ("+ myGid+","+myTcellIp+","+myTcellPort+","+myPubKey+","+myPrivKey+")";
			System.out.println("Executing query : " + query);			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}

	}
	
	
	/**********************************************Log data****************************************************************/
	public void printAllFiles() {

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllFiles);
			ResultSet rs = ps.executeQuery();
			
			String query = "SELECT FILEGID, TYPE, DESCRIPTION FROM FILE";
			System.out.println("Executing query : " + query);			

			Tools.lireResultSet(rs , out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
			
		}
	}

	
	
	public void printAllUsers() {

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllUsers);
			ResultSet rs = ps.executeQuery();
			
			String query = "SELECT IdGlobal, TCELLIP, PUBLICKEY,TCELLPORT FROM User";
			System.out.println("Executing query : " + query);			

			lireResultSet(rs , out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
			
		}
	}
	
	public void printMyInfo() {

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getMyInfo);
			ResultSet rs = ps.executeQuery();
			
			String query = " SELECT IdGlobal, MYTCELLIP, MYTCELLPORT, MYPUBLICKEY, MYPRIVATEKEY FROM MyInfo ";
			System.out.println("Executing query : " + query);			

			lireResultSet(rs,out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
			
		}
	}
	
	
	public void printAllFilesDesc() {

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllFilesDesc);
			ResultSet rs = ps.executeQuery();
			
			String query = "SELECT * FROM FILE";
			System.out.println("Executing query : " + query);			

			lireResultSet(rs,out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	
	
	/*************************************par PENG HUANG SHEN LIU*************************************************/
	
	/**
	 * Judge if tag has already existed
	 * @param TagName: the name of tag
	 * @throws Exception
	 */
	public boolean isTagExists(String TagName){
		boolean res = false;
		try{
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getTagIdFromTagname);
			ps.setString(1, TagName);
			ResultSet rs = ps.executeQuery();
			
			String query = "SELECT IdGlobal FROM Tag WHERE TagName = " + TagName;
			System.out.println("Excuting query: " + query);
			
			if (rs.next()){
				res = true;
			}
			else{
				res = false;
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally{
			//attempToClose(ps);
		}
		return res;
	}

	/**
	 * Inserts a tag
	 * 
	 * @param tagname: the name of tag
	 * @throws Exception
	 */

	public Tag insertTag(String TagName) {
		
		Tag res = new Tag();
		try {

			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_insertTag);

			ps.setInt(1, tagIdGlobal++);
			ps.setString(2, TagName);
		    
			ps.executeUpdate();
			System.out.println("Insert a tag into Tag table successful");
			String query= "INSERT INTO TAG  VALUES ("+ (tagIdGlobal-1) +","+ TagName + ")";
			System.out.println("Executing query : " + query);	
			res = new Tag(tagIdGlobal-1, TagName);
			/*
			java.sql.PreparedStatement ps1 = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getTagIdFromTagname);
			ps1.setString(1, TagName);
			ResultSet rs = ps1.executeQuery();
			while(rs.next()){
				int Id = rs.getInt(1);
				String Name = rs.getString(2);
				System.out.println(Id + " " + Name);
			}
			
			Thread.sleep(2000000000);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}
		return res;
	}
	
	/**
	 * Print all tags
	 * @throws Exception
	 */
	public ArrayList<Tag> printAllTags() {
		
		ArrayList<Tag> res = new ArrayList<Tag>();

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllTags);
			ResultSet rs = ps.executeQuery();
			
			String query = "SELECT IdGlobal, TagName FROM Tag";
			System.out.println("Executing query : " + query);			

			//lireResultSet(rs , out);
			
			while(rs.next() == true){
				int IdTag = rs.getInt(1);
				String TagName = rs.getString(2);
				res.add(new Tag(IdTag, TagName));				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
			
		}
		return res;
	}
	
	
	/**
	 * Inserts an association1 in the Association1 table
	 * @param IdFile the Id of File
	 * @param IdTag  the Id of Tag
	 * @throws Exception
	 */

	public void insertAssociation1(int IdFile, int IdTag) {
		try {

			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_insertAss1);

			ps.setInt(1, association1IdGlobal++);
			ps.setInt(2, IdFile);
			ps.setInt(3, IdTag);
		
			ps.executeUpdate();
			System.out.println("Insert an association between File and Tag successfully");
			String query= "INSERT INTO Association1  VALUES ("+ (association1IdGlobal-1) +","+  IdFile +","+IdTag +")";
			System.out.println("Executing query : " + query);			
			/*
			// test
			java.sql.PreparedStatement ps1 = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllAss1);
			ResultSet rs = ps1.executeQuery();
			
			String query1 = "SELECT IdGlobal, IdFile, IdTag FROM Association1";
			System.out.println("Executing query : " + query1);			

			//lireResultSet(rs , out);
			
			while(rs.next() == true){
				int Id1 = rs.getInt(1);
				int Id2 = rs.getInt(2);
				int Id3 = rs.getInt(3);
				System.out.println(Id1 + Id2 + Id3);
			}
			
			Thread.sleep(200);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}

	}
	
	/**
	 * Print all association1s
	 * @throws Exception
	 */
	
	public ArrayList<Association1> printAllAss1() {
		
		ArrayList<Association1> res = new ArrayList<Association1>();

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllAss1);
			ResultSet rs = ps.executeQuery();
			
			String query = "SELECT IdGlobal, IdFile, IdTag FROM Association1";
			System.out.println("Executing query : " + query);			

			//lireResultSet(rs , out);
			
			while(rs.next()){
				int IdGlobal = rs.getInt(1);
				int IdFile  = rs.getInt(2);
				int IdTag = rs.getInt(3);
				res.add(new Association1(IdGlobal, IdTag, IdFile));				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
			
		}
		return res;
	}
	
	/**
	 * Inserts an association2 in the Association2 table
	 * @param IdUser the Id of User
	 * @param IdTag  the Id of Tag
	 * @throws Exception
	 */

	public void insertAssociation2(int IdUser, int IdTag) {
		try {

			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_insertAss2);

			ps.setInt(1, association2IdGlobal++);
			ps.setInt(2, IdUser);
			ps.setInt(3, IdTag);
			ps.executeUpdate();
			System.out.println("Insert an association between File and Tag successfully");
			String query= "INSERT INTO Association2  VALUES ("+ (association2IdGlobal-1) +","+  IdUser +","+IdTag +")";
			System.out.println("Executing query : " + query);			
			/*
			// test
			java.sql.PreparedStatement ps1 = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllAss1);
			ResultSet rs = ps1.executeQuery();
			
			String query1 = "SELECT IdGlobal, IdFile, IdTag FROM Association1";
			System.out.println("Executing query : " + query1);			

			//lireResultSet(rs , out);
			
			while(rs.next() == true){
				int Id1 = rs.getInt(1);
				int Id2 = rs.getInt(2);
				int Id3 = rs.getInt(3);
				System.out.println(Id1 + Id2 + Id3);
			}
			
			Thread.sleep(200);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}
	}
	
	/**
	 * Delete an association2 in the Association2 table
	 * @param IdUser the IdGlobal of user
	 * @param IdTag  the IdGlobals of Tag
	 * @throws Exception
	 */

	public void deleteAssociation2(int IdUser, int IdTag) {
		try {

			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.Ep_deleteAssociation2);
			
			ps.setInt(1, IdUser);
			ps.setInt(2, IdTag);
			//System.out.println("Before executing the command");
			//Thread.sleep(900000000);
			ps.executeUpdate();
			System.out.println("Delete an association2 successfully");
			String query= "DELETE FROM Association2 WHERE IdUser = "+ IdUser +"AND" + "IdTag = " + IdTag;
			System.out.println("Executing query : " + query);			
			/*
			// test
			java.sql.PreparedStatement ps1 = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllAss1);
			ResultSet rs = ps1.executeQuery();
			
			String query1 = "SELECT IdGlobal, IdFile, IdTag FROM Association1";
			System.out.println("Executing query : " + query1);			

			//lireResultSet(rs , out);
			
			while(rs.next() == true){
				int Id1 = rs.getInt(1);
				int Id2 = rs.getInt(2);
				int Id3 = rs.getInt(3);
				System.out.println(Id1 + Id2 + Id3);
			}
			
			Thread.sleep(200);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
		}

	}
	/**
	 * Print all association2s
	 * @throws Exception
	 */
	
public ArrayList<Association2> printAllAss2() {
		
		ArrayList<Association2> res = new ArrayList<Association2>();

		try {
			java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getAllAss2);
			ResultSet rs = ps.executeQuery();
			
			String query = "SELECT IdGlobal, IdUser, IdTag FROM Association2";
			System.out.println("Executing query : " + query);			

			//lireResultSet(rs , out);
			
			while(rs.next()){
				int IdGlobal = rs.getInt(1);
				int IdUser  = rs.getInt(2);
				int IdTag = rs.getInt(3);
				res.add(new Association2(IdGlobal, IdTag, IdUser));				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Uncomment when the close function will be implemented
			// attemptToClose(ps);
			
		}
		return res;
	}

/**
 * Select IdTag from Association2 WHERE IdUser = 'IdUser';
 * @param IdUser the user ID
 * @return A list of tag Id
 *    
 */
public ArrayList<Integer> getTagIdByUserId( int IdUser ){
	ArrayList<Integer> res = new ArrayList<Integer>();

	try {
		java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.Ep_getTagIdFromUserId);
		ps.setInt(1, IdUser);
		ResultSet rs = ps.executeQuery();
		
		String query = "SELECT IdTag FROM Association2 WHERE IdUser = " + IdUser;
		System.out.println("Executing query : " + query);			

		
		//Retrieve TCellIP  and PublicKey from USER table in TCellDB
		
		while (rs.next()) {
			res.add(new Integer(rs.getInt(1)));
		}
		
		//rs.next();
		//res = rs.getInt(1);
		
		//res = rs.getInt(1);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		// Uncomment when the close function will be implemented
		// attemptToClose(ps);
	}

	return res;  
}


/**
 * Select IdFile from Association1 WHERE IdTag = 'IdTag';
 * @param IdTag: the tag ID
 * @return A list of IdFile
 *    
 */
public ArrayList<Integer> getFileIdByTagId( int IdTag ){
	ArrayList<Integer> result = new ArrayList<Integer>();

	try {
		java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.Ep_getFileIdFromTagId);
		ps.setInt(1, IdTag);
		ResultSet rs = ps.executeQuery();
		
		String query = "SELECT IdFile FROM Association1 WHERE IdTag = "+ IdTag +")";
		System.out.println("Executing query : " + query);			

		while (rs.next() == true) {
			int fileIdGlobal = rs.getInt(1);
			result.add(new Integer(fileIdGlobal));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		// Uncomment when the close function will be implemented
		// attemptToClose(ps);
	} 

	return result;                      
}

/**
 * SELECT FILEGID, TYPE, DESCRIPTION FROM File WHERE IdGlobal = 'IdGlobal';
 * @param fileIdGlobal: the file IdGlobal
 * @return A FileDesc
 *    
 */
public FileDesc getFileDescByFileIdGlobal( int fileIdGlobal ){
	FileDesc result = new FileDesc();

	try {
		java.sql.PreparedStatement ps = ((org.inria.jdbc.Connection) db).prepareStatement(TCell_QEP_IDs.QEP.EP_getFileDescByfileIdGlobal);
		ps.setInt(1, fileIdGlobal);
		ResultSet rs = ps.executeQuery();
		
		String query = "SELECT FILEGID, TYPE, DESCRIPTION FROM File WHERE IdGlobal = "+ fileIdGlobal +")";
		System.out.println("Executing query : " + query);			

		
		//Retrieve TCellIP  and PublicKey from USER table in TCellDB
		rs.next();
		String FileGid  = rs.getString(1);
		String type = rs.getString(2);
		String desc = rs.getString(3);
		
		result =  new FileDesc(FileGid, type, desc);
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		// Uncomment when the close function will be implemented
		// attemptToClose(ps);
	} 

	return result;                      
}

	/*************************************************************************************************************/

	

	private void attemptToClose(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
