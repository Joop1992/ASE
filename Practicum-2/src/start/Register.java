package start;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Register extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean RegisterSuccess = true;
		boolean wwCheck = false;
		boolean eCheck = false;
		Database db = new Database();
		ArrayList<User> users = new ArrayList<User>();
		String errMessage = "Vul alstublieft deze gegevens in: ";

		String gebruikersNaam = req.getParameter("gebruikersNaam");
		String naam = req.getParameter("naam");
		String w1 = req.getParameter("wachtwoord1");
		String w2 = req.getParameter("wachtwoord2");
		String e1 = req.getParameter("email1");
		String e2 = req.getParameter("email2");
		String autoMerk = req.getParameter("autoMerk");
		String kenteken = req.getParameter("kenteken");
		System.out.println("*"+gebruikersNaam+"*");
		resp.setContentType("text/html");

		if(gebruikersNaam == ""){errMessage += "gebruikersnaam; ";RegisterSuccess = false;}
		if(naam == ""){ errMessage += "naam; ";RegisterSuccess = false;}
		if(w1 == ""){ errMessage += "wachtwoord; ";RegisterSuccess = false;}
		if(w2 == ""){ errMessage += "herhaarlde wachtwoord; ";RegisterSuccess = false;}
		if(e1 == ""){ errMessage += "email; ";RegisterSuccess = false;}
		if(e2 == ""){ errMessage += "herhaalde email; ";RegisterSuccess = false;}
		if(autoMerk == ""){ errMessage += "autoMerk; ";RegisterSuccess = false;}
		if(kenteken == ""){ errMessage += "kenteken; ";RegisterSuccess = false;}
		if(e1.equals(e2)){eCheck = true;}
		if(w1.equals(w2)){wwCheck = true;}
		if(!wwCheck){
			RegisterSuccess = false;
			errMessage = "Uw wachtwoorden komen niet overeen";}
		if(!eCheck){
			if(!wwCheck){
				RegisterSuccess = false; 
				errMessage += " en uw emailadressen komen niet overeen";
			}else{
				RegisterSuccess = false; 
				errMessage = "Uw emailadressen komen niet overeen";	
			}
		}
		RequestDispatcher rd = null;
		
		if (RegisterSuccess){
			User nieuw = new User(gebruikersNaam, w1);
			users.add(nieuw);
			int klantID = db.getNewKlantID();
			int autoID = db.getNewAutoID();
			Klant k = new Klant(gebruikersNaam, e1, w1, klantID);
			Auto a = new Auto(autoMerk, kenteken, autoID);
			a.setKlantNummer(klantID);
			errMessage = db.addKlant(k);
			if(errMessage.equals("Klant toegevoegd")){
				db.addAuto(a);
				rd = req.getRequestDispatcher("Login.jsp");
			}else{
				rd = req.getRequestDispatcher("Register.jsp");
			}
			
			req.getServletContext().setAttribute("userArray", users);
			System.out.println("Arraylist:"+users);

			ServletContext s=getServletContext();  
			s.setAttribute("gebruiker", gebruikersNaam);  
			s.setAttribute("wachtwoord",w1); 
		}else{
			rd = req.getRequestDispatcher("Register.jsp");
		}
		req.setAttribute("msgs", errMessage);
		rd.forward(req, resp);
	}
}

