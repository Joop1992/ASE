<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1 - strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Klusbeheer.css" type="text/css"/>
<meta http-equiv="content-type"	content="text/html; charset = iso-8859-1" />
<%@ page import = "start.*" %>
<%@ page import = "java.util.ArrayList" %> 
<title>Klusbeheer</title>
</head>
<body>
	<%
		String monteurLijst = (String)request.getAttribute("klusMonteurs");
		String werkzaamhedenLijst = (String)request.getAttribute("klusWerkzaamheden");
	    if(monteurLijst == null){monteurLijst = "";}
	    if(werkzaamhedenLijst == null){werkzaamhedenLijst = "";}
		Object msgs = request.getAttribute("msgs");
		if (msgs != null) {
			out.println(msgs);
		}
		Database db = new Database(); 
		ArrayList<Monteur> monteurs = db.getList("monteurs");
		ArrayList<Werkzaamheid> werkzaamheden = db.getList("werkzaamheden");
		ArrayList<Klant> klanten = db.getList("klanten");
		ArrayList<Auto> autos = db.getList("autos");
		int klantID = -1;
		boolean idFound = false;
		try{
			klantID =(Integer)session.getAttribute("klantID");
			idFound = true;
		}catch(Exception e){
			idFound = false;
		}	
	%>
	<form action="KlusBeheer.do" method="post">
		<input type="hidden" name="monteurLijst" value=<%=monteurLijst %>>
		<input type="hidden" name="werkzaamhedenLijst" value=<%=werkzaamhedenLijst %>>
		    <div id="left">
		    <label>Klant:</label><br/><br/>
		    <label>Auto:</label><br/><br/>
		    <label>Monteur:</label>	<br/><br/>
		    <label>Werkzaamheid:</label><br/><br/>
		    <label>Datum:</label><br/><br/>
		    <label>ID:</label><br/><br/>
		    <input type="submit" name="annuleren" value="Annuleren">
		    </div>
			
			<div id="center">
			<select name = "klant">
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
			</select>	<br/><br/>
			
						<select name = "auto">
			<%if(idFound){
				for(Auto a : autos){
					if(a.getKlantNummer() == klantID){
						%><option><%=a.getID()+": "+a.getMerk()+" - "+a.getKenteken() %></option><%
					}
				}
			}else{
				for(Auto a : autos){
					%><option><%=a.getID()+": "+a.getMerk()+" - "+a.getKenteken() %></option><%
				}
			}%>
			</select>		<br/><br/>
			
			<select name = "monteur">
			<option>Selecteer een monteur</option>
				<%		for(Monteur m : monteurs){ %>
					<option><%=m.getID()+": "+m.getNaam()+" "+m.getAchterNaam() %></option><%
				}%>
			</select><br/><br/>
			
						<select name = "werkzaamheid">
			<option>Selecteer een werkzaamheid</option>
				<%		for(Werkzaamheid w : werkzaamheden){ %>
					<option><%=w.getID()+": "+w.getOmschrijving()%></option><%
				}%>
			</select><br/><br/>
						<select name = "dag">
			<option>Selecteer een dag</option>
			<%for(int i = 1; i <= 31; i++){
				%><option><%=i%></option><%
			}%></select><br/><br/>
			<input type="text" name="ID" value=<%=db.getNewKlusID()%> readonly> <br/><br/>
			<input type="submit" name="toevoegen" value="Toevoegen">
			</div>	    	

				    	

			<div id = "right">
				<input type="submit" name="MonteurToevoegen" value="Aan klus toevoegen"></br></br>
				<input type="submit" name="WerkzaamheidToevoegen" value="Aan klus toevoegen"></br></br>
				<select name = "maand">
					<option>Selecteer een maand</option>
					<%for(int i = 1; i <= 12; i++){
						%><option><%=i%></option><%
					}%>
				</select>
			</div>
					
			<div id ="rightright">
				<select name = "jaar">
				<option>Selecteer een jaar</option>
				<%for(int i = 2015; i <= 2020; i++){
					%><option><%=i%></option><%
				}%>
			    </select><br/><br/>
			</div>
		</form>
	</body>
</html>