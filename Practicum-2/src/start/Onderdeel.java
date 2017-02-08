package start;

import java.io.Serializable;

public class Onderdeel implements Serializable{

	private int artikelNummer;
	private int aantal;
	private String onderdeelNaam;
	//Database db = new Database();

	public Onderdeel(int aN, String nm){
		onderdeelNaam = nm;
		artikelNummer = aN;
		aantal = 10;
	}

	public int getArtikelNummer(){
		return artikelNummer;
	}

	public void setArtikelNummer(int aN){
		artikelNummer = aN;
	}

	public String getOnderdeelNaam(){
		return onderdeelNaam;
	}

	public void setOnderdeelNaam(String nm){
		onderdeelNaam = nm;
	}

	public int getVoorraad(){ // voorraad van een onderdeel ophalen
		return aantal;
	}

	public void setVoorraad(int aT){ // voorraad instellen
		aantal = aT;
	}

	public void gebruikOnderdeel(){ // een onderdeel gebruiken
		aantal = getVoorraad();
		if(aantal > 0){
			aantal--;
            setVoorraad(aantal);
		}
		stuurBestelling();
	}

	public void stuurBestelling(){ // bestelling verzenden naar leverancier en order bestand aanmaken
		int voorraad = getVoorraad();
		if(voorraad < 2){
			//Bestelling b = new Bestelling(artikelNummer+":"+onderdeelNaam,10-voorraad);
			//db.addBestelling(b);
		}

	}
}



