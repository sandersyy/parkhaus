<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 25.05.23
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/ticketVerloren.css"%></style>
<head>
    <title>Ticket-Verloren</title>
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
        <h2>Oeffnungszeiten</h2>
    </div>
    <div class="column middle">
        <h2>Ticket-Verloren</h2>
        <p style="color: red;  background-color: yellow";>${warning}</p>
        <p style="color: green;  background-color: lightskyblue";>${erfolg}</p>
        <form action = "verloren" method = post>

            <label for="email">Bitte geben Sie Ihre Email-Adresse:</label>
            <input type="email" id="email" name="email"><br><br>
            <input type="submit" value="senden">
        </form>
        <p><img src="image/sponge_fernglas.png" alt="spongebob says hello"></p>
        <p></p>
    </div>

    <div class="column side">
        <h2>Vielen Dank für ihre Buchung!</h2>
        <p>${Beleg}</p>
    </div>
</div>
<div class="footer">
    <h3>Impressum & Kontakt</h3>
    <p>Krosses Parkhaus<br> Algenstraße 12<br>Atlantischer Ozean</p><br>
</div>
</body>
</html>
