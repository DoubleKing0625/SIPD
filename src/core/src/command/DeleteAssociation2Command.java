package command;


/**
 * DeleteAssociation2Command  This command contains the IdUser, IdTag 
 *
 */
public class DeleteAssociation2Command extends Command {

	// store
	private int IdUser;
	private int IdTag;
	

	public DeleteAssociation2Command(int numCommand, int IdUser, int IdTag)
	{
		super(numCommand);
		this.IdUser = IdUser;
		this.IdTag = IdTag;
	}
	

	public int getUserId()
	{
		return this.IdUser;
	}
	
	public void setUserId(int IdUser)
	{
		this.IdUser=IdUser;
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

