package logic.Ticket;

import java.util.Date;

public class StateTicketUngebucht extends StateTicket {

    public StateTicketUngebucht(ParkhausTicket ticket){
        super(ticket);
        super.next = new StateTicketGebucht(ticket);
        super.next.next = new StateTicketBezahlt(ticket);
        super.next.next.next = this;
    }


    @Override
    public float calcPrice(Date currentTime) {
        return 0;
    }

    @Override
    public String toString(Date currentTime) {
        return "Keine Buchung erfolgt.";
    }

    @Override
    public int isActive() {
        return 0;
    }
}
