<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/Practicum-2/styles/Login.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<title>User Login</title>
</head>
<body>
	<form action="Login.do" method="post">
		<div id="messagebox">
		<% 
			String userName="";
			userName = (String)config.getInitParameter("dUser");
			String pass = "";
			pass = (String)config.getInitParameter("dPass");
		%>
			<%
				Object msgs = request.getAttribute("msgs");
				if (msgs != null) {
					out.println(msgs);
				}
			%>
			<%
				for (Cookie c : request.getCookies()) {
					if (c.getName().equals("cUsername")) {
						userName = c.getValue();
						break;
					}
				}
			%>
			<%
				for (Cookie c : request.getCookies()) {
					if (c.getName().equals("cPassword")) {
						pass = c.getValue();
						break;
					}
				}
			%>
		</div>
		<div id="left">
			<p>Gebruikersnaam: </p>
			<p>Wachtwoord:	   </p>
			<input type="submit" name="inloggen" value="Log in" /> 
		</div>
		<div id="right">
			<input type="text" name="gebruikersNaam" value="<%= userName %>" /></br>
			<input type="password" name="wachtwoord" value="<%= pass %>" /></br>
			<input type="submit" name="registreren" value="Registreren" />
			
	</br></br></br></br></br></br></br></br></br></br></br></br></br>
		</div>
	</form>
</body>
</html>