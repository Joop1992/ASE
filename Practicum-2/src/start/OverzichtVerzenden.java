package start;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class OverzichtVerzenden extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		RequestDispatcher rd = null;
		resp.setContentType("text/html");
		Database db = new Database();
		String overzicht = "";
		String errMessage = "";
		
		String maand =(String)req.getParameter("maand"); 
		for(int i = 1; i <= 31; i++){
			int aantalBezet = 50-db.checkPlaats(i+"-"+maand);
			overzicht += "Op "+i+"-"+maand+" waren er "+aantalBezet+" parkeerplekken bezet\n";
		}
		System.out.println(overzicht);
		
		
		if(req.getParameter("genereer")!=null){
			errMessage = "Verzenden geslaagd";
			rd = req.getRequestDispatcher("OverzichtVerzenden.jsp");
		}else if(req.getParameter("annuleren")!=null){
			rd = req.getRequestDispatcher("Parkeergarage.jsp");
		}
		req.setAttribute("msgs", errMessage);
		if(rd != null){
			rd.forward(req, resp);
		}
	}
}