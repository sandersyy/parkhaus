<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/style.css"%></style>

<head>
    <title> Parkhaus </title>
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
        <h2>Navigation</h2>
        <p>Zum Navigieren bitte die gewünschte Auswahl im Reiter oben links einstellen.</p>
    </div>
    <div class="column middle">
        <h2>KROSSES PARKHAUS</h2>
        <p> Willkommen bei Krosses Parkhaus.</p>
        <p><img src="image/sponge_salut.png" alt="spongebob says hello"></p>
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