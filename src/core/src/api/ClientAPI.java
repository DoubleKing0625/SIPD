package api;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import messages.AddUser;
import messages.DeleteAss2;
import messages.AddAss1;
import messages.AddAss2;
import messages.AddTag;
import messages.GetFileDesc;
import messages.GetFileDescByFileIdGlobal;
import messages.GetFileIdByTagId;
import messages.GetTagIdByUserId;
import messages.PrintAllAss1;
import messages.PrintAllAss2;
import messages.PrintAllTag;
import messages.ReadFile;
import messages.ShareFile;
import messages.StoreFile;
import beans.FileDesc;
import beans.Tag;
import beans.User;

/**
 * USER API
 * 
 */
public class ClientAPI
{


	public static FileDesc storeFile(String fileID, User user ) {
		return StoreFile.storeFile(fileID, user );
	}


	public static ArrayList<String> getFileDesc(User user) {
		return GetFileDesc.getFileDesc(user);
	}


	public static void readFile( String fileGID, User user ) {
		ReadFile.readFile(false, fileGID, user );
	}
	
	public static void shareFile(String fileGID, int userGID, User myInfo) throws UnknownHostException, IOException {
		ShareFile.shareFile( fileGID, userGID, myInfo );
	}
	/***************************************par PENG HUANG SHEN LIU************************************************/
	
	/*
	 * add a tag and return a class Tag
	 */
	public static Tag addTag(String TagName, User user){
		return AddTag.addTag(TagName, user);
	}
	
	/*
	 * print all tags
	 */
	public static void printAllTag(User user)
	{
		PrintAllTag.printAllTag(user);
	}
	
	/*
	 * add an association1. It is to say that a file has a tag. 
	 */
	public static void addAss1(int IdFile, User user, int IdTag)
	{
		AddAss1.addAss1(IdFile, user, IdTag);
	}
	
	/*
	 * print all association1. It have no meaning just for testing
	 */
	public static void printAllAss1(User user){
		PrintAllAss1.printAllAss1(user);
	}
	
	/*
	 * add an association2. It is to say that an user has a tag. 
	 */
	public static void addAss2(int IdUser, User user, int IdTag){
		AddAss2.addAss2(IdUser, user, IdTag);
	}
	
	/*
	 * delete an association2. It is to say that an user no longer has a tag. 
	 */
	public static void deleteAss2(int IdUser, User user, int IdTag){
		DeleteAss2.deleteAss2(IdUser, user, IdTag);
	}
	
	/*
	 * print all association2. It have no meaning just for testing
	 */
	public static void printAllAss2(User user){
		PrintAllAss2.printAllAss2(user);
	}
	
	/*
	 * Add a file with certain tag at same time
	 */
	public static void addFileWithTag(String fileID, String TagName, User user){
		FileDesc res_file = storeFile(fileID, user);
		Tag res_Tag = addTag(TagName, user);
		addAss1(res_file.getIdFile(), user, res_Tag.getTagID());
	}
	
	/*
	 * Add an user
	 */
	public static User addUser(int userGID, String tcellIP, int tcellPort, String pubKey, User user){
		return AddUser.addUser(userGID, tcellIP, tcellPort, pubKey, user);
	}
	
	/*
	 * Add an user with vertain tag at same time
	 */
	public static void addUserWithTag(int userGID, String tcellIP, int tcellPort, String pubKey, String TagName, User user){
		User res_user = addUser(userGID, tcellIP, tcellPort, pubKey, user);
		Tag res_Tag = addTag(TagName, user);
		addAss2(res_user.getUserGID(), user, res_Tag.getTagID());
	}
	
	/*
	 * Get a list of tag Ids of a user(with the user's IdGlobal)
	 */	
	public static ArrayList<Integer> getTagIdByUserId(int IdUser, User myInfo){
		return GetTagIdByUserId.getTagIdByUserId(IdUser, myInfo);
	}
	
	/*
	 * Get a list of file IdGlobal which has a given tag(with the tag's IdGlobal)
	 */
	
	public static ArrayList<Integer> getFileIdByTagId(int IdTag, User myInfo){
		return GetFileIdByTagId.getFileIdByTagId(IdTag, myInfo);
	}
	
	
	/*
	 * Get the FileDesc with a file IdGlobal 
	 */
	public static FileDesc getFileDescByFileIdGlobal(int FileIdGlobal, User myInfo){
		return GetFileDescByFileIdGlobal.getFileDescByFileIdGlobal(myInfo, FileIdGlobal);
	}
	
	/*
	 * Regroup those 3 previous APIs to find a list of FileDesc with a user's IdGlobal
	 */
	
	public static void getFileDescByUserId(int IdUser, User myInfo){
		
		System.out.println("111");
		ArrayList<Integer> listIdTag = getTagIdByUserId(IdUser, myInfo);
		ArrayList<Integer> listfileIdGlobal = new ArrayList<Integer>();
		
		for(Integer each : listIdTag)
		{
			listfileIdGlobal.addAll(getFileIdByTagId(each.intValue(), myInfo));
		}
		
		ArrayList<FileDesc> listFileDesc = new ArrayList<FileDesc>();
		
		for(Integer each : listfileIdGlobal)
		{
			//System.out.println("IdFile: " + each.intValue());
			int fileIdGlobal = each.intValue();
			FileDesc fd = getFileDescByFileIdGlobal(fileIdGlobal, myInfo);
			listFileDesc.add(fd);

		}
	
		for(FileDesc each : listFileDesc)
		{
			System.out.println("fileGID: " + each.getFileGID() + ", Type: "+ each.gettype() + ", Description: "+ each.getdescr());
		}
	}
	
	
}
