package logic;

import logic.Ticket.ParkhausTicket;

import java.util.ArrayList;
import java.util.Date;

public interface ParkhausIF {


    void payTicket(String id) throws IllegalStateException;

    String getTicket(char chr) throws IllegalStateException;

    Date getActualTime();

    void leave(String id);

    String reserveTicket(Date reservationTime);

    String cancelTicket(String id);

    int getNextFreeSlot();

    int getNextFreeSpecialSlot(char t);

    ArrayList<ParkhausTicket> getParkplaetze();

    void setParkplaetze(ArrayList<ParkhausTicket> parkplaetze);

    int getCapacity();

    int getCounter();

    void setCapacity(int capacity);

    float getAverage();

    float getSum();

    int getFreeSlots();

    boolean checkSpecialSlots(char chr);

    String getTicketTypeName(char a);

    int getBookedSpecialSlots(char chr);

    String getActualPrice(float priceHour, float startPrice);

    boolean checkOpeningHours(Date date);

    String getOpeningstime(Date date);

    boolean checkOpeningsDay(Date date);

    Date addMinutes(int minutesToAdd, Date parkhausTime) throws RuntimeException;

    boolean jumpTime(Date newActualTime, Date parkhausTime) throws RuntimeException;

    void setActualTime(Date newTime);

    int getTicketIndex(String id);

    String toString();

    String stringFormat(String s,int i);

    boolean checkIfTicketExists(String id, int state);
}
