package command;

/**
 * AddTagCommand  This command contains the tagName 
 *
 */
public class AddUserCommand extends Command {

	// store
	private int userGID;
	private String TCellIP;
	private String PubKey;
	private int port;

	public AddUserCommand(int numCommand, int userGID, String TCellIP, int port, String PubKey ) 
	{
		super(numCommand);
		this.userGID = userGID;
		this.TCellIP = TCellIP;
		this.port = port;
		this.PubKey = PubKey;
	}
	
	public int getuserGID()
	{
		
		return this.userGID;
	}
	
	public void setuserGID(int userGID)
	{
		this.userGID=userGID;
	}
	
	public String getTCellIP(){
		return this.TCellIP;
	}
	
	public void setTCellIP(String TCellIP){
		this.TCellIP = TCellIP;
	}
	
	public String getPubKey(){
		return this.PubKey;
	}
	
	public void setPubKey(String PubKey){
		this.PubKey = PubKey;
	}
	
	public int getport(){
		return this.port;
	}
	
	public void setport(int port){
		this.port = port;
	}
}