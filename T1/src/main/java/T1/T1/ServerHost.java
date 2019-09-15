package T1.T1;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe remota para o exemplo "Hello, world!"
public class ServerHost extends UnicastRemoteObject implements ServerHostInterface {
	private static final long serialVersionUID = 7896795898928782846L;
	private String message;
	HashMap<String, HashMap<String, String>> recursos = new HashMap<String, HashMap<String,String>>();
	

	// Constroi um objeto remoto armazenando nele o String recebido
	public ServerHost (String msg) throws RemoteException {
		message = msg;
	}

	// Implementa o metodo invocavel remotamente, que retorna a mensagem armazenada no objeto
	public String say() throws RemoteException {
		return message;
	}

	public String registraPeer() throws Exception {
		String distributeeHost = RemoteServer.getClientHost();
		
		return distributeeHost;
	}

	public List informaRegistros() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Map associaRecuso() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String solicitaInfo() throws RemoteException {
		// TODO Auto-generated method stub
		
		return "idjfaijsdfasf";
	}

	public String solicitaRecursoInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
		
		///uma thread de solicitações 
		//uma tread para percorrer a lista para ver quem ta vivo 
		 		 
	}

	public Map calulaHash(String dir) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String solicitaRecurso() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public int heartbeat() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void registraRecurso(HashMap<String, String> mapFiles) throws RemoteException, ServerNotActiveException {
		String distributeeHost = RemoteServer.getClientHost();
		recursos.put(distributeeHost,mapFiles);
		
		
		
	}
}

;