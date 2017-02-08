package start;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VoorraadBeheer extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean toevoegen = false;
		RequestDispatcher rd = null;
		Database db = new Database();
		int id = -1;
		String errMessage = "";
		resp.setContentType("text/html");

		String deleteItem = req.getParameter("deleteItem");
		String voorraadItem = req.getParameter("Voorraad item");
		String type = req.getParameter("selectType");
		String naam = req.getParameter("naam");
		if (naam != "") {
			toevoegen = true;
		} else {
			errMessage += "Vul iets in in het veld naam;"; 
		}

		try {
			id = Integer.parseInt(req.getParameter("id"));
			System.out.println("id: " + id);
			toevoegen = true;
		} catch (Exception e) {
			toevoegen = false;
			errMessage += " Vul een getal in bij artikelnummer/tsic;";
		}
		ArrayList<Brandstof> brandstoffen = db.getList("brandstoffen");
		for (Brandstof b : brandstoffen) {
			b.verwerkTankBeurt(255);
			if(b.getLiters() < 500){
				Bestelling best = new Bestelling(b.getType(),5000-b.getLiters());
				db.addBestelling(best);
			}
			db.updateBrandstofList();
		}

		if (req.getParameter("item toevoegen") != null) {
			Brandstof b = new Brandstof("Benzine", 12345);
			Onderdeel o = new Onderdeel(-1, "RemKabel");
			if (toevoegen) {
				if (type.equals("Brandstof")) {
					b = new Brandstof(naam, id);
					errMessage = db.addBrandstof(b);
				} else if (type.equals("Artikel")) { 
					o = new Onderdeel(id, naam);
					errMessage = db.addOnderdeel(o);
				}
			}
			System.out.println("Item toevoegen");
		} else if (req.getParameter("Item Verwijderen") != null) {
			errMessage = db.remove(deleteItem);
			System.out.println("Item verwijderen");
		} else if (req.getParameter("Voorraad bijwerken") != null){
			errMessage = db.removeBestelling(voorraadItem);
			System.out.println("Bestelling verwijderen");
		}
		req.setAttribute("msgs", errMessage);
		rd = req.getRequestDispatcher("VoorraadBeheer.jsp");
		rd.forward(req, resp);
	}
}