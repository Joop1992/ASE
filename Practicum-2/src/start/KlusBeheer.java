package start;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class KlusBeheer extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean filledIn = true;
		RequestDispatcher rd = null;
		Database db = new Database();
		//ArrayList<Monteur> monteurs = new ArrayList<Monteur>();
		//monteurs = db.getList("monteurs");
		resp.setContentType("text/html");
		String errMessage = "";
		int IDI = 1;
		
		String klant = (String)req.getParameter("klant"); 
		String auto =(String)req.getParameter("auto");  
		String monteur =(String)req.getParameter("monteur");  
		String monteurIDs = req.getParameter("monteurLijst");
		String werkzaamhedenIDs = req.getParameter("werkzaamhedenLijst");
		String werkzaamheid =(String)req.getParameter("werkzaamheid");
		String dag =(String)req.getParameter("dag");
		String maand =(String)req.getParameter("maand");
		String jaar =(String)req.getParameter("jaar");
		String ID = (String)req.getParameter("ID");
		
		if(klant == ""){
			filledIn = false;
			errMessage += "Selecteer een klant; ";
		}
		if(auto == ""){
			filledIn = false;
			errMessage += "Selecteer een auto; ";
		}
		if(monteurIDs.equals("")){
			filledIn = false;
			errMessage += "Voeg eerst een monteur toe; ";
		}
		if(werkzaamhedenIDs.equals("")){
			filledIn = false;
			errMessage += "Voeg eerst een werkzaamheid toe; ";
		}
		if(dag.equals("Selecteer een dag")){
			errMessage += dag+" ;";
			filledIn = false;
		}
		if(maand.equals("Selecteer een maand")){
			errMessage += maand+" ;";
			filledIn = false;
		}
		if(jaar.equals("Selecteer een jaar")){
			errMessage += jaar+" ;";
			filledIn = false;
		}
		if(ID == ""){
			filledIn = false;
			errMessage += "'Vul een identificatie nummer in; ";
		}else{
			IDI = Integer.parseInt(ID);
			if(!db.checkKlusID(IDI)){
				filledIn = false;
				errMessage += "ID van de klus is niet uniek;";
			}
		}

		if (req.getParameter("toevoegen") != null && filledIn){
			String kID[] = klant.split(":");
			int klantID = Integer.parseInt(kID[0]);
			String aID[] = auto.split(":");
			int autoID = Integer.parseInt(aID[0]);
			Klus k = new Klus(dag+"-"+maand+"-"+jaar,"Ingepland", klantID, autoID, monteurIDs, werkzaamhedenIDs, IDI);
			errMessage = db.addKlus(k);
			req.setAttribute("msgs", errMessage);
			rd = req.getRequestDispatcher("KlusBeheer.jsp");
		}else if (req.getParameter("annuleren") != null){
			if(req.getSession().getAttribute("rechten")!=null){
				rd = req.getRequestDispatcher("KlantBeheer.jsp");
			}else{
				rd = req.getRequestDispatcher("StartPagina.jsp");
			}
		}else if(req.getParameter("MonteurToevoegen") != null && monteur != "Selecteer een monteur"){
			if(!monteur.equals("Selecteer een monteur")){
				String strings[] = monteur.split(":");
				String scanMonteurs = monteurIDs;
				if(monteurIDs.equals("")){
					monteurIDs = strings[0];
				}else{
					monteurIDs += ","+  strings[0];
				}
				Scanner sc = new Scanner(scanMonteurs);
				sc.useDelimiter(",");
				while(sc.hasNext()){
					if(sc.next().equals(strings[0])){
						req.setAttribute("klusMonteurs",scanMonteurs);
						req.setAttribute("klusWerkzaamheden",werkzaamhedenIDs);
						req.setAttribute("msgs", "Deze monteur is al toegevoegd");
						rd = req.getRequestDispatcher("KlusBeheer.jsp");
						sc.close();
						rd.forward(req, resp);
						return;
					}
				}
				sc.close();
				req.setAttribute("klusMonteurs",monteurIDs);
				req.setAttribute("klusWerkzaamheden",werkzaamhedenIDs);
				req.setAttribute("msgs", "Monteur "+monteur+" toegevoegd");
				rd = req.getRequestDispatcher("KlusBeheer.jsp");
			}else{
				req.setAttribute("klusMonteurs",monteurIDs);
				req.setAttribute("klusWerkzaamheden",werkzaamhedenIDs);
				req.setAttribute("msgs", "Selecteer een monteur");
				rd = req.getRequestDispatcher("KlusBeheer.jsp");
			}
			
		}else if(req.getParameter("WerkzaamheidToevoegen") != null && werkzaamheid != "Selecteer een werkzaamheid"){
			if(!werkzaamheid.equals("Selecteer een werkzaamheid")){
				String strings[] = werkzaamheid.split(":");
				String scanWerkzaamheden = werkzaamhedenIDs;
				if(werkzaamhedenIDs.equals("")){
					werkzaamhedenIDs = strings[0];
				}else{
					werkzaamhedenIDs += ","+  strings[0];
				}
				Scanner sc = new Scanner(scanWerkzaamheden);
				sc.useDelimiter(",");
				while(sc.hasNext()){
					if(sc.next().equals(strings[0])){
						req.setAttribute("klusMonteurs",monteurIDs);
						req.setAttribute("klusWerkzaamheden",scanWerkzaamheden);
						req.setAttribute("msgs", "Deze werkzaamheid is al toegevoegd");
						rd = req.getRequestDispatcher("KlusBeheer.jsp");
						sc.close();
						rd.forward(req, resp);
						return;
					}
				}
				sc.close();
				req.setAttribute("klusMonteurs",monteurIDs);
				req.setAttribute("klusWerkzaamheden",werkzaamhedenIDs);
				req.setAttribute("msgs", "Werkzaamheid "+werkzaamheid+" toegevoegd");
				rd = req.getRequestDispatcher("KlusBeheer.jsp");
			}else{
				req.setAttribute("klusMonteurs",monteurIDs);
				req.setAttribute("klusWerkzaamheden",werkzaamhedenIDs);
				req.setAttribute("msgs", "Selecteer een werkzaamheid");
				rd = req.getRequestDispatcher("KlusBeheer.jsp");
			}
		}else{
			req.setAttribute("msgs", errMessage);
			rd = req.getRequestDispatcher("KlusBeheer.jsp");
		}
		req.getSession().removeAttribute("klantID");
		rd.forward(req, resp);
	}
}