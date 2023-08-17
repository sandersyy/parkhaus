<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="/WEB-INF/overview.css"%></style>
<body>
<head>
    <title> Overview </title>
</head>
<div class="top-nav">
    <a href = "index"> HOME </a>
    <a href = "overview"> OVERVIEW </a>
    <a href = "ticket"> TICKET </a>
    <a href = "rezensionen"> FEEDBACK </a>
    <p style="color:white; text-align: right;">Uhrzeit im Parkhaus<span style="color: red;
                background-color: yellow; ">${time}</span></p>
</div>


<div class="row">
    <div class="column side">
        <h2>Parkhaus-Informationen</h2>
        <p>${parkhausInformationen}</p>
    </div>
    <div class="column middle">
        <h2>Zeiteinstellung</h2>
        <form action = "overview" method = post >
            <input type="hidden" name="aktion" value="timewarp">
            <input type="datetime-local" step="1" name="zeitpunkt"
                   value="${actualTime}">
            <input type="submit" value="Timewarp">
        </form>
        <form action = "overview" name = "aktion" method=post>
            <input type= "submit" name = "aktion" value="plus15Min">
            <input type= "submit" name = "aktion" value="plus30Min">
            <input type= "submit" name = "aktion" value="plus60Min">
        </form>

        <label for="resetParkhaus">Setze das Parkhaus und die Zeit zurück:</label>
        <form action="overview" method = post>
            <input type="submit" id="resetParkhaus" name="aktion" value="Reset">
        </form>
        <p> Bitte nicht in die Vergangenheit reisen.</p>
        <p>Es verletzt das Raum-Zeit-Kontinuum.</p>
        <p><img src="image/plankton.png" alt="spongebob says hello"></p>
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