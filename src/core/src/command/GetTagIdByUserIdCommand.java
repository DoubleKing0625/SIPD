package command;

/**
 *   GetTagIdByUserIdCommand This command contains the userGID of the user requested 
 *  
 */
public class GetTagIdByUserIdCommand extends Command {
	
	private int IdUser;

	public GetTagIdByUserIdCommand(int numCommand, int IdUSer) {
		super(numCommand);
		this.IdUser = IdUSer;
	}

	public int getIdUSer() {
		return IdUser;
	}

	public void setIdUSer(int IdUSer) {
		this.IdUser = IdUSer;
	}
	
}