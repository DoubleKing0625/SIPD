package beans;

import java.io.Serializable;

/**
 * FileDesc records.
 * 
 * @author Athanasia Katsouraki
 * 
 */

public class FileDesc implements Serializable {
	public int IdFile;
	public String fileGID;
	public String fileID;
	public String sKey;
	public String iv;
	public String type;
	public String descr;

	public FileDesc(){}
	
	public FileDesc(int IdFile, String fileGID, String fileID, String sKey, String iv, String type, String descr)
	{
		this.IdFile=IdFile;
		this.fileGID=fileGID;
		this.fileID=fileID;
		this.sKey=sKey;
		this.iv = iv;
		this.type=type;
		this.descr=descr;            
	}

	public FileDesc(String fileGID, String fileID, String sKey, String iv, String type, String descr)
	{
		this.fileGID=fileGID;
		this.fileID=fileID;
		this.sKey=sKey;
		this.iv = iv;
		this.type=type;
		this.descr=descr;            
	}
	
	public FileDesc(String fileID,String sKey, String iv, String descr){
		this.fileID=fileID;
		this.sKey=sKey;
		this.iv=iv;
	}
	
	
	public FileDesc(String fileGID,String type, String descr){
		this.fileGID=fileGID;
		this.type=type;      
		this.descr = descr;
	}
	
	public int getIdFile(){
		return IdFile;
	}
	
	public String getFileGID(){
		return fileGID;
	}
	
	public String gettype(){
		return type;
	}
	
	public String getdescr(){
		return descr;
	}
}


