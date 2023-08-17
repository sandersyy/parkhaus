package logic.Ticket;

import java.util.Date;

public interface ParkhausTicketIF {
    void setTime(Date time);

    public String calcParkingTime(Date currentTime);

    public void reserveTicket();

    public void cancelTicket();

    public Date getTime();
    public String getId();
    public void setId(String id);

    public String getZahlungsart();

    public void setZahlungsart(String zahlungsart);

    public StateTicket getState();

    public void setState(StateTicket state);

    public void setEmail(String email);

    public String getEmail();


}
