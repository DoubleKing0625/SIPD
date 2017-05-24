package command;

/**
 * AddTagCommand  This command contains the tagName 
 *
 */
public class AddTagCommand extends Command {

	// store
	private String tagName;

	public AddTagCommand(int numCommand, String tagName) 
	{
		super(numCommand);
		this.tagName = tagName;
	}
	
	public String getTagName()
	{
		
		return this.tagName;
	}
	
	public void setTagName(String tagName)
	{
		this.tagName=tagName;
	}
}