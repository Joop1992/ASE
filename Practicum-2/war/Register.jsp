<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1 - strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="/Practicum-2/Default.css" type="text/css"/>
<link rel="stylesheet" href="/Practicum-2/styles/Register.css" type="text/css"/>
<meta http-equiv= "content-type" content="text/html; charset = iso-8859-1" />

<title>Registratie</title>
</head>

<body>

	<form action="Register.do" method="post">
		<div id="messagebox">
			<%
				Object msgs = request.getAttribute("msgs");
				if (msgs != null) {
					out.println(msgs);
				}
			%>
		</div>
		<div id="left">
			<u><p >Gebruikersnaam:   	</p></u></br>
			<u><p>Volledige Naam:		</p></u></br>
			<u><p>Wachtwoord: 			</p></u></br>
			<u><p>Herhaal Wachtwoord:   </p></u></br>
			<u><p>Email: 				</p></u></br>
			<u><p>Herhaal Email: 		</p></u></br>
			<u><p>Automerk: 				</p></u></br>
			<u><p>kenteken: 				</p></u></br>
			<input type="submit" name="aanmaken" value="Account aanmaken" /> 
		</div>
		<div id="right">
		<span title="Vul gewenste gebruikersnaam in"><p><input type="text" name="gebruikersNaam" /></p></span></br>
		<span title="Vul uw volledige naam in"><p><input type="text" name="naam" /></p></span></br>
		<span title="Vul hier uw gewenste wachtwoord in"><p><input type="password" name="wachtwoord1" /></p></span></br>
		<span title="Vul hier uw gewenste wachtwoord in"><p><input type="password" name="wachtwoord2" /></p></span></br>
		<span title="Vul hier uw email adres in"><p><input type="text" name="email1" /></p></span></br>
		<span title="Vul hier uw email adres in"><p><input type="text" name="email2" /></p></span></br>
		<span title="Vul hier uw auto merk in"><p><input type="text" name="autoMerk" /></p></span></br>
		<span title="Vul hier uw kenteken in"><p><input type="text" name="kenteken" /></p></span></br>
		</div>
	</form>
</body>
</html>