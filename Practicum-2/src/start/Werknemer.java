package start;

import java.io.Serializable;

public class Werknemer implements Serializable{
	private String naam;
	private String wachtwoord;
	private String rechten;
	private int ID;
	
	public Werknemer(String nm, String ww, int id){
		naam = nm;
		wachtwoord = ww;
		ID = id;
		rechten = "";
	}
	
	public int getID(){
		return ID;
	}
	
	public String getNaam(){
		return naam;
	}
	
	public void setNaam(String nm){
		naam = nm;
	}
	
    public String getWachtwoord(){
		return wachtwoord;
	}
	
	public void setWachtwoord(String ww){
		wachtwoord = ww;
	}
	
	public String getRechten(){
		return rechten;
	}
	
	public void setRechten(String r){
		rechten = r;
	}
}
