package start;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Changed the names of variables and this class
//Extracted service class for the KlusBeheerServlet
public class KlusBeheerService {
	//Added constants
	private static final String YEAR_NOT_FILLED_IN_ERROR = "Selecteer een jaar ;";
	private static final String EMPTY_YEAR_FIELD = "Selecteer een jaar";
	private static final String MONTH_NOT_FILLED_IN_ERROR = "Selecteer een maand ;";
	private static final String EMPTY_MONTH_FIELD = "Selecteer een maand";
	private static final String DAY_NOT_FILLED_IN_ERROR = "Selecteer een dag ;";
	private static final String EMPTY_DAY_FIELD = "Selecteer een dag";
	private static final String ID_NOT_UNIQUE_ERROR = "ID van de klus is niet uniek;";
	private static final String ID_NOT_FILLED_IN_ERROR = "Vul een identificatie nummer in; ";
	private static final String JOB_NOT_FILLED_IN_ERROR = "Voeg eerst een werkzaamheid toe; ";
	private static final String MECHANIC_NOT_FILLED_IN_ERROR = "Voeg eerst een monteur toe; ";
	private static final String CAR_NOT_FILLED_IN_ERROR = "Selecteer een auto; ";
	private static final String CUSTOMER_NOT_FILLED_IN_ERROR = "Selecteer een klant; ";
	
	//Extracted method
	public void addNewJobTask(HttpServletRequest req,
			HttpServletResponse resp, RequestDispatcher rd, String monteurIDs,
			String werkzaamhedenIDs, String werkzaamheidInformatie)
			throws ServletException, IOException {
		if (!werkzaamheidInformatie.equals("Selecteer een werkzaamheid")) {
			String werkzaamheid = werkzaamheidInformatie.split(":")[0];
			String scanWerkzaamheden = werkzaamhedenIDs;
			werkzaamhedenIDs = werkzaamhedenIDs.isEmpty() ? werkzaamheid : werkzaamhedenIDs + "," + werkzaamheid; 
			boolean jobExists = checkIfJobExists(req, resp, monteurIDs, werkzaamheid,
					scanWerkzaamheden);
			if(jobExists){
				setJobAttributes(req, monteurIDs, scanWerkzaamheden, "Deze werkzaamheid is al toegevoegd");
				rd.forward(req, resp);
			}
			setJobAttributes(req, monteurIDs, werkzaamhedenIDs, "Job " + werkzaamheid + " added");
		} else {
			setJobAttributes(req, monteurIDs, werkzaamhedenIDs, "Select a job");
		}
	}

	//Extracted method
	public void addNewMechanic(HttpServletRequest req,
			HttpServletResponse resp, RequestDispatcher rd, String monteur,
			String monteurIDs, String werkzaamhedenIDs)
			throws ServletException, IOException {
		if (!monteur.equals("Selecteer een monteur")) {
			String monteurID = determineID(monteur);
			String monteurIdsBeforeAddingNewID = monteurIDs;
			if (monteurIDs.isEmpty()) {
				monteurIDs = monteurID;
			} else {
				monteurIDs += "," + monteurID;
			}
			boolean mechanicExists = checkIfMechanicExists(monteurID, monteurIdsBeforeAddingNewID);
			if(mechanicExists){
				setJobAttributes(req, monteurIdsBeforeAddingNewID, werkzaamhedenIDs, "Deze monteur is al toegevoegd");
				rd.forward(req, resp);
			}

			setJobAttributes(req, monteurIDs, werkzaamhedenIDs, "Monteur " + monteur + " toegevoegd");
		} else {
			setJobAttributes(req, monteurIDs, werkzaamhedenIDs, "Selecteer een monteur");
		}
	}

	//Extracted method
	public HttpServletRequest addNewJob(HttpServletRequest req, Database db, int IDI,
			String klant, String auto, String monteurIDs,
			String werkzaamhedenIDs, String dag, String maand, String jaar) {
		String errorMessage;
		int klantID = Integer.parseInt(determineID(klant));
		int autoID = Integer.parseInt(determineID(auto));
		Klus k = new Klus(dag + "-" + maand + "-" + jaar, "Ingepland",
				klantID, autoID, monteurIDs, werkzaamhedenIDs, IDI);
		errorMessage = db.addKlus(k);
		req.setAttribute("msgs", errorMessage);
		return req;
	}

	//Extracted method
	public boolean checkIfMechanicExists(String monteurID,
			String monteurIdsBeforeAddingNewID) {
		Scanner sc = new Scanner(monteurIdsBeforeAddingNewID);
		sc.useDelimiter(",");
		while (sc.hasNext()) {
			if (sc.next().equals(monteurID)) {
				sc.close();
				return true;
			}
		}
		sc.close();
		return false;
	}

	//Extracted method
	public String proccessJobFormFields(Database db, String errorMessage,
			String klant, String auto, String monteurIDs,
			String werkzaamhedenIDs, String dag, String maand, String jaar,
			String ID) {
		if (klant.isEmpty()) {
			errorMessage += CUSTOMER_NOT_FILLED_IN_ERROR;
		}
		if (auto.isEmpty()) {
			errorMessage += CAR_NOT_FILLED_IN_ERROR;
		}
		if (monteurIDs.isEmpty()) {
			errorMessage += MECHANIC_NOT_FILLED_IN_ERROR;
		}
		if (werkzaamhedenIDs.isEmpty()) {
			errorMessage += JOB_NOT_FILLED_IN_ERROR;
		}
		if (dag.equals(EMPTY_DAY_FIELD)) {
			errorMessage += DAY_NOT_FILLED_IN_ERROR;
		}
		if (maand.equals(EMPTY_MONTH_FIELD)) {
			errorMessage += MONTH_NOT_FILLED_IN_ERROR;
		}
		if (jaar.equals(EMPTY_YEAR_FIELD)) {
			errorMessage += YEAR_NOT_FILLED_IN_ERROR;
		}
		if (ID.isEmpty()) {
			errorMessage += ID_NOT_FILLED_IN_ERROR;
		} else {
			if (!db.checkKlusID(Integer.parseInt(ID))) {
				errorMessage += ID_NOT_UNIQUE_ERROR;
			}
		}
		return errorMessage;
	}

	//Extracted method
	public boolean checkIfJobExists(HttpServletRequest req,
			HttpServletResponse resp, String monteurIDs, String werkzaamheid,
			String scanWerkzaamheden) throws ServletException, IOException {
		RequestDispatcher rd;
		Scanner sc = new Scanner(scanWerkzaamheden);
		sc.useDelimiter(",");
		while (sc.hasNext()) {
			if (sc.next().equals(werkzaamheid)) {
				sc.close();
				return true;
			}
		}
		sc.close();
		return false;
	}

	//Extracted method
	public String determineID(String infoPrecededByID) {
		return infoPrecededByID.split(":")[0];
	}

	//Extracted method
	public void setJobAttributes(HttpServletRequest req, String monteurIDs,
			String werkzaamhedenIDs, String message) {
		req.setAttribute("klusMonteurs", monteurIDs);
		req.setAttribute("klusWerkzaamheden", werkzaamhedenIDs);
		req.setAttribute("msgs", message);
	}
}
