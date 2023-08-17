import logic.Parkhaus;
import org.junit.jupiter.api.Test;
import web.ParkhausServlet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class ParkhausIFTest {

    Parkhaus p1 = new Parkhaus(100);
    Date time = new Date();
    Date time1 = new Date();
    Date time2 = new Date();


    @Test
    void payTicket() {
        p1 = new Parkhaus(100); //Neue Instanz wegen Kapaztitätsüberschreitung


        assertEquals(0, p1.getCounter());
        p1.getTicket('T');
        p1.getTicket('T');
        p1.payTicket("T000");
        p1.leave("T000");
        assertEquals(1, p1.getCounter());
        p1.payTicket("T001");
        p1.leave("T001");
        assertEquals(0, p1.getCounter());
        assertThrows(IllegalStateException.class, () -> p1.payTicket("T000"), "No ISE?");
    }

    @Test
    void getTicket() {
        assertEquals(0, p1.getCounter());
        for(int i = 0; i <10; ++i){
            assertEquals(i, p1.getCounter());
            p1.getTicket('T');
            assertEquals(i + 1, p1.getCounter());
        }
        assertThrows(IllegalStateException.class, () -> p1.getTicket('T'));
    }

    @Test
    void leave(){
        p1.getTicket('T');
        p1.getTicket('T');
        assertEquals(2, p1.getCounter());
        p1.payTicket("T000");
        p1.payTicket("T001");
        assertEquals(2, p1.getCounter());
        p1.leave("T000");
        assertEquals(1, p1.getCounter());
        p1.leave("T001");
        assertEquals(0, p1.getCounter());
    }

    @Test
    void getNextFreeSlot(){
        assertEquals(0 , p1.getNextFreeSlot());
        p1.getTicket('T');
        assertEquals(1 , p1.getNextFreeSlot());
        p1.getTicket('T');
        assertEquals(2 , p1.getNextFreeSlot());
        p1.getTicket('T');
    }

    @Test
    void getNextFreeSpecialSlot(){
        assertEquals(85 , p1.getNextFreeSpecialSlot('F'));
        p1.getTicket('F');
        assertEquals(86, p1.getNextFreeSpecialSlot('F'));
        p1.getTicket('F');
        assertEquals(87, p1.getNextFreeSpecialSlot('F'));
        assertEquals(95, p1.getNextFreeSpecialSlot('B'));
        p1.getTicket('B');
        assertEquals(96, p1.getNextFreeSpecialSlot('B'));
    }

    @Test
    void getTicketIndex(){
        assertEquals(0, p1.getTicketIndex("T000"));
        assertEquals(1, p1.getTicketIndex("T001"));
        assertEquals(2, p1.getTicketIndex("T002"));
        assertEquals(87, p1.getTicketIndex("F002"));
    }

    @Test
    void getOpeningstime(){
        if( (time.getHours() < 22 && time.getHours() > 8) && (time.getDay() != 6 && time.getDay() != 0)){
            assertEquals("Aktuelle Öffnungszeiten:Montag bis Freitag 9:00 - 22:00 Wir sind aktuell geöffnet!", p1.getOpeningstime(time));
        }else{
            assertEquals("Aktuelle Öffnungszeiten:Montag bis Freitag 9:00 - 22:00 Wir sind aktuell geschlossen!", p1.getOpeningstime(time));
        }
    }

    @Test
    void addMinutes(){
        assertEquals(time, p1.getActualTime());

        p1.addMinutes(10, p1.getActualTime());
        assertNotEquals(time, p1.getActualTime());

        if(time.getMinutes()<50)
            assertEquals(time.getMinutes(), p1.getActualTime().getMinutes()-10);

    }


    @Test
    void stringFormat(){

        assertEquals("T001", p1.stringFormat("T", 1));
        assertEquals("helloworld777", p1.stringFormat("helloworld", 777));
        assertEquals("eierkuchen mit speck 088", p1.stringFormat("eierkuchen mit speck ", 88));

    }

    @Test
    void getAverage() {

        assertEquals(0.0,p1.getAverage());
        p1.getTicket('T');
        p1.getTicket('T');
        p1.getTicket('T');
        assertEquals(2.0,p1.getAverage());

    }
    @Test
    void getSum() {
        p1.getTicket('T');
        p1.getTicket('T');
        assertEquals(4.0,p1.getSum());
    }
    @Test
    void getFreeSlots() {
        for(int i = 0; i <10; ++i) {
            assertEquals(p1.getCapacity() - i, p1.getFreeSlots());
            p1.getTicket('T');
            assertEquals(p1.getCapacity() - (i + 1), p1.getFreeSlots());
        }
    }

    @Test
    void checkSpecialSlots() {
        p1 = new Parkhaus(20); //Neue Instanz wegen Kapaztitätsüberschreitung
        for(int i = 0; i <5; ++i) { //Test Frauenparkplatz
            assertTrue(p1.checkSpecialSlots('F'));
            p1.getTicket('F');
        }
        assertFalse(p1.checkSpecialSlots('F'));

        for(int i = 0; i <5; ++i) { //Test Behindertenparkplatz
            assertTrue(p1.checkSpecialSlots('B'));
            p1.getTicket('B');
        }
        assertFalse(p1.checkSpecialSlots('B'));

        for(int i = 0; i <5; ++i) { //Test Kinderparkplatz
            assertTrue(p1.checkSpecialSlots('K'));
            p1.getTicket('K');
        }
        assertFalse(p1.checkSpecialSlots('K'));
    }

    @Test
    void getTicketTypeName() {
        assertEquals("Frauen", p1.getTicketTypeName('F'));
        assertEquals("Behinderten", p1.getTicketTypeName('B'));
        assertEquals("Kinder", p1.getTicketTypeName('K'));
        assertEquals("", p1.getTicketTypeName('T'));
        assertThrows(IllegalStateException.class, () -> p1.getTicketTypeName('C'));
    }

    @Test
    void getFreeSpecialSlots() {
        p1 = new Parkhaus(20); //Neue Instanz wegen Kapaztitätsüberschreitung
        for(int i = 0; i <5; ++i) { //Test Frauenparkplatz
            assertEquals(5 - i, p1.getBookedSpecialSlots('F'));
            p1.getTicket('F');
            assertEquals(5 - (i + 1), p1.getBookedSpecialSlots('F'));
        }

        for(int i = 0; i <5; ++i) {
            assertEquals(5 - i, p1.getBookedSpecialSlots('B')); //Test Beindertenparkplatz
            p1.getTicket('B');
            assertEquals(5 - (i + 1), p1.getBookedSpecialSlots('B'));
        }

        for(int i = 0; i <5; ++i) {
            assertEquals(5 - i, p1.getBookedSpecialSlots('K')); //Test Kinderparkplatz
            p1.getTicket('K');
            assertEquals(5 - (i+1), p1.getBookedSpecialSlots('K'));
        }
    }

    @Test
    void getActualPrice() {
        assertEquals("Aktueller Stundenpreis: 5.0, Preis pro angefange Stunde: 2.0", p1.getActualPrice(5f, 2f));
    }

    @Test
    void checkOpeningHours(){

        time.setHours(10);
        assertTrue(p1.checkOpeningHours(time));
        time.setHours(22);
        assertFalse(p1.checkOpeningHours(time));
        time.setHours(9);
        assertFalse(p1.checkOpeningHours(time));
    }

    @Test
    void checkOpeningTime(){

        time.setHours(10);
        time.setDate(4);
        assertEquals("Aktuelle Öffnungszeiten:Montag bis Freitag 9:00 - 22:00 wir sind aktuell geöffnet!",p1.getOpeningstime(time));
        time.setHours(3);
        time.setDate(0);
        assertEquals("Aktuelle Öffnungszeiten:Montag bis Freitag 9:00 - 22:00 wir sind aktuell geschlossen!",p1.getOpeningstime(time));

    }
    @Test
    void checkOpeningsDay(){

        time.setDate(5);
        assertTrue(p1.checkOpeningsDay(time));
        time.setDate(6);
        assertFalse(p1.checkOpeningsDay(time));
    }
    @Test
    void checkAddMinutes(){

        time1.setMinutes(0);
        time2.setMinutes(0);
        time = p1.addMinutes(10,time1);
        time2.setMinutes(10);
        assertEquals(time2.getMinutes(),time.getMinutes());
        assertThrows(RuntimeException.class, () -> p1.addMinutes(-10,time));

    }
    @Test
    void checkJumpTime(){
        time1.setMinutes(40);
        time2.setMinutes(23);
        assertTrue(p1.jumpTime(time1,time2));
        time1.setMinutes(10);
        time2.setMinutes(23);
        assertThrows(RuntimeException.class, () -> p1.jumpTime(p1.addMinutes(10,time1),time2));
        assertTrue(p1.jumpTime(time,time));
    }
    @Test
    void convertTimeTest(){
        Date date = new Date();
        System.out.println(date);
        String formattedTime = ParkhausServlet.convertDateToString(date);
        System.out.println(formattedTime);
    }
    @Test
    void convertStringToDateTest(){
        Date date = new Date();
        System.out.println("actual date: " + date);

        String formattedTime = ParkhausServlet.convertDateToString(date);
        System.out.println("nach erster umwandlung: " + formattedTime);

        Date newDate = ParkhausServlet.convertStringToDate(formattedTime);
        System.out.println("nach zweiter umwandlung: " + newDate);

    }
    @Test
    void reserveTicketTest(){
        assertEquals("Ihr Beleg: <br>Ihre Ticket-ID: F000<br>Ihre Startzeit: "+time+"<br>Bei Antritt wird die Zeit verrechnet. Bitte stornieren falls nicht gewuenscht.",p1.reserveTicket(p1.getParkplaetze().get(0).getTime()));
        assertEquals(2,p1.getParkplaetze().get(0).getState().isActive());
    }
    @Test
    void cancelTicketTest(){
        p1.reserveTicket(p1.getParkplaetze().get(0).getTime());
        assertEquals("Stornierung erfolgreich",p1.cancelTicket(p1.getParkplaetze().get(0).getId()));
        assertEquals(0,p1.getParkplaetze().get(0).getState().isActive());
    }
    @Test
    void checkIfTicketExistsTest(){

        p1.reserveTicket(p1.getParkplaetze().get(0).getTime());
        assertTrue(p1.checkIfTicketExists(p1.getParkplaetze().get(0).getId(),p1.getParkplaetze().get(0).getState().isActive()));
        assertFalse(p1.checkIfTicketExists("T1000",2));

    }
    @Test
    void findLostTicketTest(){
        p1 = new Parkhaus(100);
        String email = "fQ@j.com";
        assertEquals(0, p1.getCounter());
        p1.getTicket('T');
        p1.getLastBookedTicket('T').setEmail(email);
        assertTrue(p1.lostTicketExist(email));
        assertEquals("fQ@j.com", p1.findLostTicket(email).getEmail());

        p1.getTicket('K');
        assertEquals(2, p1.getCounter());

    }

}