<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1 - strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Reserveringbeheer.css" type="text/css"/>
<meta http-equiv="content-type"	content="text/html; charset = iso-8859-1" />

<title>Reservering toevoegen</title>
</head>
<body>
		<%
			Object msgs = request.getAttribute("msgs");
			if (msgs != null) {
				out.println(msgs);
			}
		%>
				
	<form action="ReserveringBeheer.do" method="post">
		<div id="left">
			<label>Kenteken:</label></br></br>
			<label>Dag:</label></br></br>
			<label>Maand:</label></br></br>
			<label>Duur:</label></br></br>
			<input type="submit" name="annuleren" value="annuleren">
		</div>
		<div id="right">
			<input type=text name="kenteken" value=""></br></br>
				    <select name="dag" value="dag">
			 <option>Selecteer een dag</option>
		<%for(int i = 1; i <= 31; i++){
			%><option><%=i%></option><%
		}%>
		</select></br></br>
				<select name="maand" value="maand">
			  <option>Selecteer een maand</option>
		<%for(int i = 1; i <= 12; i++){
			%><option><%=i%></option><%
		}%>
		</select></br></br>
		<select name="duur" value="duur">
			<option>Selecteer verblijfsduur</option>
			<option>Dag</option>
			<option>Week</option>
			<option>Maand</option>
		</select></br></br>
		<input type="submit" name="toevoegen" value="toevoegen">
		</div>
		</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
		</form>
	</body>
</html>
