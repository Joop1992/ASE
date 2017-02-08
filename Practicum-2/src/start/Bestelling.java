package start;

import java.io.Serializable;

public class Bestelling implements Serializable{
	private String besteldItem;
	private int aantal;
	
	public Bestelling(String bI, int a ){
		besteldItem = bI;
		aantal = a;
	}
	
	public String getBesteldItem(){
		return besteldItem;
	}
	
	public int getAantal(){
		return aantal;
	}

}
