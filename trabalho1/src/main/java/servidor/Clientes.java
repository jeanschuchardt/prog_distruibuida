package servidor;

import java.io.Serializable;
import java.util.HashMap;

public class Clientes implements Serializable {
	private String ip;
	private int islive;
	private HashMap<String, String> recursos = new HashMap<String, String>();
	
	
	public Clientes(String distributeeHost) {
		 ip =  distributeeHost;
		 islive = 10;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getIslive() {
		return islive;
	}
	public void decrementaIsLive() {
		islive--;
	}

	public void resetIsLive() {
		this.islive = 10;
	}

	public HashMap<String, String> getRecursos() {
		return recursos;
	}

	public void setRecursos(HashMap<String, String> recursos) {
		this.recursos = recursos;
	}


	

}
