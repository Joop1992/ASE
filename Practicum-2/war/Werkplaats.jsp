<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1 - strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="content-type"	content="text/html; charset = iso-8859-1" />

<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<title>Werkplaats</title>
</head>
<%@ page import = "start.*" %>
<%@ page import = "java.util.ArrayList" %>
<body>
	<%
		Object msgs = request.getAttribute("msgs");
		if (msgs != null) {
			out.println(msgs);
		}
		Database db = new Database();
		boolean klusAanwezig = true;
		int klusID = -1;
		try{
	    	klusID = Integer.parseInt((String)session.getAttribute("klusID"));
		}catch(Exception e){System.out.println(e);klusAanwezig = false;}
		ArrayList<Onderdeel> onderdelen = db.getList("onderdelen");
		ArrayList<Klus> klussen = db.getList("klussen");
		Klus selected = new Klus("","", -1, -1, "", "", 0);
		if(klusAanwezig){
			for(Klus k : klussen){
				if(k.getID() == klusID){
					selected = k;
				}
			}
		}
		String status = "", auto= "", monteurs= "", werkzaamheden= "", klusOnderdelen= "", uren= "";
		try{
		status = (String)session.getAttribute("klusStatus");
		auto = (String)session.getAttribute("klusAuto");
		monteurs = (String)session.getAttribute("klusMonteurs");
		werkzaamheden = (String)session.getAttribute("klusWerkzaamheden");
		klusOnderdelen = (String)session.getAttribute("klusOnderdelen");
		uren = (String)session.getAttribute("klusUren");
		}catch(Exception e){System.out.println(e);}
	%>
	<form action="Werkplaats.do" method="post">
		<span>
			<div id="boven">
				<select name = "datum">
				<option>Selecteer datum</option>
                	<%for(Klus k : klussen){ %>
                		<option><%="Klus:"+k.getID()+": op: "+k.getDatum() %></option>
                	<%} %>
				</select> <br/><br/>
				<input type="submit" name="selecteerDatum" value="Toon klus">
			</div>
		</span>
		<span>
			<div id="links">
				<input type="submit" name="previous" value="vorige klus van vandaag"> <br/><br/>				
			</div>
		</span>
		<span>
			<div id="midden"> 
			<%if(klusAanwezig){ %>
				<label>Status:</label><%=status %>				<br/><br/>
				<label>Auto:</label>	<%=auto %>			<br/><br/>
				<label>Monteurs:</label><%=monteurs %>				<br/><br/>
				<label>Werkzaamheden:</label><%=werkzaamheden %>			<br/><br/>
				<label>Onderdelen:</label><%=klusOnderdelen %>				<br/><br/>
				<label>Manuren:</label>	<%=uren %>				<br/><br/>
			<%} %>
			</div>
		</span>
		<span>
			<div id="rechtsmidden">
				<select name = "onderdeel">
					<option>Selecteer onderdeel</option>
					<%
						for(Onderdeel o : onderdelen){
							%><option><%=o.getArtikelNummer()+": "+o.getOnderdeelNaam() %></option>							<%
						}
					%>
				</select><br/><br/>
				<input type="text" name="AantalUren" value=""><br/><br/>
				<select name = "status">
				    <option>Selecteer status</option>
					<option>Mee-bezig</option>
					<option>Afgerond</option>
					<option>Ingepland</option>
				</select>
			</div>
		</span>
		<span>
			<div id="rechts">
				<input type="submit" name="next" value="volgende klus van vandaag"> <br/><br/>
				<input type="submit" name="addA" value="Artikel toevoegen aan klus"><br/><br/>
				<input type="submit" name="addU" value="Uren toevoegen aan klus"><br/><br/>
				<input type="submit" name="changeS" value="Status bijwerken"><br/><br/>
			</div>
		</span>
		</form>
	</body>
</html>