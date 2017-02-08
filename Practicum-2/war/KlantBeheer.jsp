<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Klantbeheer.css" type="text/css"/>
<title>Klantbeheer</title>
</head>
<%@ page import = "start.*" %>
<%@ page import = "java.util.ArrayList" %> 
<body>
	<form action="KlantBeheer.do" method="post">
	<div id="left">
		<input type="submit" name="klantToevoegen" value="Klant toevoegen" />
		<input type="submit" name="autoToevoegen" value="Auto toevoegen" />
		<input type="submit" name="klusToevoegen" value="Klus toevoegen" /></br></br>
		<input type="submit" name="monteurToevoegen" value="Monteur toevoegen" />
		<input type="submit" name="werkzaamheidToevoegen" value="Werkzaamheid toevoegen" />
		<input type="submit" name="EmailVerzenden" value="Email verzenden" /></br></br>
	</div></br></br>
	<div id="right">
		<input type="submit" name="start" value="Terug naar start"/>
	</div>
	</form>
	</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
	</br></br></br>
</body>
</html>