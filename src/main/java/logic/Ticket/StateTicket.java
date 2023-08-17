package logic.Ticket;

import java.util.Date;

public abstract class StateTicket {
    protected StateTicket next;
    protected ParkhausTicket ticket;

    StateTicket(){
        this.ticket = new ParkhausTicket();
        this.next = null;
    }
    StateTicket(ParkhausTicket p){
        this.ticket = p;
    }

    public void nextState(){
        ticket.setState(next);
    }

    public abstract float calcPrice(Date currentTime);

    public abstract String toString(Date currentTime);

    public abstract int isActive();

    public StateTicket getNext() {
        return next;    }
}
