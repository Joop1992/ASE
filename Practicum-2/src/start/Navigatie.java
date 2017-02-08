package start;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Navigatie extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = null;
		resp.setContentType("text/html");
		
		if (req.getParameter("werkplaats") != null){
			rd = req.getRequestDispatcher("Werkplaats.jsp");
		}else if(req.getParameter("parkeergarage") != null){
			rd = req.getRequestDispatcher("Parkeergarage.jsp");
		}else if(req.getParameter("klantenbinding") != null){
			rd = req.getRequestDispatcher("KlantBeheer.jsp");
		}else if(req.getParameter("voorraadbeheer") != null){
			rd = req.getRequestDispatcher("VoorraadBeheer.jsp");
		}else if(req.getParameter("accountbeheer") != null){
			rd = req.getRequestDispatcher("AccountBeheer.jsp");
		}else if(req.getParameter("uitloggen") != null){
			req.getSession().removeAttribute("rechten");
			rd = req.getRequestDispatcher("Login.jsp");
		}
		rd.forward(req, resp);
	}
}