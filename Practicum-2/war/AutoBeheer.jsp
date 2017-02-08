<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/Practicum-2/styles/Autobeheer.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<%@ page import = "start.*" %>
<%@ page import = "java.util.ArrayList" %> 
<title>Mijn auto's beheren</title>
</head>
<body>
	<form action="AutoBeheer.do" method="post">
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
			ArrayList<Auto> autos = db.getList("autos");
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
		<div id = "leftleft">
			<label>Klant:</label></br></br>
			<label>Auto:</label>
		</div>
		<div id="left">
		    <label>Auto verwijderen</label></br></br> 	
			<select name = "klant" action="AutoBeheer.do" method="post">
			<%if(idFound){ 
				for(Klant k : klanten){
					if(k.getID() == klantID){
						System.out.println("check: "+klantID);
						%><option><%=k.getID()+": "+k.getNaam() %></option><%
					}
				}
			} else{
			   %><option>0: Onbekend</option><%
				for(Klant k : klanten){
					%><option><%=k.getID()+": "+k.getNaam() %></option><%
				}
			}%>
			</select></br></br>
			<%if(klantID != -1){ %>
			
			<select name="auto">
			<%for(Auto a : autos){
				if(a.getKlantNummer() == klantID){
					%><option><%=a.getID()+"| "+a.getMerk()+"-"+a.getKenteken() %></option><%
				}
			}
			%>	
			</select></br></br>
			<input type="submit" name="verwijderen" value="Auto verwijderen"></br></br>
			<input type="submit" name="annuleren" value="annuleren">
		<%} %>
		</div>
		<div id ="right">
		<label>Auto toevoegen</label></br></br>
		<input type="text" name="merk" value=""></br></br>
		<input type="text" name="kenteken" value=""></br></br>
		<input type="submit" name="autotoevoegen" value="Voeg deze auto toe">
		</div>
		<div id = "center">
			<label>Merk:</label></br></br>
			<label>Kenteken:</label>
		</div>
		</br></br></br></br></br></br></br></br>
		
	</form>
</body>
</html>