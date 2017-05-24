package org.inria.dmsp;
public class QEP {
public static final String META =
	"TAB_DESC,9\n"+
	"0	QEP	512\n"+
	"1	LogDeleted	12\n"+
	"2	UpdateLog	512\n"+
	"3	User	49\n"+
	"4	File	456\n"+
	"5	MyInfo	73\n"+
	"6	Tag	106\n"+
	"7	Association1	12\n"+
	"8	Association2	12\n"+
	"COL_DESC,35\n"+
	"0	0	IdGlobal	4	1	0\n"+
	"1	0	QEPStr	460	0	4\n"+
	"2	0	SQLStr	24	9	464\n"+
	"3	0	ExplainStr	24	9	488\n"+
	"4	1	TabId	4	1	0\n"+
	"5	1	TuplePos	4	1	4\n"+
	"6	1	Flag	4	1	8\n"+
	"7	2	TabId	4	1	0\n"+
	"8	2	TuplePos	4	1	4\n"+
	"9	2	TupleSize	4	1	8\n"+
	"10	2	CompleteTup	500	0	12\n"+
	"11	3	IdGlobal	4	1	0\n"+
	"12	3	TCELLIP	17	0	4\n"+
	"13	3	TCELLPORT	4	1	21\n"+
	"14	3	PUBLICKEY	24	9	25\n"+
	"15	4	IdGlobal	4	1	0\n"+
	"16	4	FILEGID	102	0	4\n"+
	"17	4	FILEID	102	0	106\n"+
	"18	4	SECRETKEY	102	0	208\n"+
	"19	4	IV	32	0	310\n"+
	"20	4	TYPE	12	0	342\n"+
	"21	4	DESCRIPTION	102	0	354\n"+
	"22	5	IdGlobal	4	1	0\n"+
	"23	5	MYTCELLIP	17	0	4\n"+
	"24	5	MYTCELLPORT	4	1	21\n"+
	"25	5	MYPUBLICKEY	24	9	25\n"+
	"26	5	MYPRIVATEKEY	24	9	49\n"+
	"27	6	IdGlobal	4	1	0\n"+
	"28	6	TagName	102	0	4\n"+
	"29	7	IdGlobal	4	1	0\n"+
	"30	7	IdFile	4	1	4\n"+
	"31	7	IdTag	4	1	8\n"+
	"32	8	IdGlobal	4	1	0\n"+
	"33	8	IdUser	4	1	4\n"+
	"34	8	IdTag	4	1	8\n"+
	"FK_DESC,4\n"+
	"7	30	4	15\n"+
	"7	31	6	27\n"+
	"8	33	3	11\n"+
	"8	34	6	27\n"+
	"SKT_DESC,2\n"+
	"0	7	Association1	8\n"+
	"1	8	Association2	8\n"+
	"SKT_COL_DESC,4\n"+
	"0	0	4	15	1\n"+
	"0	4	6	27	1\n"+
	"1	0	3	11	1\n"+
	"1	4	6	27	1\n"+
	"CI_DESC,7\n"+
	"0	0	0	0	1\n"+
	"1	3	3	11	1\n"+
	"2	4	4	15	1\n"+
	"3	5	5	22	1\n"+
	"4	6	6	27	1\n"+
	"5	7	7	29	1\n"+
	"6	8	8	32	1\n"+
	"";

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

