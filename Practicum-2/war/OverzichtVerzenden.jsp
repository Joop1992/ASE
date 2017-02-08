<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1 - strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Overzichtverzenden.css" type="text/css"/>
<meta http-equiv="content-type"	content="text/html; charset = iso-8859-1" />

<title>Overzicht verzenden</title>
</head>
<body>
		<%
			Object msgs = request.getAttribute("msgs");
			if (msgs != null) {
				out.println(msgs);
			}
		%>
	<form action="OverzichtVerzenden.do" method="post">
		<div id="left">
		<label>Selecteer maand:</label></br></br>
		<input type="submit" name="annuleren" value="annuleren">
		</div>
		<div id ="right">
		<select name="maand" value="">
		<%for(int i = 1; i <= 12; i++){
			%><option><%=i%></option><%
		}%>
		</select></br></br>
		<input type="submit" name="genereer" value="Genereer">
		</div>
		</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
		
	</body>
</html>