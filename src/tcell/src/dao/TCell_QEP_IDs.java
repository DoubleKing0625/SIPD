package dao;

public class TCell_QEP_IDs {
	/**
	 * EP_QEP class must exist in every application. It allows to interact
	 * hard coded QEPs inside SGBD. Application QEP start id should be greater
	 * than the value of last element of this class.
	 */
	public static class EP_QEP // 1
	{
		public static final int EP_QEP_INSERT = 0;
	}

	/* Application QEP ids */
	public static class QEP // 2
	{
		public static final int EP_insertUser = EP_QEP.EP_QEP_INSERT + 1;
		public static final int EP_insertFileDesc = EP_insertUser + 1;
		public static final int EP_deleteFileDescByFileGid = EP_insertFileDesc + 1;
		public static final int EP_deleteUserByUserGid = EP_deleteFileDescByFileGid + 1;
		public static final int EP_isFileGidExists = EP_deleteUserByUserGid + 1;
		public static final int EP_getUserById = EP_isFileGidExists + 1;
		public static final int EP_getFileDescByGid = EP_getUserById + 1;
		public static final int EP_getAllUsersGid = EP_getFileDescByGid + 1;
		public static final int EP_getAllFiles = EP_getAllUsersGid + 1;
		public static final int EP_getMyInfo = EP_getAllFiles + 1;
		public static final int EP_insertMyInfo = EP_getMyInfo + 1;
		public static final int EP_getAllUsers = EP_insertMyInfo + 1;
		public static final int EP_getAllFilesDesc = EP_getAllUsers + 1;
		
		/***************************PENG HUANG********************************************************/
		public static final int EP_addTag = EP_getAllFilesDesc + 1;
		public static final int EP_getTagIdFromTagname = EP_addTag + 1;
		public static final int EP_insertTag = EP_getTagIdFromTagname + 1;
		public static final int EP_getAllTags = EP_insertTag + 1;
		public static final int EP_insertAss1 = EP_getAllTags + 1;
		public static final int EP_getAllAss1 = EP_insertAss1 + 1;
		public static final int EP_insertAss2 = EP_getAllAss1 + 1;
		public static final int EP_getAllAss2 = EP_insertAss2 + 1;
		public static final int Ep_getTagIdFromUserId = EP_getAllAss2 + 1;
		public static final int Ep_getFileIdFromTagId = Ep_getTagIdFromUserId + 1;
		public static final int EP_getFileDescByfileIdGlobal = Ep_getFileIdFromTagId + 1;
		public static final int Ep_deleteAssociation2 = EP_getFileDescByfileIdGlobal + 1;
	}
}
