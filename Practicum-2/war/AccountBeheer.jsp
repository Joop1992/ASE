<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Accountbeheer.css" type="text/css"/>
<title>Account beheer</title>
<%@ page import = "start.*" %>
<%@ page import = "java.util.ArrayList" %> 
</head>
<body>
	<form action="AccountBeheer.do" method="post">
	<%
		Database db = new Database();
	%>
		<div id="left">
			<!-- ALS WE TIJD OVER HEBBEN!!<input type="submit" name="wijzigen" value="Accountgegevens wijzigen" />  -->
			<label>Gebruikersnaam:</label></br></br>
			<label>Wachtwoord:</label></br></br>
			<label>ID:</label></br></br>
			<label>Toegang tot werkplaats:</label></br></br>
			<label>Toegang tot voorraadbeheer:</label></br></br>
			<label>Toegang tot parkeergarage:</label></br></br>
			<label>Toegang tot Klantbeheer:</label></br></br>
			<label>Admin rechten:</label> </br></br>
			<input type="submit" name="annuleren" value="Annuleren" /> 
		</div>
		<div id="right">
			<input type="text" name="naam" value="" /></br></br>
			<input type="text" name="wachtwoord" value="" /></br></br>
			<input type="text" name="ID" value=<%=db.getNewWerknemerID()%> readonly></br></br>
			<input type="checkbox" name="werkplaats" value="true" /></br></br>
			<input type="checkbox" name="voorraadbeheer" value="true" /></br></br>
			<input type="checkbox" name="parkeergarage" value="true" /></br></br>
			<input type="checkbox" name="baliemedewerker" value="true" /></br></br>
			<input type="checkbox" name="admin" value="true" /></br></br>
		</div>
		<div id="down">
			<input type="submit" name="aanmaken" value="Nieuwe account aanmaken" />
		</div>
	</form>
</body>
</html>