package beans;

import java.io.Serializable;

public class Tag implements Serializable
{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private int IdTag;
	private String TagName;
	
	public Tag(){}
	public Tag(int IdTag, String TagName)
	{
		this.IdTag=IdTag;
		this.TagName=TagName;	
	}


	public int getTagID() {
		return IdTag;
	}


	public void setTagID(int IdTag) {
		this.IdTag = IdTag;
	}


	public String getTagName() {
		return TagName;
	}


	public void setTagName(String TagName) {
		this.TagName = TagName;
	}

	
}
