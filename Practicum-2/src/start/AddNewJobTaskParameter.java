package start;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewJobTaskParameter {
	public HttpServletRequest req;
	public HttpServletResponse resp;
	public RequestDispatcher rd;
	public String monteurIDs;
	public String werkzaamhedenIDs;
	public String werkzaamheidInformatie;

	public AddNewJobTaskParameter(HttpServletRequest req,
			HttpServletResponse resp, RequestDispatcher rd, String monteurIDs,
			String werkzaamhedenIDs, String werkzaamheidInformatie) {
		this.req = req;
		this.resp = resp;
		this.rd = rd;
		this.monteurIDs = monteurIDs;
		this.werkzaamhedenIDs = werkzaamhedenIDs;
		this.werkzaamheidInformatie = werkzaamheidInformatie;
	}
}