package logic;

import logic.Ticket.ParkhausTicket;
import logic.Ticket.StateTicketGebucht;
import org.junit.jupiter.api.Test;
import java.util.Date;

class CovidObserverTest {

    Parkhaus parkhaus = new Parkhaus(100);

    @Test
    void lastHour() {
        Date timeJump = new Date();
        timeJump.setHours((timeJump.getHours() + 1 )% 24);
        for(int i = 0; i < 20; ++i){
            parkhaus.getTicket('T');
            parkhaus.getTicket('T');
            timeJump.setHours((timeJump.getHours() + 1 )% 24);
        }
    }

    @Test
    void lastDasy() {

    }

    @Test
    void isInLastHour() {
        ParkhausTicket parkhausTicket1 = new ParkhausTicket();
        parkhausTicket1.setState(new StateTicketGebucht(parkhausTicket1));
        parkhausTicket1.setTime(new Date());
        parkhausTicket1.getTime().setHours(parkhausTicket1.getTime().getHours() - 2);

    }

    @Test
    void isInLastDay() {
    }
}