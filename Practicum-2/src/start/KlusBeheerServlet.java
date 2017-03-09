package start;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KlusBeheerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		KlusBeheerService klusBeheerService = new KlusBeheerService();
		
		boolean jobFormFilledIn = true;
		RequestDispatcher rd = null;
		Database db = new Database();
		resp.setContentType("text/html");
		String errorMessage = "";
		int IDI = 1;

		String klant = (String) req.getParameter("klant");
		String auto = (String) req.getParameter("auto");
		String monteur = (String) req.getParameter("monteur");
		String monteurIDs = req.getParameter("monteurLijst");
		String werkzaamhedenIDs = req.getParameter("werkzaamhedenLijst");
		String werkzaamheidGegevens = (String) req.getParameter("werkzaamheid");
		String dag = (String) req.getParameter("dag");
		String maand = (String) req.getParameter("maand");
		String jaar = (String) req.getParameter("jaar");
		String ID = (String) req.getParameter("ID");

		errorMessage =  klusBeheerService.proccessJobFormFields(db, errorMessage, klant, auto,
				monteurIDs, werkzaamhedenIDs, dag, maand, jaar, ID);

		if (!errorMessage.isEmpty()) {
			jobFormFilledIn = false;
		}

		if (req.getParameter("toevoegen") != null && jobFormFilledIn) {
			req = klusBeheerService.addNewJob(req, db, IDI, klant, auto, monteurIDs, werkzaamhedenIDs,
					dag, maand, jaar);
			rd = req.getRequestDispatcher("KlusBeheer.jsp");
		} else if (req.getParameter("annuleren") != null) {
			if (req.getSession().getAttribute("rechten") != null) {
				rd = req.getRequestDispatcher("KlantBeheer.jsp");
			} else {
				rd = req.getRequestDispatcher("StartPagina.jsp");
			}
		} else if (req.getParameter("MonteurToevoegen") != null && monteur != "Selecteer een monteur") {
			rd = req.getRequestDispatcher("KlusBeheer.jsp");
			klusBeheerService.addNewMechanic(req, resp, rd, monteur, monteurIDs, werkzaamhedenIDs);

		} else if (req.getParameter("WerkzaamheidToevoegen") != null && werkzaamheidGegevens != "Selecteer een werkzaamheid") {
			rd = req.getRequestDispatcher("KlusBeheer.jsp");
			klusBeheerService.addNewJobTask(new AddNewJobTaskParameter(req, resp, rd, monteurIDs,
					werkzaamhedenIDs, werkzaamheidGegevens));
		} else {
			req.setAttribute("msgs", errorMessage);
		}
		req.getSession().removeAttribute("klantID");
		if(rd != null) {
			rd.forward(req, resp);
		}
	}


	
}