package start;



public class User {

	private String gebruikersNaam;
	private String wachtwoord;
	
	public User(String g, String ww){
		gebruikersNaam = g; 
		wachtwoord = ww;
	}
	
	public String getGebruikersNaam(){
		return gebruikersNaam;
	}
	
	public String getWachtwoord(){
		return wachtwoord;
	}
	
	
}
