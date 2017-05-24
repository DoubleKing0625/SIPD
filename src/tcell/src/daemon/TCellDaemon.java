package daemon;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.MyInfo;
import beans.User;
import configuration.Configuration;
import dao.TcellDAOToken;

/**
 * Daemon in TrustedCell.
 * 
 * @author Athanasia Katsouraki
 */
public class TCellDaemon{


	public static void main(String[] args) throws IOException {
		
		//Vérifier si un fichier de configuration est défini
		if (System.getProperty("configurationFilePath") == null) {
			System.out.println("'configurationFilePath' system property is not defined");
			return;
		}
		
		System.getProperties().setProperty("jdbc.port", Configuration.getConfiguration().getProperty("dbPort"));
		
		MyInfo myInfo  = TcellDAOToken.getInstance(false).getMyInfo();
		
		int userGID = myInfo.getMyGid();
		int listenPort = myInfo.getMyTcellPort();
		ServerSocket server = null;
		try {

			/* Creation of the server socket */
			server = new ServerSocket(listenPort);

			/* The server listens for new connections and accepts it */
			System.out.println("TCell Daemon started...");

			while (true) {
				System.out.println("\nWaiting for a connection from an APP or from other TCells");
				Socket clientSocket = server.accept();
				System.out.println("Accepted connection : " + clientSocket);

				/* For each socket, a new thread is created */
				ClientConnectionManager ccm = new ClientConnectionManager(clientSocket, userGID);
				ccm.start();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			server.close();
		}
	}
}