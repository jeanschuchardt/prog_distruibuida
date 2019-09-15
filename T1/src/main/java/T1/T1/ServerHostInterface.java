package T1.T1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Interface remota para o exemplo "Hello, world!"
public interface ServerHostInterface extends Remote {
	//servidor
	
		//ip	
		public String registraPeer() throws RemoteException, Exception;
		
		//informa recursos disponiveis 
		public List informaRegistros() throws RemoteException;

		// associa um peer aos seus recursos
		public Map associaRecuso() throws RemoteException;

		// retorna uma lista de recursos
		public String solicitaInfo() throws RemoteException;

		// retorna uma lista de recursos
		public String solicitaRecursoInfo() throws RemoteException ;

		



		//peer

		//calcula  hash
		public Map calulaHash(String dir) throws RemoteException;

		// recebe endereço do outro servidor onde esta o recurso 
		public String solicitaRecurso() throws RemoteException;

		public int heartbeat () throws RemoteException;

		public String say() throws RemoteException;

		public void registraRecurso(HashMap<String, String> mapFiles) throws RemoteException, ServerNotActiveException;;

}

