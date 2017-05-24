package beans;

import java.io.Serializable;

public class Association2 implements Serializable
{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private int IdAss2;
	private int IdTag;
	private int IdUser;
	
	public Association2(int IdAss2, int IdTag, int IdUser)
	{
		this.IdAss2=IdAss2;
		this.IdTag=IdTag;
		this.IdUser=IdUser;
	}
	
	public int getAssociation2ID() {
		return IdAss2;
	}

	public void setAssociation2ID(int IdAss1) {
		this.IdAss2 = IdAss1;
	}

	public int getAssociation2TagId() {
		return IdTag;
	}

	public void setAssociation2TagId(int IdTag) {
		this.IdTag = IdTag;
	}

	public int getAssociation2UserId() {
		return IdUser;
	}

	public void setAssociation2UserId(int IdUser) {
		this.IdUser = IdUser;
	}
}
