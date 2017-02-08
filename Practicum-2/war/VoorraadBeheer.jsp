<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1 - strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Voorraadbeheer.css" type="text/css"/>
<meta http-equiv="content-type"
	content="text/html; charset = iso-8859-1" />
<%@ page import = "start.*" %>
<%@ page import = "java.util.ArrayList" %> 
<title>Voorraadbeheer</title>
</head>
<body>
	<form action="VoorraadBeheer.do" method="post">
		<div id="messagebox">
			<%
				Object msgs = request.getAttribute("msgs");
				if (msgs != null) {
					out.println(msgs);
				}
				Database db = new Database();
				ArrayList<Brandstof> brandstoffen = db.getList("brandstoffen");
				ArrayList<Onderdeel> onderdelen = db.getList("onderdelen");
				ArrayList<Bestelling> bestellingen = db.getList("bestellingen");
			%>
		</div>
				<select name="Voorraad item" value = "Voorraad item">
				<%		for(Bestelling b : bestellingen){ %>
						<option><%=b.getBesteldItem() %></option><%
						}%>
				</select>
					<input type="submit" name = "Voorraad bijwerken" value = "Voorraad bijwerken"/>
				
				<select name="deleteItem" value="deleteItem">
						<%for(Brandstof b : brandstoffen){ %>
						<option><%=b.getType() %></option><%
						}%>
						<%for(Onderdeel o : onderdelen){ %>
						<option><%=o.getArtikelNummer()+": "+o.getOnderdeelNaam() %></option><%
						}%>
				</select>
					<input type="submit" name="Item Verwijderen" value="Item Verwijderen"/></br></br>
				<select name="selectType" value="">
						<option>Artikel</option>
						<option>Brandstof</option>
				</select>
				<span title="Vul hier de naam van het artikel/de brandstof in">
					<input type=text name=naam value="">
				</span>
				<span title="Vul hier het artikelnummer/TSIC in">
				<input type=text name=id value=""></span>
					<input type="submit" name="item toevoegen" value="item toevoegen" />
		     
		<div id="textarea">
		<input id ="big" type="text" value="Type" readonly/>
		<input id ="big" type="text" value="Voorraad(Liters en aantal)" readonly/>
		<input id ="big" type="text" value="ID" readonly/></br>
		<%
		  for(Brandstof b : brandstoffen){
			 %><input id ="big" type ="text" value=<%=b.getType() %> readonly/>
			 <input id ="big" type ="text" value=<%=b.getLiters() %> readonly/>
			 <input id ="big" type ="text" value=<%=b.getTSIC() %> readonly/></br>
             <%
		  }
		  for(Onderdeel o : onderdelen){
			 %><input id ="big" type ="text" value=<%=o.getOnderdeelNaam() %> readonly/>
			 <input id ="big" type ="text" value=<%=o.getVoorraad() %> readonly/>
			 <input id ="big" type ="text" value=<%=o.getArtikelNummer() %> readonly/></br>
			 <%
		  }
		%>
		</div>
		</br></br></br></br></br></br></br></br></br></br></br></br>
	</form>
</body>
</html>