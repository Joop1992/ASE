<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/Practicum-2/styles/Startpagina.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<title>Home</title>
</head>
<body>
	<form action="StartPagina.do" method="post">
			<div id="messagebox">
			<%
			String gebruikersNaam =(String)session.getAttribute("gebruiker");  
			String wachtwoord =(String)session.getAttribute("wachtwoord");
			%>
			<%
			    response.addCookie(new Cookie("cPassword", (String)wachtwoord));
				response.addCookie(new Cookie("cUsername", (String)gebruikersNaam));
			%>
		</div>
		<div id="messagebox">
		</div>
		<div>
			<h2 id="welkom">Welkom op de site!</h2>
			<div id ="bottom">
			<input type="submit" name="uitloggen" value="Log uit" /> 
			<input type="submit" name="gegevenswijzigen" value="Uw gegevens wijzigen" /> 
			<input type="submit" name="klusinplannen" value="Onderhoud/reparatie inplannen" /> 
			<input type="submit" name="autotoevoegen" value="Mijn auto('s) beheren" /> 
			</div>
			</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
		</div>
	</form>
</body>
</html>