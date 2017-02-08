package start;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Monteur implements Serializable{

	private String naam, achterNaam;
	private int ID;

	public Monteur(String nm, String anm, int i){
		naam = nm.replaceAll(" ","");
		achterNaam = anm.replaceAll(" ","");
		ID = i;
	}

	public int getID(){
		return ID;
	}

	public String getNaam(){
		return naam;
	}

	public String getAchterNaam(){
		return achterNaam;
	}
}