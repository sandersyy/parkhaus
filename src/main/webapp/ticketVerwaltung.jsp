<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 18.06.23
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/ticketVerwaltung.css"%></style>



<head>
    <title> Ticket-Verwaltung </title>
</head>
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
        <h2>Herzlich Willkommen bei der Ticket Verwaltung!</h2>
    </div>
    <div class="column middle">
        <h2>Ticket - Verwaltung</h2>
        <p> <p>Zur Ticket-Bezahlung: <a href = "bezahlen"> Ticket-Bezahlung</a> </p><br>
        <p>Zur Ticket-Buchung: <a href = "buchung"> Ticket-Buchung </a> </p><br>
        <p> Zur Ticket-Reservierung: <a href = "reservierung"> Ticket-Reservierung </a> </p><br>.</p>
        <p><img src="image/sponge_patrick_boot.png" alt="spongebob und patrick auf boot"></p>
        <p></p>
    </div>

    <div class="column side">
        <h2>*Hier könnte Ihre Werbung stehen*</h2>
        <p>Bei Interesse bitte bei Team 9 melden!</p>
    </div>
</div>
<div class="footer">
    <h3>Impressum & Kontakt</h3>
    <p>Krosses Parkhaus<br> Algenstraße 12<br>Atlantischer Ozean</p><br>
</div>
</body>
</html>