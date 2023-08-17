package logic.Ticket;

import java.util.Date;

public class ParkhausTicket implements ParkhausTicketIF {

    private Date time;
    private String id;
    private String email=null;
    private StateTicket next;

    private StateTicket state;

    private String zahlungsart;


    public ParkhausTicket(Date time, String name){
        this.time=time;
        this.id = name;
    }

    public ParkhausTicket(){
        time = new Date();
    }

    public ParkhausTicket(char id){
        this.time = new Date();
        this.id = id + "";
    }

    public ParkhausTicket(String id, StateTicket state){
        this.time = null;
        this.id = id;
        this.state = state;
    }

    public ParkhausTicket(String id){
        this.time = new Date();
        this.id = id;
        this.state = null;
    }
    @Override
    public void setTime(Date time) {
        this.time = time;
    }


    public String calcParkingTime(Date currentTime){
        return (currentTime.getDate()- time.getDate()) + "D" + (currentTime.getHours() - time.getHours()) + "H" + (currentTime.getMinutes() - time.getMinutes()) + "M";
    }

    public void reserveTicket(){
        setState(new StateTicketReserviert(this));
    }

    @Override
    public void cancelTicket(){
        setState(new StateTicketUngebucht(this));
    }






    public Date getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZahlungsart() {
        return zahlungsart;
    }

    public void setZahlungsart(String zahlungsart) {
        this.zahlungsart = zahlungsart;
    }

    public StateTicket getState() {
        return state;
    }

    public void setState(StateTicket state) {
        this.state = state;
    }
    public void setEmail(String email){
        this.email=email;
    };
    public String getEmail(){
        return this.email;
    }
}
