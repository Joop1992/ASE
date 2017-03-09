package start;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class KlantToevoegen extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean toevoegen = true;
		RequestDispatcher rd = null;
		Database db = new Database();
		resp.setContentType("text/html");
		String errMessage = "Klant toegevoegd";

		String gebruikersNaam = (String)req.getParameter("gebruikersNaam");
		String emailAdres = (String)req.getParameter("emailAdres");
		
		if(gebruikersNaam.equals("")){
			toevoegen = false;
			errMessage = "Vul een gebruikersnaam in; ";
		}
		
		if(emailAdres.equals("")){
			toevoegen = false;
			errMessage += "Vul een email adres in";
		}
		
		if (req.getParameter("klantToevoegen") != null){
			if(toevoegen){
				errMessage = db.addKlant(new Klant(gebruikersNaam, emailAdres, "ATD", db.getNewKlantID()));
			}
			rd = req.getRequestDispatcher("KlantToevoegen.jsp");
		}else if(req.getParameter("annuleren")!= null){
			rd = req.getRequestDispatcher("KlantBeheer.jsp");
		}
		req.setAttribute("msgs", errMessage);
		if(rd != null){
			rd.forward(req, resp);
		}
	}
}