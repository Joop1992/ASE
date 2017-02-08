package start;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Login extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean LoginSuccess = false; 
		boolean verified = false;
		boolean loginWerknemer = false;
		String rechten;
		ArrayList<User> users = (ArrayList<User>) req.getServletContext().getAttribute("userArray");
		Database db = new Database();
		ArrayList<Klant> klanten = db.getList("klanten");
		ArrayList<Werknemer> werknemers = db.getList("werknemers");
		if(users == null){
			System.out.println("nieuwe arraylist aanmaken");
			ArrayList<User> nieuw = new ArrayList<User>();
			users = nieuw;
		}
		
		String errMessage = "Vul alstublieft deze gegevens in: ";
		RequestDispatcher rd = null;

		String gebruikersNaam = req.getParameter("gebruikersNaam");
		String wachtwoord = req.getParameter("wachtwoord");

		resp.setContentType("text/html");
		
		if(gebruikersNaam == ""){errMessage += "gebruikersnaam; ";LoginSuccess = false;}
		if(wachtwoord == ""){ errMessage += "naam; ";LoginSuccess = false;}
		
		for(Klant k : klanten){
			System.out.println("LOGIN:::::"+k.getNaam()+"-"+k.getWachtwoord());
			System.out.println("LOGIN:::::"+gebruikersNaam+"-"+wachtwoord);
			if(k.getNaam().equals(gebruikersNaam) && k.getWachtwoord().equals(wachtwoord)){
				verified = true;
				System.out.println("hier wel???::::"+gebruikersNaam+" "+wachtwoord);
				req.getSession().setAttribute("user", k);
			}
		}
		
		for(Werknemer w : werknemers){
			if(w.getNaam().equals(gebruikersNaam) && w.getWachtwoord().equals(wachtwoord)){
				rechten = w.getRechten();
				req.getSession().setAttribute("rechten", rechten);
				loginWerknemer = true;
				//Attribuut rechten moet ergens ook een x verwijderd worden!!!!!!!!!!!!!!!!!!!!!!!
			}
		}
		
		if(verified){
			System.out.println("login succusvol");
			LoginSuccess = true;
            
		}else{
			errMessage = "Uw Gebruiksnaam en/of wachtwoord is incorrect";
			LoginSuccess = false;
		}
		
		if(req.getParameter("registreren")!= null){
			rd = req.getRequestDispatcher("Register.jsp");
		}else if (LoginSuccess){
			req.getSession().setAttribute("gebruiker", gebruikersNaam);  
			req.getSession().setAttribute("wachtwoord",wachtwoord);
			rd = req.getRequestDispatcher("StartPagina.jsp");	
		}else if(loginWerknemer){
			rd = req.getRequestDispatcher("Navigatie.jsp");
		}else{
			req.setAttribute("msgs", errMessage);
			rd = req.getRequestDispatcher("Login.jsp");
		}
		rd.forward(req, resp);
	}
}

