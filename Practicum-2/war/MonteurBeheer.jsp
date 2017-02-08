<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1 - strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type"	content="text/html; charset = iso-8859-1" />
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Monteurbeheer.css" type="text/css"/>
<%@ page import = "start.*" %>
<title>Monteurbeheer</title>
</head>
<body>
	<form action="MonteurBeheer.do" method="post">
	<%
		Object msgs = request.getAttribute("msgs");
		if (msgs != null) {
			out.println(msgs);
		}
		Database db = new Database(); 
	%>
	<div id = "left">
		<label>Naam:</label></br></br>
		<label>Achternaam:</label></br></br>
		<label>ID:</label></br></br>
		<input type="submit" name="annuleren" value="Annuleren">
	</div>
	<div id="right">
		<input type="text" name="naamMonteur" value=""> </br></br>
		<input type="text" name="achternaamMonteur" value=""> </br><br/>
		<input type="text" name="ID" value=<%=db.getNewMonteurID()%> readonly> <br/></br>
		<input type="submit" name="toevoegen" value="Toevoegen">
	</div>
			</br></br></br></br></br></br></br></br></br>
		</form>
	</body>
</html>