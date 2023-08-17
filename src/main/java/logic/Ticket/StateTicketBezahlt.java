package logic.Ticket;

import java.util.Date;

public class StateTicketBezahlt extends StateTicket {

    public StateTicketBezahlt(ParkhausTicket ticket){
        super(ticket);
    }

    @Override
    public float calcPrice(Date currentTime) {
        return ((currentTime.getMonth() - super.ticket.getTime().getMonth() % 12) * (30f * 24f * 5f)) //price per month
                + ((currentTime.getDate()- super.ticket.getTime().getDate() % 30) * 24f * 5f) //price per day
                + (Math.abs(currentTime.getHours() - super.ticket.getTime().getHours() % 24) * 5f) //price per hour
                + (2f);
    }

    @Override
    public String toString(Date currentTime) {
        return  "Beleg:<br>Email Adresse " + super.ticket.getEmail() + "   TicketId: "+super.ticket.getId()+"<br>"
                +"Ankunftszeit im Parkhaus: " + super.ticket.getTime() + "<br>"
                + "Zeitpunkt des Lösens des Tickets: " + currentTime + "<br>"
                + "Ihre Zeit im Parkhaus: " + super.ticket.calcParkingTime(currentTime) + "<br>"
                + "Ihr Preis: " + ticket.getState().calcPrice(currentTime) + "<br>"
                + "Ihre Zahlungsart: " + super.ticket.getZahlungsart() + "<br>";
    }

    @Override
    public int isActive() {
        return -1;
    }


}
