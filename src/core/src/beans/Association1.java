package beans;

import java.io.Serializable;

public class Association1 implements Serializable
{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private int IdAss1;
	private int IdTag;
	private int IdFile;
	
	public Association1(int IdAss1, int IdTag, int IdFile)
	{
		this.IdAss1=IdAss1;
		this.IdTag=IdTag;
		this.IdFile=IdFile;
	}
	
	public int getAssociation1ID() {
		return IdAss1;
	}

	public void setAssociation1ID(int IdAss1) {
		this.IdAss1 = IdAss1;
	}

	public int getAssociation1TagId() {
		return IdTag;
	}

	public void setAssociation1TagId(int IdTag) {
		this.IdTag = IdTag;
	}

	public int getAssociation1FileId() {
		return IdFile;
	}

	public void setAssociation1FileId(int IdFile) {
		this.IdFile = IdFile;
	}
}
