package command;

/**
 * AddAssociation1Command  This command contains the IdAss1, IdFile, IdTag 
 *
 */
public class AddAssociation1Command extends Command {

	// store
	private int IdFile;
	private int IdTag;
	

	public AddAssociation1Command(int numCommand, int IdFile, int IdTag)
	{
		super(numCommand);
		this.IdFile = IdFile;
		this.IdTag = IdTag;
	}
	

	public int getFileId()
	{
		return this.IdFile;
	}
	
	public void setFileId(int IdFile)
	{
		this.IdFile=IdFile;
	}
	
	public int getTagId()
	{
		return this.IdTag;
	}
	
	public void setTagId(int IdTag)
	{
		this.IdTag=IdTag;
	}
}

