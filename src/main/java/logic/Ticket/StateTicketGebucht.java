package logic.Ticket;

import java.util.Date;

public class StateTicketGebucht extends StateTicket {

    public StateTicketGebucht(ParkhausTicket ticket){
        super(ticket);
    }

    @Override
    public String toString(Date currentTime) {
        return  "Ankunftszeit im Parkhaus: " + super.ticket.getTime() + "<br>"
                + "Der aktuelle Preis pro Stunde: 5€ <br>"
                + "Der aktuelle Preis pro angefangene Stunde: 2€ <br>"
                + "Ihre Ticket-ID (BITTE MERKEN): " + super.ticket.getId() + "<br>";
    }

    @Override
    public int isActive() {
        return 1;
    }

    public float calcPrice(Date currentTime) { //calculates price of ticket using the starting time and the actual time

        return ((currentTime.getMonth() - super.ticket.getTime().getMonth() % 12) * (30f * 24f * 5f)) //price per month
                + ((currentTime.getDate()- super.ticket.getTime().getDate() % 30) * 24f * 5f) //price per day
                + (Math.abs(currentTime.getHours() - super.ticket.getTime().getHours() % 24) * 5f) //price per hour
                + (2f);

    }

}
