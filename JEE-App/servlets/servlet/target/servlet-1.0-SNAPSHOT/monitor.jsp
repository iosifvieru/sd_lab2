<%@ page contentType="text/html; charset=UTF-8" %>
<html>
	<head>
		<title>Meniu principal</title>
		<meta charset="utf-8" />
	</head>
	<body>
        <h1>Database monitor</h1>
        <form action="./database-monitor" methond="GET">
            <select name="camp">
                <option value="varsta">Varsta</option>
                <option value="nume">Nume</option>
                <option value="prenume">Prenume</option>
            </select>
            Minim: <input type="numeric" name="minim" value="18">
            Maxim: <input type="numeric" name="maxim" value="35">
            <button type="submit">Trimite</button>
        </form>
	</body>
</html>