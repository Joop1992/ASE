package start;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class MonteurBeheer extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean filledIn = true;
		RequestDispatcher rd = null;
		Database db = new Database();
		resp.setContentType("text/html");
		String errMessage = "";
		int IDI = 1;
		
		String naam =(String)req.getParameter("naamMonteur"); 
		String achterNaam =(String)req.getParameter("achternaamMonteur");  
		String ID =(String)req.getParameter("ID");  
		if(naam == ""){
			filledIn = false;
			errMessage += "Vul een naam in;";
		}
		if(achterNaam == ""){
			filledIn = false;
			errMessage += "Vul een achternaam in;";
		}
		if(ID == ""){
			filledIn = false;
			errMessage += "'Vul een identificatie nummer in";
		}else{
			IDI = Integer.parseInt(ID);
			if(!db.checkMonteurID(IDI)){
				filledIn = false;
				errMessage += "ID van de monteur is niet uniek;";
			}
		}

		if (req.getParameter("toevoegen") != null && filledIn){
			Monteur m = new Monteur(naam, achterNaam, IDI);
			errMessage = db.addMonteur(m);
			req.setAttribute("msgs", errMessage);
			rd = req.getRequestDispatcher("MonteurBeheer.jsp");
		}else if(req.getParameter("annuleren")!=null){
			req.setAttribute("msgs", errMessage);
			rd = req.getRequestDispatcher("KlantBeheer.jsp");
		}
		rd.forward(req, resp);
	}
}