<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1 - strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Navigatie.css" type="text/css"/>
<meta http-equiv="content-type"
	content="text/html; charset = iso-8859-1" />

<title>Navigatie</title>
</head>
<body>
	<%
		boolean werkplaats =false;
		boolean voorraadBeheer = false;
		boolean parkeerGarage = false;
		boolean balieMedewerker = false;
		boolean admin = false;
	    boolean geenRechten = true;
		String rechten = (String)session.getAttribute("rechten");
	    try{
	    	String recht[] = rechten.split(",");
	    	if(!recht[0].equals("-")){ 
	    		werkplaats = true;
	    		geenRechten = false;
	    	}
	    	if(!recht[1].equals("-")){ 
	    		voorraadBeheer = true;
	    		geenRechten = false;
	    	}
	    	if(!recht[2].equals("-")){ 
	    		parkeerGarage = true;
	    		geenRechten = false;
	    	}
	    	if(!recht[3].equals("-")){ 
	    		balieMedewerker = true;
	    		geenRechten = false;
	    	}
	    	if(!recht[4].equals("-")){ 
	    		admin = true;
	    		geenRechten = false;
	    	}
	    }catch(Exception e){System.out.println(e); geenRechten = true;}
	    
	%>
	<h2>Welkom op de werknemers site</h2>
	
	<form action="Navigatie.do" method="post">
	<input type="submit" name="uitloggen" value="Log uit">
		<%if(werkplaats){ %>
		<input type="submit" name="werkplaats" value="werkplaats">
		<%} %>
		<%if(parkeerGarage){ %>
		<input type="submit" name="parkeergarage" value="parkeergarage">
		<%} %>
		<%if(balieMedewerker){ %></br></br>
		<input type="submit" name="klantenbinding" value="klantenbinding">
		<%} %>
		<%if(voorraadBeheer){ %>
		<input type="submit" name="voorraadbeheer" value="voorraadbeheer">
		<%} %>
		<%if(admin){ %>
		<input type="submit" name="accountbeheer" value="Accountbeheer">
		<%} %>
		<%if(geenRechten){ %>
		<p>U heeft niet de juiste rechten om deze pagina in te zien</p>
		<%} %>
		</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
	</form>
</body>
</html>
