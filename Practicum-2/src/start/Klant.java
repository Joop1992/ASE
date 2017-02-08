package start;

import java.io.Serializable;

public class Klant implements Serializable{
	private String naam;
	private String email;
	private String wachtwoord;
	private int ID;
	
	public Klant(String nm, String em, String ww, int id){
		naam = nm;
		wachtwoord = ww;
		email = em;
		ID = id;
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
	public String getEmail(){
		return email;
	}
	public void setEmail(String em){
		email = em;
	}
	
    public String getWachtwoord(){
		return wachtwoord;
	}
	
	public void setWachtwoord(String ww){
		wachtwoord = ww;
	}
}
