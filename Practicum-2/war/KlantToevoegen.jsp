<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import ="start.*"%>
<%@ page import ="java.util.ArrayList"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Klanttoevoegen.css" type="text/css"/>
<title>       Klant toevoegen</title>
</head>
<body>
	<form action="KlantToevoegen.do" method="post">
		<div id="messagebox">
			<%
				Object msgs = request.getAttribute("msgs");
				if (msgs != null) {
					out.println(msgs);
				}
			%>
		</div>
		<div id ="left">
			<h3>Klant toevoegen:</h3>
			<label>Gebruikersnaam:</label></br></br>
			<label>Emailadres:</label></br></br> 
			<input type="submit" name="annuleren" value="annuleren" /> 
		</div>
		<div id="right">
			<input type="text" name="gebruikersNaam" value="" /></br></br>
			<input type="text" name="emailAdres" value=""></br></br>
			<input type="submit" name="klantToevoegen" value="Voeg klant toe" />
		</div>
		</br></br></br></br></br></br></br></br></br></br></br>
		
	</form>
</body>
</html>