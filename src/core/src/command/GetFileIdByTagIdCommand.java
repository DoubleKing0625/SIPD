package command;

/**
 *   GetIdFileByIdTagCommand This command contains the tagGID of the tag requested 
 *  
 */
public class GetFileIdByTagIdCommand extends Command {
	
	private int IdTag;

	public GetFileIdByTagIdCommand(int numCommand, int IdTag) {
		super(numCommand);
		this.IdTag = IdTag;
	}

	public int getIdTag() {
		return IdTag;
	}

	public void setIdTag(int IdTag) {
		this.IdTag = IdTag;
	}
	
}