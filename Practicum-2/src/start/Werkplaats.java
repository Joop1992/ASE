package start;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Werkplaats extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean klusGevonden = false; 
		RequestDispatcher rd = req.getRequestDispatcher("Werkplaats.jsp");
		String errMessage = "";		
		resp.setContentType("text/html");
		Database db = new Database();
		ArrayList<Klus> klussen = db.getList("klussen");
		ArrayList<Auto> autos = db.getList("autos");
		ArrayList<Monteur> monteurs = db.getList("monteurs");
		ArrayList<Werkzaamheid> werkzaamheden = db.getList("werkzaamheden");
		ArrayList<Onderdeel> onderdelen = db.getList("onderdelen");
		int klusID = -1;
		Klus selected = new Klus("","", -1, -1, "", "", 0);
		
		if((String)req.getParameter("selecteerDatum") != null){
			String nieuweDatum = (String)req.getParameter("datum");
			String datumKlusID[] = nieuweDatum.split(":");
			int id = Integer.parseInt(datumKlusID[1]);
			for(Klus k : klussen){
				if(k.getID() == id){
					req.getSession().setAttribute("klusID", k.getID()+"");
					break;
				}
			}
		}
		try{
			klusID = Integer.parseInt((String)req.getSession().getAttribute("klusID"));
		}catch(Exception e){System.out.println(e);}
		if(klusID > 0){
			for(Klus k : klussen){
				if(k.getID() == klusID){
					selected = k;
					klusGevonden = true;
				}
			}
		}
		if(klusGevonden){
			if ((String)req.getParameter("addA")!= null){
				 String onderdeel = (String)req.getParameter("onderdeel");
				 if(onderdeel != null){
					 String alleOnderdeelNummers = selected.getOnderdelen();
					 String o[] = onderdeel.split(":");
					 selected.setOnderdelen(alleOnderdeelNummers+","+o[0]);
				 }else{
					 errMessage = "Selecteer eerst een onderdeel";
				 }
			}else if((String)req.getParameter("addU") != null){
				errMessage = "Uren toevoegen geslaagd";
				int uren = 0;
				try{
					uren = Integer.parseInt((String)req.getParameter("AantalUren"));
				}catch(Exception e){System.out.println(e);errMessage = "Uren toevoegen mislukt";}
				int huidigeUren = selected.getUren();
				selected.setUren(huidigeUren+uren);
			}else if((String)req.getParameter("changeS") != null){
				String nieuweStatus = (String)req.getParameter("status");
				if(!nieuweStatus.equals("Selecteer status")){
					selected.setStatus(nieuweStatus);
					errMessage = "Status veranderd";
				}else{
					errMessage = "Selecteer eerst een status";
				}
			}else if((String)req.getParameter("previous") != null){
				errMessage = "Dit is de eerste klus van vandaag";
				for(Klus k : klussen){
					if(k.getDatum().equals(selected.getDatum()) && k.getID() < selected.getID()){
						req.getSession().setAttribute("klusID", k.getID()+"");
						selected = k;
		                errMessage = "";
					}
				}
			}else if((String)req.getParameter("next") != null){
				errMessage = "Dit is de laatste klus van vandaag";
				for(Klus k : klussen){
					if(k.getDatum().equals(selected.getDatum()) && k.getID() > selected.getID()){
						req.getSession().setAttribute("klusID", k.getID()+"");
						selected = k;
						errMessage = "";
						break;
					}
				}				
			}
			db.updateKlusList();
			//Gegevens van de klus in strings zetten voor de jsp pagina:
			String status = selected.getStatus();
			String auto = "";
			int autoID = selected.getAutoID();
			for(AutoInterface a : autos){
				if(a.getID() == autoID){
					auto = a.getMerk()+" - "+a.getKenteken();
				}
			}
			String werkNemers = "";
			Scanner sc = new Scanner(selected.getWerknemers());
			sc.useDelimiter(",");
			while(sc.hasNext()){
				int monteurNummer = Integer.parseInt(sc.next());
				for(Monteur m : monteurs){
					if(m.getID() == monteurNummer){
						werkNemers += m.getNaam()+" "+m.getAchterNaam()+"; "; 
					}
				}
			}
			
			String werk = "";
			sc = new Scanner(selected.getWerk());
			sc.useDelimiter(",");
			while(sc.hasNext()){
				int werkNummer = Integer.parseInt(sc.next());
				for(Werkzaamheid w : werkzaamheden){
					if(w.getID() == werkNummer){
						werk += w.getOmschrijving()+"; "; 
					}
				}
			}
			String items = "";
			sc = new Scanner(selected.getOnderdelen());
			sc.useDelimiter(",");
			while(sc.hasNext()){
				int ondNummer = Integer.parseInt(sc.next());
				for(Onderdeel o : onderdelen){
					if(o.getArtikelNummer() == ondNummer){
						items += o.getOnderdeelNaam()+"; "; 
					}
				}
			}
			int uren = selected.getUren();
			//Einde gegevens in strings zetten
			req.getSession().setAttribute("klusStatus", status);
			req.getSession().setAttribute("klusAuto", auto);
			req.getSession().setAttribute("klusMonteurs", werkNemers);
			req.getSession().setAttribute("klusWerkzaamheden", werk);
			req.getSession().setAttribute("klusOnderdelen", items);
			req.getSession().setAttribute("klusUren", uren+"");
		}else{
			errMessage = "Er zijn nog geen klussen aangemaakt";
		}
		req.setAttribute("msgs", errMessage);
		rd.forward(req, resp);
	}
}