package servidor;

import java.io.ObjectOutputStream.PutField;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.ServerHostInterface;

// Classe remota para o exemplo "Hello, world!"
public class ServerHost extends UnicastRemoteObject implements ServerHostInterface {

	private static final long serialVersionUID = 7896795898928782846L;

	HashMap<String, HashMap<String, String>> recursos = new HashMap<String, HashMap<String, String>>();
	HashMap<String, Clientes> clientes = new HashMap<String, Clientes>();

	// Constroi um objeto remoto armazenando nele o String recebido
	public ServerHost() throws RemoteException {
		System.out.println("Servidor up");
	}

	// Implementa o metodo invocavel remotamente, que retorna a mensagem armazenada
	// no objeto
	
	public String registraPeer() throws Exception {
		String ip = RemoteServer.getClientHost();
		clientes.put(ip, new Clientes(ip));

		return ip;

	}

	
	// retorna todos os recursos disponiveis
	public HashMap<String, HashMap<String, String>> listaRecursos() throws RemoteException {

		return recursos;
	}

	

	public int heartbeat() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void registraRecurso(HashMap<String, String> mapFiles) throws RemoteException, ServerNotActiveException {
		String distributeeHost = RemoteServer.getClientHost();
		Clientes clientes2 = clientes.get(distributeeHost);
		clientes2.setRecursos(mapFiles);
		recursos.put(distributeeHost, mapFiles);

	}

	public HashMap<String, Clientes> solicitaClientes() throws RemoteException {
		return clientes;
	}

	public String findByHash(String hash) throws RemoteException {
		for (String key : clientes.keySet()) {
			HashMap<String, String> recursos2 = clientes.get(key).getRecursos();
			
				for (String s : recursos2.keySet()) {
					String fileName = recursos2.get(s);
					if(s.equals(hash)){
						String a =  key +";" + s +";"+ fileName;
						System.out.println(a);
						return a;
					}
					
					System.out.println(s);
					System.out.println(fileName);
				}
				
			
			
			//	return key +";" + fileName +";"+ hash;
			
			System.out.println(key);
			
		
			
		}
		return "error";

	}


}

;