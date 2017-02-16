package start;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GegevensBeheer extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String huidigWachtwoord;
	String huidigeGebruikersNaam;
	String wachtwoord;
	String gebruikersNaam;
	String nieuwWachtwoord1;
	String nieuwWachtwoord2;
	int klantID;
	Database db = new Database();
	ArrayList<Klant> klanten = db.getList("klanten");

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		boolean wijzigen = true;
		RequestDispatcher rd = null;
		String errMessage = "";
		resp.setContentType("text/html");
		
		try{
			klantID = (Integer)req.getSession().getAttribute("klantID");
		}catch(Exception e){e.printStackTrace();}
		
		gebruikersNaam = (String)req.getParameter("naam");
		wachtwoord = (String)req.getParameter("oudWachtwoord");
		nieuwWachtwoord1 = (String)req.getParameter("nieuwWachtwoord1");
		nieuwWachtwoord2 = (String)req.getParameter("nieuwWachtwoord2");
		
		for(Klant k : klanten){
			if(k.getID() == klantID){
				huidigWachtwoord = k.getWachtwoord();
				huidigeGebruikersNaam = k.getNaam();
			}
		}
		
		if(gebruikersNaam.isEmpty()){
			errMessage += "Vul een nieuwe gebruikersnaam in; ";
		}
		
		if(wachtwoord.isEmpty()){
			errMessage += "Vul uw huidige wachtwoord in; ";
		}else if(!wachtwoord.equals(huidigWachtwoord)){
			errMessage += "Uw oude wachtwoord klopt niet; ";
		}
		
		if(nieuwWachtwoord1.isEmpty()){
			errMessage += "Vul een nieuw wachtwoord in; ";
		}
		
		if(nieuwWachtwoord2.isEmpty()){
			errMessage += "Herhaal nieuwe wachtwoord; ";
		}
		
		if(!nieuwWachtwoord1.equals(nieuwWachtwoord2)){
			errMessage += "Uw nieuw wachtwoorden komen niet overeen";
		}
		
		if(!errMessage.isEmpty()){
			wijzigen = false;
		}

		if (req.getParameter("wijzigen") != null) {
			if(wijzigen){
				for(Klant k : klanten){
					if(k.getID() == klantID){
						k.setWachtwoord(nieuwWachtwoord1);
						k.setNaam(gebruikersNaam);
						db.updateKlantList();
						req.getSession().removeAttribute("user");
					}
				}
				rd = req.getRequestDispatcher("Login.jsp");
			}else{
				rd = req.getRequestDispatcher("GegevensBeheer.jsp");
			}
			req.setAttribute("msgs", errMessage);
		}else if (req.getParameter("annuleren") != null) {
			rd = req.getRequestDispatcher("StartPagina.jsp");
		}
		rd.forward(req, resp);
	}
}