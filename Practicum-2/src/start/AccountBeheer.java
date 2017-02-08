package start;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountBeheer extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		boolean toevoegen = true;
		RequestDispatcher rd = null;
		String errMessage = "";
		Database db = new Database();
		resp.setContentType("text/html");
		
		String wachtwoord;
		String gebruikersNaam;
		int IDI = 0;
		String ID;
		String rechten = "", rechtenAdmin, rechtenWerkplaats, rechtenVoorraadBeheer, rechtenParkeergarage, rechtenBalieMedewerker;

		gebruikersNaam = (String)req.getParameter("naam");
		wachtwoord = (String)req.getParameter("wachtwoord");
		System.out.println("Inloggegevens: "+gebruikersNaam+"  "+wachtwoord);
		ID = (String)req.getParameter("ID");
		rechtenWerkplaats = req.getParameter("werkplaats");
		rechtenVoorraadBeheer = req.getParameter("voorraadbeheer");
		rechtenParkeergarage = req.getParameter("parkeergarage");
		rechtenBalieMedewerker = req.getParameter("baliemedewerker");
		rechtenAdmin = req.getParameter("admin");
		
		if(gebruikersNaam.equals("")){
			toevoegen = false;
			errMessage = "Vul een gebruikersnaam in";
		}
		
		if(wachtwoord.equals("")){
			toevoegen = false;
			errMessage += "Vul een wachtwoord in";
		}
		
		if(ID == ""){
			toevoegen = false;
			errMessage += "'Vul een identificatie nummer in; ";
		}else{
			IDI = Integer.parseInt(ID);
		}
		
		if(rechtenWerkplaats != null){
			rechten += "1,";
		}else{
			rechten += "-,";
		}
		
		if(rechtenVoorraadBeheer != null){
			rechten += "2,";
		}else{
			rechten += "-,";
		}
		
		if(rechtenParkeergarage != null){
			rechten += "3,";
		}else{
			rechten += "-,";
		}
		
		if(rechtenBalieMedewerker != null){
			rechten += "4,";
		}else{
			rechten += "-,";
		}
		
		if(rechtenAdmin != null){
			rechten += "5,";
		}else{
			rechten += "-,";
		}

		if (req.getParameter("aanmaken") != null) {
			if(toevoegen){
			    Werknemer nieuw = new Werknemer(gebruikersNaam, wachtwoord, IDI);	
			    nieuw.setRechten(rechten);
			    db.addWerknemer(nieuw);
			    errMessage = "Werknemer aangemaakt";
			}
			req.setAttribute("msgs", errMessage);
			rd = req.getRequestDispatcher("AccountBeheer.jsp");
		
		}else if(req.getParameter("annuleren") != null){
			rd = req.getRequestDispatcher("Navigatie.jsp");
		}
		rd.forward(req, resp);
	}
}