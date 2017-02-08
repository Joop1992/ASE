package start;

import java.io.Serializable;

public class Brandstof implements Serializable{

	private String type;
	private int liters;
	private int TSIC;
    //Database db = new Database();

	public Brandstof(String tp, int tC){
		type = tp;
		liters = 5000;
		TSIC = tC;
	}

	public String getType(){
		return type;
	}

	public void setType(String tp){
		type = tp;
	}

	public int getTSIC(){
		return TSIC;
	}

	public void setTSIC(int t){
		TSIC = t;
	}

	public int getLiters(){ 
		return liters;
	}

	public void setLiters(int newlt){ //voorraad instellen
		liters = newlt;
	}

	public void verwerkTankBeurt(int lt){ // een gesimuleerde tankbeurt verwerken
		if(liters-lt > 0){
			setLiters(liters-lt);
		}	
		if(liters <= 500){ // als de voorraad lager dan 10% is bijbestellen
			//Bestelling b = new Bestelling(type,5000-getLiters());
			//db.addBestelling(b);
		}
	}
}

