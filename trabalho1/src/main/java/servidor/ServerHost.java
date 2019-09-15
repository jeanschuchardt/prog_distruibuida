package servidor;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe remota para o exemplo "Hello, world!"
public class ServerHost extends UnicastRemoteObject implements ServerHostInterface {
	private static final long serialVersionUID = 7896795898928782846L;
	HashMap<String, HashMap<String, String>> recursos = new HashMap<String, HashMap<String, String>>();
	ArrayList<String> clientes = new ArrayList<String>();
	
	// Constroi um objeto remoto armazenando nele o String recebido
	public ServerHost() throws RemoteException {
		System.out.println("Servidor up");
	}

	// Implementa o metodo invocavel remotamente, que retorna a mensagem armazenada
	// no objeto
	public String say() throws RemoteException {
		return "Teste blablabla";
	}

	public String registraPeer() throws Exception {
		String distributeeHost = RemoteServer.getClientHost();
		if(clientes.size()>0) {
		
			for (String ip : clientes) {
				if (!distributeeHost.equals(ip)) {
					clientes.add(distributeeHost);
					System.out.println("registrando ip cliente");
					return "Seu ip foi registrado "+distributeeHost;		
				}
				else {
					
					return "cliente ja registrado";
				}
			}
		}else {
			clientes.add(distributeeHost);
			System.out.println("registrando ip cliente");
			return "Seu ip foi registrado "+distributeeHost;
			
		}
		return "erro";
		
				
		
	}

	public Map associaRecuso() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	// retorna todos os recursos disponiveis
	public HashMap<String, HashMap<String, String>> listaRecursos() throws RemoteException {

		return recursos;
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
		recursos.put(distributeeHost, mapFiles);

	}
}

;