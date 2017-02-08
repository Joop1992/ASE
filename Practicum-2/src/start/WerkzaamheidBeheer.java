package start;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class WerkzaamheidBeheer extends HttpServlet{
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
		
		String omschrijving =(String)req.getParameter("omschrijving");  
		String ID =(String)req.getParameter("ID");  
		if(omschrijving == ""){
			filledIn = false;
			errMessage += "Vul een omschrijving in;";
		}
		if(ID == ""){
			filledIn = false;
			errMessage += "'Vul een identificatie nummer in";
		}else{
			IDI = Integer.parseInt(ID);
			if(!db.checkWerkzaamheidID(IDI)){
				filledIn = false;
				errMessage += "ID van de werkzaamheid is niet uniek;";
			}
		}

		if (req.getParameter("toevoegen") != null && filledIn){
			Werkzaamheid w = new Werkzaamheid(omschrijving, IDI);
			errMessage = db.addWerkzaamheid(w);
			req.setAttribute("msgs", errMessage);
			rd = req.getRequestDispatcher("WerkzaamheidBeheer.jsp");
		}else if (req.getParameter("annuleren")!=null){
			req.setAttribute("msgs", errMessage);
			rd = req.getRequestDispatcher("KlantBeheer.jsp");
		}
		rd.forward(req, resp);
	}
}