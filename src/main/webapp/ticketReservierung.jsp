<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 18.06.23
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/ticketReservierung.css"%></style>
<head>
    <title>Ticket-Reservierung</title>
</head>
<body>
<div class="top-nav" >
    <a href = "index"> HOME </a>
    <a href = "overview"> OVERVIEW </a>
    <a href = "ticket"> TICKET </a>
    <a href = "rezensionen"> FEEDBACK </a>
    <p style="color:white; text-align: right;">Uhrzeit im Parkhaus
        <span style="color: red;  background-color: yellow; ">${time}</span></p>
</div>

<div class="row">
    <div class="column side">
        <h2> </h2>
    </div>
    <div class="column middle">
        <h2>KROSSES PARKHAUS</h2>
        <form action = "reservierung" method = post >
            <input type="hidden" name="aktion" value="reservieren">
            <input type="datetime-local" step="1" name="zeitpunkt"
                   value="${reservationTime}">
            <input type="submit" value="Reservieren">
        </form>
        <form action = "reservierung" method = post>
            <label for="ticketID">Bitte geben Sie die Ticket-ID ein:</label>
            <input type="text" id="ticketID" name="ticketID"><br><br>
            <label for="Stornierung">Reservierung stornieren</label>
            <input class="inputOption" id="Stornierung"type="submit" name="aktion" value="stornieren">
        </form>
        <img src="image/blaubarsch_meerjungfrauenmann.png" alt="Meerjungfrauenmann und Blaubarschbube">
    </div>

    <div class="column side">
        <h2>Vielen Dank für Ihre Reservierung!</h2>
        <p>${Beleg}</p>
    </div>
</div>
<div class="footer">
    <h3>Impressum & Kontakt</h3>
    <p>Krosses Parkhaus<br> Algenstraße 12<br>Atlantischer Ozean</p><br>
</div>

</body>
</html>
