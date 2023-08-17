package logic;

import logic.Ticket.ParkhausTicket;
import logic.Ticket.StateTicketGebucht;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkhausTicketIFTest {


    ParkhausTicket ticket1 = new ParkhausTicket();
    ParkhausTicket ticket2 = new ParkhausTicket();

    Date date1 = new Date();
    Date date2 = new Date();


    @Test
    void calcPriceTest() {
        date1.setHours(0);
        date1.setMonth(Calendar.JANUARY);
        date1.setMinutes(0);
        date2.setHours(0);
        date2.setMonth(Calendar.JANUARY);
        date2.setMinutes(0);
        ticket1.setState(new StateTicketGebucht(ticket1));
        ticket1.setTime(date2);
        assertEquals(2, ticket1.getState().calcPrice(date1));

        date1.setHours(4);
        assertEquals(22, ticket1.getState().calcPrice(date1));


        date1.setMinutes(30);
        assertEquals(22, ticket1.getState().calcPrice(date1));

        date1.setMonth(Calendar.FEBRUARY);
        assertEquals(3622, ticket1.getState().calcPrice(date1));


        }
    }