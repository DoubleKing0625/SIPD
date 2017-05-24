package command;

/**
 *   GetUserCommand This command contains the userGID of the user requested
 *
 */
public class GetFileDescByFileIdGlobalCommand extends Command {

    private int fileIdGlobal;

    public GetFileDescByFileIdGlobalCommand(int numCommand, int fileIdGlobal) {
        super(numCommand);
        this.fileIdGlobal = fileIdGlobal;
    }

    public int getfileIdGlobal() {
        return fileIdGlobal;
    }

    public void setfileIdGlobal(int fileIdGlobal) {
        this.fileIdGlobal = fileIdGlobal;
    }

}