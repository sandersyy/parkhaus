<%@ page import="java.util.ArrayList" %>
<%@ page import="logic.Rezension" %>
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
    <title>Rezensionen</title>
</head>

<body>
<style><%@include file="/WEB-INF/rezensionen.css"%></style>

<header id="main-navigation" role="navigation">
    <nav>
        <div class="top-nav">
            <a href = "index"> HOME </a>
            <a href = "overview"> OVERVIEW </a>
            <a href = "ticket"> TICKET </a>
            <a href = "rezensionen"> FEEDBACK </a>
            <p style="color:white; text-align: right;">Uhrzeit im Parkhaus<span style="color: red;
      background-color: yellow; ">${time}</span></p>
        </div>
    </nav>
</header>
<div class="row">
    <div class="column side">
    </div>
    <div class="column middle">
        <h2>Durchschnittliche Bewertung: ${gesamtPunkte} </h2>
        <div class="scrollDynamicTable">
            <table>
                <thead>
                <tr>
                    <th>Bewertungen</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<Rezension> bewertungen = (ArrayList<Rezension>) request.getAttribute("bewertungen");
                %>
                <%
                    for(Rezension r : bewertungen){
                %>
                <tr>
                    <td>User: <%=r.getUsername()%><br>Punkte: <%=r.getPunkte()%><br>Bewertung: <%=r.getBewertung()%></td>
                </tr>
                <%}%>
                </tbody>
                <tfoot>
                <th> Gesamtanzahl: ${gesamt}</th>
                </tfoot>
            </table>
        </div>
        <p></p>
    </div>

    <div class="column side">
        <h2>Vielen Dank für Ihren Aufenthalt!</h2>
    </div>
</div>
<div class="footer">
    <a class="bewertungVerfassen" href="formularRezension.jsp">Feedback verfassen</a>
    <h3> Impressum & Kontakt</h3>
    <p>Krosses Parkhaus<br> Algenstraße 12<br>Atlantischer Ozean</p><br>
</div>
</body>
</html>




