package start;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReserveringBeheer extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		boolean filledIn = true;
		boolean resMogelijk = false;
		int aantalDagen = 0;
		Database db = new Database();
		String errMessage = "";
		resp.setContentType("text/html");
		System.out.println("kom ik op de pagina???");

		String kenteken = req.getParameter("kenteken");
		String dag = (String) req.getParameter("dag");
		String maand = (String) req.getParameter("maand");
		String duur = (String) req.getParameter("duur");
		if (kenteken == "") {
			errMessage += "Vul een kenteken in";
			filledIn = false;
		}
		if (dag.equals("Selecteer een dag")) {
			errMessage += dag;
			filledIn = false;
		} else {
			int dagInt = Integer.parseInt(dag);
		}
		if (maand.equals("Selecteer een maand")) {
			errMessage += maand;
			filledIn = false;
		} else {
			int maandInt = Integer.parseInt(maand);
		}
		if (duur.equals("Selecteer verblijfsduur")) {
			errMessage += duur;
			filledIn = false;
		}
		if (req.getParameter("toevoegen") != null) {
			if (filledIn) {
				if (duur.equals("Dag")) {
					aantalDagen = 1;
				} else if (duur.equals("Week")) {
					aantalDagen = 7;
				} else if (duur.equals("Maand")) {
					aantalDagen = 31;
				}
				int dagInt = Integer.parseInt(dag);
				int maandInt = Integer.parseInt(maand);
				for (int i = 0; i < aantalDagen; i++) {
					System.out.println(db.checkPlaats(dagInt + i + "-"
							+ maandInt)
							+ "<---Aantal dagen vrij ");
					if (dagInt + i == 32) {
						dagInt = 1 - i;
						maandInt++;
					}
					if (db.checkPlaats(dagInt + i + "-" + maandInt) == 0) {
						errMessage += "geen plek op" + dagInt + i + "-"
								+ maandInt;
					} else {
						resMogelijk = true;
					}
				}
				if (resMogelijk) {
					dagInt = Integer.parseInt(dag);
					maandInt = Integer.parseInt(maand);
					for (int i = 0; i < aantalDagen; i++) {
						if (dagInt + i == 32) {
							dagInt = 1 - i;
							maandInt++;
						}
						Reservering r = new Reservering(kenteken, dagInt + i
								+ "-" + maandInt, duur);
						System.out.println(dagInt + i + "-" + maandInt
								+ "<--Reservering voor:");
						errMessage = db.addReservering(r);
					}
				}
			}
			rd = req.getRequestDispatcher("ReserveringBeheer.jsp");
		}else if(req.getParameter("annuleren")!= null){
			rd = req.getRequestDispatcher("Parkeergarage.jsp");
		}
		req.setAttribute("msgs", errMessage);
		
		rd.forward(req, resp);
	}
}