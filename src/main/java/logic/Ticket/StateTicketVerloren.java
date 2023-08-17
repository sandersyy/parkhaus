package logic.Ticket;

import java.util.Date;

public class StateTicketVerloren  extends StateTicket{


    public StateTicketVerloren(ParkhausTicket ticket) {

        super(ticket);
        super.next= new StateTicketBezahlt(ticket);
        super.next.next = new StateTicketUngebucht(ticket);
    }

    @Override
    public float calcPrice(Date currentTime) {
        return 0;
    }

    @Override
    public String toString(Date currentTime) {
        return null;
    }

    @Override
    public int isActive() {
        return -2;
    }
}
