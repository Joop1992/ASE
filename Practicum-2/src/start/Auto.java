package start;

import java.io.Serializable;

public class Auto implements Serializable{
	private String kenteken;
    private int ID;
	private String merk;
	private int klantNummer;
	
	public Auto(String mk, String kt, int id){
        ID = id;
		kenteken = kt;
		merk = mk;
		klantNummer = 0;
	}
	
	public int getID(){
		return ID;
	}
	
	public String getMerk(){
		return merk;
	}
	
	public void setMerk(String mk){
		merk = mk;
	}
	
	public String getKenteken(){
		return kenteken;
	}
	
	public void setKenteken(String kt){
		kenteken = kt;
	}

	public int getKlantNummer() {
		return klantNummer;
	}

	public void setKlantNummer(int kNr) {
		this.klantNummer = kNr;
	}
}
