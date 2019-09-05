import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PidInterface extends Remote {
	
	//servidor
		
	//ip	
	public int registraPeer() throws RemoteException;
	
	//informa recursos disponiveis 
	public List informaRegistros() throws RemoteException;

	// associa um peer aos seus recursos
	private Map associaRecuso() throws RemoteException;

	// retorna uma lista de recursos
	public List solicitaInfo() throws RemoteException;

	// retorna uma lista de recursos
	public List solicitaRecursoInfo() throws RemoteException;

	



	//peer

	//calcula  hash
	private Map calulaHash(String dir) throws RemoteException;

	// recebe endereço do outro servidor onde esta o recurso 
	public String solicitaRecurso() throws RemoteException;

	public int heartbeat () throws RemoteException;


}
