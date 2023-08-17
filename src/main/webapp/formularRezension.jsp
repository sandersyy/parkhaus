<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 26.05.23
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bewertung verfassen</title>
</head>
<body>
<style><%@include file="/WEB-INF/formularRezension.css"%></style>
<div class="completeFeedback"><form action = "rezensionen" method = post>
    <h2 class="h2">Formular für Bewertung</h2>
    <label type="text" for="username"></label><br>
    <input class="user" type="text" id="username" name="username" placeholder="Username eingeben (optional)"><br>
    <label type="text" for="sterne"></label><br>
    <input class="points" type="text" id="sterne" name="sterne" placeholder="Punkte 0-5"><br>
    <textarea class="feedback"  type="text" id="bewertung" name="bewertung" placeholder="Bewertung verfassen"></textarea><br>
    <input class="button" type="submit" value="Bewertung abschicken"><br>
    <h4>${message}</h4>
</form></div>
<div class="footer">
    <a href = "index"> Home </a><br>
    <h3> Impressum & Kontakt</h3>
    <p>Krosses Parkhaus<br> Algenstraße 12<br>Atlantischer Ozean</p><br>
</div>
</body>
</html>
