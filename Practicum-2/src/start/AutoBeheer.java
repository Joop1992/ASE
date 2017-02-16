package start;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoBeheer extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String merk;
	String kenteken;
	String klantNummer;
	int klantID;
	Database db = new Database();
	ArrayList<Auto> autos = db.getList("autos");

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		boolean toevoegen = true;
		RequestDispatcher rd = req.getRequestDispatcher("AutoBeheer.jsp");
		String errMessage = "";
		resp.setContentType("text/html");
        
		klantNummer = (String)req.getParameter("klant");
		if(klantNummer != null){
			String kNr[] = klantNummer.split(":");
			klantID = Integer.parseInt(kNr[0]);
		}
		merk = (String)req.getParameter("merk");
		kenteken = req.getParameter("kenteken");
		
		if(merk.isEmpty()){
			toevoegen = false;
			errMessage += "Voer een merk in; ";
		}
		if(kenteken.isEmpty()){
			toevoegen = false;
			errMessage += "Voer een kenteken in;";
		}

		if (req.getParameter("autotoevoegen") != null) {
			if(toevoegen){
				Auto a = new Auto(merk, kenteken, db.getNewAutoID());
				a.setKlantNummer(klantID);
				db.addAuto(a);
				errMessage = "Auto toegevoegd";

			}
		}else if(req.getParameter("annuleren") != null){
			rd = req.getRequestDispatcher("StartPagina.jsp");
		}else if(req.getParameter("verwijderen") != null){
			try{
				String autoGegevens = (String)req.getParameter("auto");
				int autoID = Integer.parseInt(autoGegevens.split("|")[0]);
				errMessage = db.removeAuto(autoID);
			}catch(Exception e){e.printStackTrace();}
		}
		req.setAttribute("msgs", errMessage);
		rd.forward(req, resp);
	}
}