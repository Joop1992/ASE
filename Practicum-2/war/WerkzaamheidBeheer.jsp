<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1 - strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Werkzaamheidbeheer.css" type="text/css"/>
<meta http-equiv="content-type"	content="text/html; charset = iso-8859-1" />
<%@ page import = "start.*" %>
<title>Werkzaamheid beheer</title>
</head>
<body>
	<%
		Object msgs = request.getAttribute("msgs");
		if (msgs != null) {
			out.println(msgs);
		}
		Database db = new Database(); 
	%>
	<form action="WerkzaamheidBeheer.do" method="post">
		<div id="left">
			<label>Omschrijving:</label></br></br>
			<label>ID:</label></br></br>
			<input type="submit" name="annuleren" value="Annuleren">
		</div>
		<div id="right">
			<input type="text" name="omschrijving" value=""> </br></br>
			<input type="text" name="ID" value=<%=db.getNewWerkzaamheidID() %> readonly> </br></br>
			<input type="submit" name="toevoegen" value="Toevoegen">
		</div>
		</form>
		</br></br></br></br></br></br></br></br></br></br></br>
	</body>
</html>