package dao;

/**
 * 
 * 
 * @author Majdi Ben Fredj
 * 
 * The execution plans of queries should be declaring this class
 * 
 */

public class QEP {
		public static String EP_insertFileDesc =
			"/*EP \u0007 6 1 1 -1 2 ?1 # 5 0 0 1 7 4 1 15 r0 16 ?2 17 ?3 18 ?4 19 ?5 20 ?6 21 ?7 # \u0000*/";

		public static String EP_insertTag =
			"/*EP \u0002 6 1 1 -1 4 ?1 # 5 0 0 1 2 6 1 27 r0 28 ?2 # \u0000*/";

		public static String EP_insertUser =
			"/*EP \u0004 6 1 1 -1 1 ?1 # 5 0 0 1 4 3 1 11 r0 12 ?2 13 ?3 14 ?4 # \u0000*/";

		public static String EP_insertAss1 =
			"/*EP \u0003 2 4 4 -1 2 ?2 # 2 5 5 -1 4 ?3 # 7 3 3 4 5 # 6 2 2 3 5 ?1 # 5 1 1 2 2 0 0 0 r1 1 r3 # 5 0 0 1 3 7 1 29 r4 30 ?2 31 ?3 # \u0000*/";

		public static String EP_insertAss2 =
			"/*EP \u0003 2 4 4 -1 1 ?2 # 2 5 5 -1 4 ?3 # 7 3 3 4 5 # 6 2 2 3 6 ?1 # 5 1 1 2 2 1 0 0 r1 1 r3 # 5 0 0 1 3 8 1 32 r4 33 ?2 34 ?3 # \u0000*/";

		public static String Ep_getTagIdFromuserId =
			"/*EP \u0001 0 2 2 8 # 1 1 1 2 r0 2 8 1 34 33 # 4 0 0 1 33 0 ?1 r2 # \u0000 1 1 1 IdTag # \u0000*/";

		public static String Ep_getFileIdFromTagId =
			"/*EP \u0001 0 2 2 7 # 1 1 1 2 r0 2 7 1 30 31 # 4 0 0 1 31 0 ?1 r2 # \u0000 1 1 1 IdFile # \u0000*/";

		public static String EP_getFileDescFromFileId =
			"/*EP \u0001 2 1 1 -1 2 ?1 # 1 0 0 1 r1 2 4 1 16 21 # \u0000 2 0 2 FILEGID 0 3 DESCRIPTION # \u0000*/";

		public static String EP_getAllTags =
			"/*EP \u0000 0 1 1 6 # 1 0 0 1 r0 2 6 1 27 28 # \u0000 2 1 1 IdGlobal 0 2 TagName # \u0000*/";

		public static String EP_getTagIdFromTagname =
			"/*EP \u0001 0 2 2 6 # 1 1 1 2 r0 2 6 1 27 28 # 4 0 0 1 28 0 ?1 r2 # \u0000 1 1 1 IdGlobal # \u0000*/";

		public static String EP_deleteFileDescByFileGid =
			"/*EP \u0001 2 2 2 -1 2 ?1 # 5 1 1 2 3 1 1 4 v14 5 r1 6 v10 # 9 0 0 1 4 r1 # \u0000*/";

		public static String EP_deleteUserByUserGid =
			"/*EP \u0001 2 2 2 -1 1 ?1 # 5 1 1 2 3 1 1 4 v13 5 r1 6 v10 # 9 0 0 1 3 r1 # \u0000*/";

		public static String EP_isFileGidExists =
			"/*EP \u0001 0 2 2 4 # 1 1 1 2 r0 2 4 1 15 16 # 4 0 0 1 16 0 ?1 r2 # \u0000 2 1 1 IdGlobal 0 2 FILEGID # \u0000*/";

		public static String EP_getUserById =
			"/*EP \u0001 2 1 1 -1 1 ?1 # 1 0 0 1 r1 3 3 1 12 14 13 # \u0000 4 1 0 IdGlobal 0 2 TCELLIP 9 3 PUBLICKEY 1 4 TCELLPORT # \u0000*/";

		public static String EP_getFileDescByGid =
			"/*EP \u0001 0 2 2 4 # 1 1 1 2 r0 4 4 1 17 18 19 16 # 4 0 0 1 16 0 ?1 r4 # \u0000 3 0 1 FILEID 0 2 SECRETKEY 0 3 IV # \u0000*/";

		public static String EP_getAllUsersGid =
			"/*EP \u0000 0 1 1 3 # 1 0 0 1 r0 1 3 1 11 # \u0000 1 1 1 IdGlobal # \u0000*/";

		public static String EP_getAllFiles =
			"/*EP \u0000 0 1 1 4 # 1 0 0 1 r0 3 4 1 16 20 21 # \u0000 3 0 1 FILEGID 0 2 TYPE 0 3 DESCRIPTION # \u0000*/";

		public static String EP_getAllFilesDesc =
			"/*EP \u0000 0 1 1 4 # 1 0 0 1 r0 7 4 1 15 16 17 18 19 20 21 # \u0000 7 1 1 IdGlobal 0 2 FILEGID 0 3 FILEID 0 4 SECRETKEY 0 5 IV 0 6 TYPE 0 7 DESCRIPTION # \u0000*/";

		public static String EP_getMyInfo =
			"/*EP \u0000 0 1 1 5 # 1 0 0 1 r0 5 5 1 22 23 24 25 26 # \u0000 5 1 1 IdGlobal 0 2 MYTCELLIP 1 3 MYTCELLPORT 9 4 MYPUBLICKEY 9 5 MYPRIVATEKEY # \u0000*/";

		public static String EP_insertMyInfo =
			"/*EP \u0005 6 1 1 -1 3 ?1 # 5 0 0 1 5 5 1 22 r0 23 ?2 24 ?3 25 ?4 26 ?5 # \u0000*/";

		public static String EP_getAllUsers =
			"/*EP \u0000 0 1 1 3 # 1 0 0 1 r0 4 3 1 11 12 14 13 # \u0000 4 1 1 IdGlobal 0 2 TCELLIP 9 3 PUBLICKEY 1 4 TCELLPORT # \u0000*/";

		public static String EP_deleteAss1ByAss1Id =
			"/*EP \u0001 2 2 2 -1 5 ?1 # 5 1 1 2 3 1 1 4 v17 5 r1 6 v10 # 9 0 0 1 7 r1 # \u0000*/";

		public static String EP_deleteAss2ByAss2Id =
			"/*EP \u0001 2 2 2 -1 6 ?1 # 5 1 1 2 3 1 1 4 v18 5 r1 6 v10 # 9 0 0 1 8 r1 # \u0000*/";

		public static String EP_getAllAss1 =
			"/*EP \u0000 0 1 1 7 # 1 0 0 1 r0 3 7 1 29 30 31 # \u0000 3 1 1 IdGlobal 1 2 IdFile 1 3 IdTag # \u0000*/";

		public static String EP_getAllAss2 =
			"/*EP \u0000 0 1 1 8 # 1 0 0 1 r0 3 8 1 32 33 34 # \u0000 3 1 1 IdGlobal 1 2 IdUser 1 3 IdTag # \u0000*/";
		
		public static String Ep_getTagIdFromUserId =
				"/*EP \u0001 0 2 2 8 # 1 1 1 2 r0 2 8 1 34 33 # 4 0 0 1 33 0 ?1 r2 # \u0000 1 1 1 IdTag # \u0000*/";
		public static String EP_getFileDescByfileIdGlobal =
				"/*EP \u0001 2 1 1 -1 2 ?1 # 1 0 0 1 r1 3 4 1 16 20 21 # \u0000 3 0 2 FILEGID 0 3 TYPE 0 4 DESCRIPTION # \u0000*/";

		public static String Ep_deleteAssociation2 =
				"/*EP \u0002 0 5 5 8 # 1 4 4 5 r0 3 8 1 32 33 34 # 4 3 3 4 33 0 ?1 r2 # 4 2 2 3 34 0 ?2 r3 # 5 1 1 2 3 1 1 4 v18 5 r0 6 v10 # 9 0 0 1 8 r0 # \u0000*/";

}