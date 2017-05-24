--public static String EP_insertFileDesc =
INSERT INTO File  VALUES (?,?,?,?,?,?,?)
/

--public static String EP_insertTag = 
INSERT INTO Tag  VALUES (?,?)
/

--public static String EP_insertUser =
INSERT INTO User  VALUES (?,?,?,?)
/

--public static String EP_insertAss1 = 
INSERT INTO Association1  VALUES (?,?,?)
/

--public static String EP_insertAss2 = 
INSERT INTO Association2  VALUES (?,?,?)
/

--public static String Ep_getTagIdFromuserId = 
SELECT IdTag FROM Association2 WHERE IdUser = ?
/

--public static String Ep_getFileIdFromTagId = 
SELECT IdFile FROM Association1 WHERE IdTag = ?
/

--public static String EP_getFileDescFromFileId = 
SELECT  FILEGID, DESCRIPTION FROM File where IdGlobal = ?
/

--public static String EP_getAllTags = 
SELECT IdGlobal, TagName FROM Tag
/

--public static String EP_getTagIdFromTagname = 
SELECT IdGlobal FROM Tag WHERE TagName = ?
/

--public static String EP_deleteFileDescByFileGid =
DELETE FROM File WHERE IdGlobal = ?
/

--public static String EP_deleteUserByUserGid =
DELETE FROM User WHERE IdGlobal = ?
/

--public static String EP_isFileGidExists = 
SELECT IdGlobal, FILEGID FROM File WHERE FILEGID = ? 
/

--public static String EP_getUserById = 
SELECT IdGlobal, TCELLIP, PUBLICKEY,TCELLPORT FROM USER WHERE IdGlobal= ?
/

--public static String EP_getFileDescByGid = 
SELECT FILEID, SECRETKEY, IV FROM FILE WHERE FILEGID = ?
/

--public static String EP_getAllUsersGid = 
SELECT IdGlobal from USER
/

--public static String EP_getAllFiles = 
SELECT FILEGID, TYPE, DESCRIPTION FROM FILE
/

--public static String EP_getAllFilesDesc = 
SELECT IdGlobal ,FILEGID ,FILEID ,SECRETKEY ,IV ,TYPE ,DESCRIPTION FROM FILE
/

--public static String EP_getMyInfo = 
SELECT IdGlobal, MYTCELLIP, MYTCELLPORT, MYPUBLICKEY, MYPRIVATEKEY FROM MyInfo
/

--public static String EP_insertMyInfo =
INSERT INTO MyInfo  VALUES (?,?,?,?,?)
/

--public static String EP_getAllUsers = 
SELECT IdGlobal, TCELLIP, PUBLICKEY,TCELLPORT FROM USER
/

--public static String EP_deleteAss1ByAss1Id =
DELETE FROM Association1 WHERE IdGlobal = ?
/

--public static String EP_deleteAss2ByAss2Id =
DELETE FROM Association2 WHERE IdGlobal = ?
/

--public static String EP_getAllAss1 = 
SELECT IdGlobal, IdFile, IdTag FROM Association1
/

--public static String EP_getAllAss2 = 
SELECT IdGlobal, IdUser, IdTag FROM Association2
/

--public static String Ep_getTagIdFromUserId = 
SELECT IdTag FROM Association2 WHERE IdUser = ?
/

--public static String EP_getFileDescByfileIdGlobal = 
SELECT FILEGID, TYPE, DESCRIPTION FROM File WHERE IdGlobal = ?
/

--public static String Ep_deleteAssociation2 = 
DELETE FROM Association2 WHERE IdUser = ? AND IdTag = ?
/






