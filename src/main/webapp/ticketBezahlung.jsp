<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 18.06.23
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/ticketBezahlung.css"%></style>
<head>
    <title>Ticket-Bezahlung</title>
</head>
<body>
<div class="top-nav" >
    <a href = ""> HOME </a>
    <a href = "overview"> OVERVIEW </a>
    <a href = "ticket"> TICKET </a>
    <a href = "rezensionen"> FEEDBACK </a>
    <p style="color:white; text-align: right;">Uhrzeit im Parkhaus<span style="color: red;
      background-color: yellow; ">${time}</span></p>
</div>


<div class="row">
    <div class="column side">
        <h2>Side</h2>
    </div>
    <div class="column middle">
        <h2>Ticket - Bezahlung</h2>
        <form action = "bezahlen" method = post>
            <label for="ticketID">Bitte geben Sie die Ticket-ID ein:</label>
            <input type="text" id="ticketID" name="ticketID"><br><br>
            <label for="radioButtonLeave">Parkhaus verlassen</label>
            <input type="radio" name="selectedOption" value="leave" id="radioButtonLeave">
            <input class="inputOption" type="submit" name="control" value="Auswahl">
            <p>Ticket verloren? <a href = "verloren"> Ticket verloren </a> </p><br>
        </form>
        <p><img src="image/mrkrabs.png" alt="mrkrabs says hello"></p>
    </div>

    <div class="column side">
        <h2>Vielen Dank für Ihren Aufenthalt!</h2>
        ${Beleg}
    </div>
</div>
<div class="footer">
    <h3>Impressum & Kontakt</h3>
    <p>Krosses Parkhaus<br> Algenstraße 12<br>Atlantischer Ozean</p><br>
</div>
</body>
</html>
