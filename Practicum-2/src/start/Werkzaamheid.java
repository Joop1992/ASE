package start;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Werkzaamheid implements Serializable{

	private String omschrijving;
	private int ID;

	public Werkzaamheid(String o, int i){
		omschrijving = o.replaceAll(" ","");
		ID = i;
	}

	public int getID(){
		return ID;
	}

	public String getOmschrijving(){
		return omschrijving;
	}
}