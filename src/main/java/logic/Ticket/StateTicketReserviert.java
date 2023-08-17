package logic.Ticket;

import java.util.Date;

public class StateTicketReserviert extends StateTicket{

    public StateTicketReserviert(ParkhausTicket ticket){
        super(ticket);
        super.next = new StateTicketGebucht(ticket);
    }

    @Override
    public float calcPrice(Date currentTime) {
        return 0;
    }

    @Override
    public String toString(Date currentTime) {
        return "Ihre Ticket-ID: " +
                super.ticket.getId() +
                "<br>Ihre Startzeit: " +
                super.ticket.getTime() +
                "<br>Bei Antritt wird die Zeit verrechnet. Bitte stornieren falls nicht gewuenscht.";
    }

    @Override
    public int isActive() {
        return 2;
    }
}
