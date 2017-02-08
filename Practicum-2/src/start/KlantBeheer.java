package start;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class KlantBeheer extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		resp.setContentType("text/html");
		String errMessage = "";

		if (req.getParameter("klantToevoegen") != null){
			rd = req.getRequestDispatcher("KlantToevoegen.jsp");
		}else if (req.getParameter("autoToevoegen") != null){
			rd = req.getRequestDispatcher("AutoBeheer.jsp");
		}else if (req.getParameter("klusToevoegen") != null){
			rd = req.getRequestDispatcher("KlusBeheer.jsp");
		}else if (req.getParameter("monteurToevoegen") != null){
			rd = req.getRequestDispatcher("MonteurBeheer.jsp");
		}else if (req.getParameter("werkzaamheidToevoegen") != null){
			rd = req.getRequestDispatcher("WerkzaamheidBeheer.jsp");
		}else if (req.getParameter("emailVerzenden") != null){
			//rd = req.getRequestDispatcher("VerstuurEmail.jsp");
		}else if (req.getParameter("start") != null){
			rd = req.getRequestDispatcher("Navigatie.jsp");
		}
		rd.forward(req, resp);
	}
}