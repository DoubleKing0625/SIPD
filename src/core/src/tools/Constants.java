package tools;

/**
 * Constants definitions
 * @author tranvan
 */
public class Constants 
{
    
    public final static int CMD_STORE_FILE = 0;
    public final static int CMD_SHARE_FILE = 1;
    public final static int CMD_GET_FILES_DESC = 2;
    public final static int CMD_READ_FILE = 3;
    public final static int CMD_GET_FILE_TO_SHARE = 4;
    public final static int CMD_GET_USER = 5;
    
    /***********************************par PENG HUANG SHEN LIU*****************************************************/
    public final static int CMD_ADD_TAG = 6;
    public final static int CMD_PRINT_ALL_TAG = 7;
    public final static int CMD_ADD_ASSOCIATION1 = 8;
    public final static int CMD_Print_ALL_ASSOCIATION1 = 9;
    public final static int CMD_ADD_USER = 10;
    public final static int CMD_ADD_ASSOCIATION2 = 11;
    public final static int CMD_Print_ALL_ASSOCIATION2 = 12;
    public final static int CMD_GET_TAG_ID_BY_USER_ID = 13;
    public final static int CMD_GET_FILE_ID_BY_TAG_ID = 14;
    public final static int CMD_GET_FILE_DESC_BY_FILE_IDGLOBAL = 15;
    public final static int CMD_DELETE_ASSOCIATION2 = 16;

    public final static String TMP_FILES=			"TMP/";    
    
    public final static String SYM_DECR_FILE_NAME = "Decrypted_";
    public final static String SYM_ENCR_FILE_NAME = "Encrypted_";
    public final static String PUB_KEY_PREFIX = "pub";
    public final static String PR_KEY_PREFIX = "priv";
    public final static String KEY_EXT = ".key";
    
    public final static String RSA_ALG = "RSA";
    public final static int RSA_KEY_LENGTH = 1024;
    public final static int RSA_PLAIN_BLOCK_SIZE = (RSA_KEY_LENGTH / 8 ) - 11;
    
    public final static int AES_KEY_ENCODED_BYTES_LENGTH = 24;
    public final static int IV_LENGTH = 16;
    public final static int IV_ENCODED_BYTES_LENGTH = 24;

    public final static int SQL_OK = 100;
    public final static int SQL_KO = 101;
    public final static int SQL_NOT_UNIQUE = 102;
    public final static int KO = -1;
    public final static int OK = 104;
    
    public final static int ADD_TAG_OK = 105;
    public final static int ADD_ASS1_OK = 106;
    public final static int PRINT_ALL_ASS1_OK = 107;
    public final static int ADD_USER_OK = 108;
    public final static int ADD_ASS2_OK = 109;
    public final static int PRINT_ALL_ASS2_OK = 110;
    public final static int GET_TAG_ID_BY_USER_ID_OK = 111;
    public final static int PRINT_ALL_TAG_OK = 112;
    public final static int GET_FILE_ID_BY_TAG_ID_OK = 113;
    public final static int GET_FILE_DESC_BY_FILE_IDGLOBAL_OK = 114;
    public final static int DELETE_ASSOCIATION2_OK = 115;
}
