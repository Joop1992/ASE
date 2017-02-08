package start;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartPagina extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String wachtwoord;
	String gebruikersNaam;

	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		boolean UitlogSuccess = false;
		RequestDispatcher rd = null;
		String errMessage = "";
		Database db = new Database();
		resp.setContentType("text/html");
		req.getSession().setAttribute("klantID", "-1");
		req.getSession().removeAttribute("rechten");

		gebruikersNaam = (String)req.getSession().getAttribute("gebruiker");
		wachtwoord = (String)req.getSession().getAttribute("wachtwoord");
		System.out.println("Inloggegevens: "+gebruikersNaam+"  "+wachtwoord);

		if (req.getParameter("uitloggen") != null) {
			req.getSession().removeAttribute("user");
			UitlogSuccess = true;
			errMessage = "Uitloggen geslaagd";
			req.setAttribute("msgs", errMessage);
			rd = req.getRequestDispatcher("Login.jsp");
		
			
		}else{
			ArrayList<Klant> klanten = db.getList("klanten");
			int klantID = 0;
			for(Klant k : klanten){
				System.out.println(":::::"+k.getNaam()+"-"+k.getWachtwoord());
				System.out.println(":::::"+gebruikersNaam+"-"+wachtwoord);
				if(k.getNaam().equals(gebruikersNaam) && k.getWachtwoord().equals(wachtwoord)){
					System.out.println("match gevonden");
					klantID = k.getID();
					System.out.println("Check1: "+klantID);
				}
			}
			req.getSession().setAttribute("klantID", klantID);
			if(req.getParameter("klusinplannen") != null){
				rd = req.getRequestDispatcher("KlusBeheer.jsp");
			}else if(req.getParameter("autotoevoegen") != null){
				rd = req.getRequestDispatcher("AutoBeheer.jsp");
			}else if(req.getParameter("gegevenswijzigen") != null){
				rd = req.getRequestDispatcher("GegevensBeheer.jsp");
			}
		}
		rd.forward(req, resp);
	}
}