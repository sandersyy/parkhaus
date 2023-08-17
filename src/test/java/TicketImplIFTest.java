import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketImplIFTest {


    TicketImpl ticket1 = new TicketImpl();
    TicketImpl ticket2 = new TicketImpl();

    @Test
    void calcPriceTest() {

        assertEquals(10.0F, ticket1.calcPrice());
        assertEquals(14.1F, ticket2.calcPrice());
    }
}