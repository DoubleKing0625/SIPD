CREATE TABLE QEP
(
IdGlobal numeric PRIMARY KEY,
QEPStr char(458),
SQLStr Blob,
ExplainStr Blob
)
/

CREATE TABLE LogDeleted 
(
TabId numeric,
TuplePos numeric,
Flag numeric 
) 
/

CREATE TABLE UpdateLog 
(
TabId numeric,
TuplePos numeric,
TupleSize numeric,
CompleteTup char(498)
)
/

-- I have added TcellPort
CREATE TABLE User 
( 
IdGlobal numeric PRIMARY KEY,
TCELLIP char(15),
TCELLPORT numeric,
PUBLICKEY Blob
)
/

-- I have added FILEGID, FILEID, IV, TYPE
CREATE TABLE File
(
IdGlobal numeric PRIMARY KEY,
FILEGID char(100) ,
FILEID char(100),
SECRETKEY char(100),
IV char(30),
TYPE char(10),
DESCRIPTION char(100)
)
/

CREATE TABLE MyInfo 
( 
IdGlobal numeric PRIMARY KEY,
MYTCELLIP char(15),
MYTCELLPORT numeric,
MYPUBLICKEY Blob,
MYPRIVATEKEY Blob
)
/

CREATE TABLE Tag
(
IdGlobal numeric PRIMARY KEY,
TagName char(100)
)
/

CREATE TABLE Association1 
(
 IdGlobal numeric PRIMARY KEY,
 IdFile numeric REFERENCES File(IdGlobal),
 IdTag numeric REFERENCES Tag(IdGlobal)
)
/

CREATE TABLE Association2 
(
 IdGlobal numeric PRIMARY KEY,
 IdUser numeric REFERENCES User(IdGlobal),
 IdTag numeric REFERENCES Tag(IdGlobal)
)
/

CREATE SKT Association1
(
File(IdGlobal),
Tag(IdGlobal)
)
/

CREATE SKT Association2
(
User(IdGlobal),
Tag(IdGlobal)
)
/

