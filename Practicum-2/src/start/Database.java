package start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {

	ArrayList<Brandstof> brandstoffen = new ArrayList<Brandstof>();
	ArrayList<Onderdeel> onderdelen = new ArrayList<Onderdeel>();
	ArrayList<Reservering> reserveringen = new ArrayList<Reservering>();
	ArrayList<Bestelling> bestellingen = new ArrayList<Bestelling>();
	ArrayList<Monteur> monteurs = new ArrayList<Monteur>();
	ArrayList<Werkzaamheid> werkzaamheden = new ArrayList<Werkzaamheid>();
	ArrayList<Klus> klussen = new ArrayList<Klus>();
	ArrayList<Klant> klanten = new ArrayList<Klant>();
	ArrayList<Auto> autos = new ArrayList<Auto>();
	ArrayList<Werknemer> werknemers = new ArrayList<Werknemer>();
	ObjectInputStream ois;
	ObjectOutputStream oos;

	public Database() {
		createDirs();
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("C:/users/Public/database/brandstoffen.db")));
			brandstoffen = (ArrayList<Brandstof>) ois.readObject(); 
			ois.close();
		} catch (Exception e) {System.out.println("Er zijn nog geen brandstoffen aangemaakt");throw(e)}
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("C:/users/Public/database/onderdelen.db")));
			onderdelen = (ArrayList<Onderdeel>) ois.readObject(); 
			ois.close();
		} catch (Exception e) {System.out.println("Er zijn nog geen onderdelen aangemaakt");throw(e)}
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("C:/users/Public/database/reserveringen.db")));
			reserveringen = (ArrayList<Reservering>) ois.readObject(); 
			ois.close();
		} catch (Exception e) {System.out.println("Er zijn nog geen reserveringen aangemaakt");throw(e)}
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("C:/users/Public/database/bestellingen.db")));
			bestellingen = (ArrayList<Bestelling>) ois.readObject(); 
			ois.close();
		} catch (Exception e) {System.out.println("Er zijn nog geen bestelling aangemaakt");throw(e)}
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("C:/users/Public/database/monteurs.db")));
			monteurs = (ArrayList<Monteur>) ois.readObject(); 
			ois.close();
		} catch (Exception e) {System.out.println(e);throw(e)}
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("C:/users/Public/database/werkzaamheden.db")));
			werkzaamheden = (ArrayList<Werkzaamheid>) ois.readObject(); 
			ois.close();
		} catch (Exception e) {System.out.println(e);throw(e)}
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("C:/users/Public/database/klussen.db")));
			klussen = (ArrayList<Klus>) ois.readObject(); 
			ois.close();
		} catch (Exception e) {System.out.println(e);throw(e)}
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("C:/users/Public/database/klanten.db")));
			klanten = (ArrayList<Klant>) ois.readObject(); 
			ois.close();
		} catch (Exception e) {System.out.println(e);throw(e)}
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("C:/users/Public/database/autos.db")));
			autos = (ArrayList<Auto>) ois.readObject();
			ois.close(); 
		} catch (Exception e) {System.out.println(e);throw(e)}
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("C:/users/Public/database/werknemers.db")));
			werknemers = (ArrayList<Werknemer>) ois.readObject();
			ois.close();
		} catch (Exception e) {System.out.println(e);throw(e)}
		try{
			ois.close();
		}catch(Exception e){System.out.println("sluiten ging fout"+e); throw(e)}
		adminToevoegen();
	}

	
	public void adminToevoegen(){
		int listLengte = 0;
		for(Werknemer w : werknemers){
			listLengte++;
		}
		if(listLengte == 0){
			System.out.println("Size: "+werknemers.size());
			Werknemer w = new Werknemer("Henk", "Admin", 1);
			w.setRechten("1,2,3,4,5,");
			werknemers.add(w);
			updateWerknemerList();
		}
	}
	
	public void createDirs() {
		File f2 = new File("C:/Users/Public/database");
		if (!f2.exists()) {
			f2.mkdir();
		}
	}

	public ArrayList getList(String naam) {
		if (naam.equals("brandstoffen")) {
			return brandstoffen;
		} else if (naam.equals("onderdelen")) {
			return onderdelen;
		}else if (naam.equals("reserveringen")){
			return reserveringen;
		}else if (naam.equals("bestellingen")){
			return bestellingen;
		}else if (naam.equals("monteurs")){
			return monteurs;
		}else if (naam.equals("werkzaamheden")){
			return werkzaamheden;
		}else if (naam.equals("klussen")){
			return klussen;
		}else if (naam.equals("klanten")){
			return klanten;
		}else if (naam.equals("autos")){
			return autos;
		}else if (naam.equals("werknemers")){
			return werknemers;
		}
		return brandstoffen;
	}
	// ------------------------------Verwijderen------------------------------//
	
	public String remove(String item) {
		Brandstof gooiWeg = new Brandstof("nep", 12121212);
		boolean found = false;
		for (Brandstof b : brandstoffen) {
			if (b.getType().equals(item)) {
				gooiWeg = b;
				found = true;
			}
		}
		if (found) {
			brandstoffen.remove(gooiWeg);
			updateBrandstofList();
			return "Brandstof is verwijderd";
		}
		found = false;
		Onderdeel gooiWeg2 = new Onderdeel(1, "nep");
		for (Onderdeel o : onderdelen) {
			System.out.println("Item"+item);
			String strings[] = item.split(":");
			item = strings[0];
			System.out.println(item);
			if (o.getArtikelNummer() == Integer.parseInt(item)) {
				gooiWeg2 = o;
				found = true;
			}
		}
		if (found) {
			onderdelen.remove(gooiWeg2);
			updateOnderdeelList();
			return "Onderdeel is verwijderd";
		}
		return "Verwijderen mislukt";
	}
	
	public String removeBestelling(String item){
		Bestelling gooiWeg = new Bestelling("nep", 100);
		boolean found = false;
		int besteldAantal = 0;
		boolean deleted = false;
		for (Bestelling b : bestellingen) {
			if (b.getBesteldItem().equals(item)) {
				gooiWeg = b;
				found = true;
				besteldAantal = b.getAantal();
			}
		}
		if (found) {
			for(Brandstof b : brandstoffen){
				if(b.getType().equals(item)){
					b.setLiters(b.getLiters()+besteldAantal);
					deleted = true;
					updateBrandstofList();
				}
			}
			if(!deleted){
				String strings[] = item.split(":");
				item = strings[0];
				for(Onderdeel o : onderdelen){
					if(o.getArtikelNummer() == Integer.parseInt(item)){
						o.setVoorraad(o.getVoorraad()+besteldAantal);
						updateOnderdeelList();
					}
				}
			}
			bestellingen.remove(gooiWeg);
			updateBestellingList();
			return "Bestelling is verwijderd";
		}
		return "Bestelling verwijderen mislukt";
	}
	
	public String removeAuto(int id){
		String info = "Auto verwijderen mislukt";
		AutoInterface gooiWeg = Auto.createAuto("nep", "aaaaaa", -1);
		boolean found = false;
		for(AutoInterface a : autos){
			if(a.getID() == id){
				gooiWeg = a;
				found = true;
			}
		}
		if(found){
			autos.remove(gooiWeg);
			info = "Auto verwijderd";
			updateAutoList();
		}
		return info;
	}

	// ------------------------------Brandstof------------------------------//

	public String addBrandstof(Object o) {
		boolean found = false;
		if (o instanceof Brandstof) {
			Brandstof nieuw = (Brandstof) o;
			for (Brandstof b : brandstoffen) {
				if (b.getType().equals(nieuw.getType())) {
					found = true;
				}
			}
			if (!found) {
				brandstoffen.add((Brandstof) o);
				updateBrandstofList();
				return "Brandstof toevoegen geslaagd";
			}
			return "Brandstof met deze naam bestaat al";
		}
		return "";
	}

	// ------------------------------Onderdeel------------------------------//

	public String addOnderdeel(Object o) {
		boolean found = false;
		String info = "";
		if (o instanceof Onderdeel) {
			Onderdeel nieuw = (Onderdeel) o;
			for (Onderdeel a : onderdelen) {
				if (a.getArtikelNummer() == nieuw.getArtikelNummer()) {
					found = true;
				}
			}
			if (!found) {
				onderdelen.add((Onderdeel) o);
                updateOnderdeelList();
				return "Onderdeel toevoegen geslaagd";
			}
			return "Artikelnummer is niet uniek";
		}
		return info;
	}
	
	// -----------------------------Reservering-----------------------------//
	
	public String addReservering(Object o) {
		String info = "Reservering niet toegevoegd";
		if (o instanceof Reservering) {
				reserveringen.add((Reservering) o);
                updateReserveringList();
				return "Reservering toevoegen geslaagd";
		}
		return info;
	}
	
	public int checkPlaats(String datum){
		int count = 0;
		for(Reservering r : reserveringen){
			if(r.getDatum().equals(datum)){
				count++;
			}
		}
		if(count > 0){
			return 50-count;
		}else{
			return 50;
		}
	}
	// -----------------------------Bestelling------------------------------//
	
	public String addBestelling(Object o) {
		boolean found = false;
		String info = "";
		if (o instanceof Bestelling) {
			Bestelling nieuw = (Bestelling) o;
			for (Bestelling b : bestellingen) {
				if (b.getBesteldItem().equals(nieuw.getBesteldItem())) {
					found = true;
				}
			}
			if (!found) {
				bestellingen.add((Bestelling) o);
                updateBestellingList();
				return "Bestelling toevoegen geslaagd";
			}
			return "Er loopt al een bestelling";
		}
		return info;
	}
	
	// --------------------------------Klant--------------------------------//
	
	public String addKlant(Object o) {
		String info = "Klant toevoegen mislukt";
		if (o instanceof Klant) {
			//klanten.add((Klant)o);
			Klant nieuw = (Klant)o;
			for(Klant k : klanten){
				if(k.getNaam().equals(nieuw.getNaam())){
					return "Klant met deze gebruikers naam bestaat al";
				}
				System.out.println(k.getNaam()+k.getID());
			}
			klanten.add(nieuw);
            updateKlantList();
			return "Klant toegevoegd";
		}
		return info;
	}
	
	public int getNewKlantID(){
		int nextID = 1;
		for(Klant k : klanten){
			nextID++;
		}
		return nextID;
	}
	
	// --------------------------------Auto---------------------------------//
	
	public String addAuto(Object o) {
		String info = "Auto toevoegen mislukt";
		if (o instanceof Auto) {
			autos.add((Auto)o);
			for(AutoInterface a : autos){
				System.out.println(a.getMerk()+a.getID());
			}
            updateAutoList();
			return "Auto toegevoegd";
		}
		return info;
	}
	
	public int getNewAutoID(){
		int nextID = 1;
		for(AutoInterface a : autos){
			if(a.getID()>nextID){
				nextID = a.getID();
			}
		}
		nextID++;
		return nextID;
	}
	
	// -------------------------------Monteur-------------------------------//
	
	public String addMonteur(Object o) {
		String info = "Monteur toevoegen mislukt";
		if (o instanceof Monteur) {
			monteurs.add((Monteur)o);
			for(Monteur m : monteurs){
				System.out.println(m.getNaam()+m.getID());
			}
            updateMonteurList();
			return "Monteur toegevoegd";
		}
		return info;
	}
	
	public int getNewMonteurID(){
		int nextID = 1;
		for(Monteur m : monteurs){
			nextID++;
		}
		return nextID;
	}
	
	public boolean checkMonteurID(int id){
		for(Monteur m : monteurs){
			if(m.getID() == id){
				return false;
			}
		}
		return true;
	}
	
	// -----------------------------Werkzaamheid----------------------------//
	
	public String addWerkzaamheid(Object o) {
		String info = "Werkzaamheid toevoegen mislukt";
		if (o instanceof Werkzaamheid) {
			werkzaamheden.add((Werkzaamheid)o);
			for(Werkzaamheid w : werkzaamheden){
				System.out.println(w.getOmschrijving()+w.getID());
			}
            updateWerkzaamheidList();
			return "Werkzaamheid toegevoegd";
		}
		return info;
	}
	
	public int getNewWerkzaamheidID(){
		int nextID = 1;
		for(Werkzaamheid w : werkzaamheden){
			nextID++;
		}
		return nextID;
	}
	
	public boolean checkWerkzaamheidID(int id){
		for(Werkzaamheid w : werkzaamheden){
			if(w.getID() == id){
				return false;
			}
		}
		return true;
	}
	
	// --------------------------------Klus---------------------------------//
	
	public String addKlus(Object o) {
		String info = "Klus toevoegen mislukt";
		if (o instanceof Klus) {
			klussen.add((Klus)o);
			for(Klus k : klussen){
				System.out.println(k.getDatum()+k.getID());
			}
            updateKlusList();
			return "Klus toegevoegd";
		}
		return info;
	}
	
	public int getNewKlusID(){
		int nextID = 1;
		for(Klus k : klussen){
			nextID++;
		}
		return nextID;
	}
	
	public boolean checkKlusID(int id){
		for(Klus k : klussen){
			if(k.getID() == id){
				return false;
			}
		}
		return true;
	}
	// -----------------------------Werknemer-------------------------------//
	
	public String addWerknemer(Object o) {
		String info = "Werknemer toevoegen mislukt";
		if (o instanceof Werknemer) {
			werknemers.add((Werknemer)o);
			for(Werknemer w : werknemers){
				System.out.println(w.getNaam()+w.getID()+"Rechten:"+w.getRechten());
			}
            updateWerknemerList();
			return "Werknemer toegevoegd";
		}
		return info;
	}
	
	public int getNewWerknemerID(){
		int nextID = 0;
		for(Werknemer w : werknemers){
			if(w.getID()>nextID){
				nextID = w.getID();
			}
		}
		nextID++;
		return nextID;
	}
	
	// ------------------------------Updates--------------------------------//
	
	public void updateOnderdeelList(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/users/Public/database/onderdelen.db")));
			oos.writeObject(onderdelen);
			oos.close();
		} catch (Exception e) {
		}
	}
	
	public void updateBrandstofList(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/users/Public/database/brandstoffen.db")));
			oos.writeObject(brandstoffen);
			oos.close();
		} catch (Exception e) {
		}
	}
	
	public void updateReserveringList(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/users/Public/database/reserveringen.db")));
			oos.writeObject(reserveringen);
			oos.close();
		} catch (Exception e) {
		}
	}
	
	public void updateBestellingList(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/users/Public/database/bestellingen.db")));
			oos.writeObject(bestellingen);
			oos.close();
		} catch (Exception e) {
		}
	}
	
	public void updateMonteurList(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/users/Public/database/monteurs.db")));
			oos.writeObject(monteurs);
			oos.close();
		} catch (Exception e) {
		}
	}
	
	public void updateWerkzaamheidList(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/users/Public/database/werkzaamheden.db")));
			oos.writeObject(werkzaamheden);
			oos.close();
		} catch (Exception e) {
		}
	}
	
	public void updateKlusList(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/users/Public/database/klussen.db")));
			oos.writeObject(klussen);
			oos.close();
		} catch (Exception e) {
		}
	}
	
	public void updateKlantList(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/users/Public/database/klanten.db")));
			oos.writeObject(klanten);
			oos.close();
		} catch (Exception e) {
		}
	}
	
	public void updateAutoList(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/users/Public/database/autos.db")));
			oos.writeObject(autos);
			oos.close();
		} catch (Exception e) {
		}
	}
	
	public void updateWerknemerList(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("C:/users/Public/database/werknemers.db")));
			oos.writeObject(werknemers);
			oos.close();
		} catch (Exception e) {
		}
	}
}
