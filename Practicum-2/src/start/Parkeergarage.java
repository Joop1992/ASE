package start;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Parkeergarage extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean toegevoegd = false; 
		RequestDispatcher rd = null;
		String errMessage = "Error";	
		resp.setContentType("text/html");

		if (req.getParameter("addR") != null){
			rd = req.getRequestDispatcher("ReserveringBeheer.jsp");
		}else if(req.getParameter("sendO") != null){
			rd = req.getRequestDispatcher("OverzichtVerzenden.jsp");
		}else if(req.getParameter("start") != null){
			rd = req.getRequestDispatcher("Navigatie.jsp");
		}
		rd.forward(req, resp);
	}
}