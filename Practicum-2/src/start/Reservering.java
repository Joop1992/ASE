package start;

import java.io.Serializable;

public class Reservering implements Serializable{

	private String kenteken, datum, duur;

	public Reservering(String k, String d,String dr){
		kenteken = k.replaceAll(" ","");
		datum = d.replaceAll(" ","");
		duur = dr;
	}

	public String getKenteken(){
		return kenteken;
	}

	public String getDatum(){
		return datum;
	}
	
	public String getDuur(){
		return duur;
	}
    
	//Reservering opslaan database vanuit de servlet!!
	
	//Kosten berekenen van de reservering in de database!!
}

