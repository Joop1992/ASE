package start;

import java.io.Serializable;

public class Klus implements Serializable{

	private int klantID, autoID, ID , uren;
	private String datum, status, werk, werknemers, onderdelen;

	public Klus(String d, String s, int kID, int aID, String w, String wN, int i){
		datum = d;
		status = s;
		klantID = kID;
		autoID = aID;
		werk = w;
		werknemers = wN;
		ID = i;
		onderdelen = "";
		uren = 0;
	}

	public String getDatum(){
		return datum;
	}
	public String getStatus(){
		return status;
	}
	
	public String getOnderdelen(){
		return onderdelen;
	}
	
	public void setOnderdelen(String o){
		onderdelen = o;
	}
	
	public void setStatus(String s){
		status = s;
	}

	public int getKlantID(){
		return klantID;
	}
	
	public int getUren(){
		return uren;
	}
	
	public void setUren(int u){
		uren = u;
	}

	public int getAutoID(){
		return autoID;
	}

	public String getWerk(){
		return werk;
	}

	public String getWerknemers(){
		return werknemers;
	}

	public int getID(){
		return ID;
	}
}