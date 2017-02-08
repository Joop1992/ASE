<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Gegevensbeheer.css" type="text/css"/>
<%@ page import = "start.*" %>
<%@ page import = "java.util.ArrayList" %> 
<title>Account gegevens wijzigen</title>
</head>
<body>
	<form action="GegevensBeheer.do" method="post">
	<div>
		<%
		Object msgs = request.getAttribute("msgs");
		if (msgs != null) {
			out.println(msgs);
		}
		%>
	</div>
		<%
			Database db = new Database(); 
			ArrayList<Klant> klanten = db.getList("klanten");
			String klantNaam = "";
			String klantWachtwoord;
 			int klantID = -1;
			boolean idFound = false;
			try{
				klantID =(Integer)session.getAttribute("klantID");
				idFound = true;
				System.out.println("ID FOUND: "+klantID);
			}catch(Exception e){
				idFound = false;
			}
		%>
			<%if(idFound){ 
				for(Klant k : klanten){
					if(k.getID() == klantID){
						klantNaam = k.getNaam();
					}
				}
			}%>
		<div id="left">
		<label>Gebruikersnaam: </label>	</br></br>			
		<label>Huidig wachtwoord: </label></br>		</br>	
		<label>Nieuw wachtwoord: </label></br>			</br>
		<label>Herhaal nieuw wachtwoord: </label></br></br>
		<input type="submit" name="annuleren" value="annuleren">	
		</div>
		
		<div id="right">
		<input type="text" name="naam" value=<%=klantNaam %>></br></br>
		<input type="text" name="oudWachtwoord" value=""></br></br>
		<input type="text" name="nieuwWachtwoord1" value=""></br></br>
		<input type="text" name="nieuwWachtwoord2" value=""></br></br>
		<input type="submit" name="wijzigen" value="Wijzig mijn gegevens">
		</div>
		
		</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
	</form>
</body>
</html>