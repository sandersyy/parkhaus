import java.util.Random;

public class Auto {
    private TicketImpl ticket;
    private String marke;

    public Auto(TicketImpl ticket){

        this.marke = chooseMarke();
        this.ticket = ticket;
    }
    public Auto(){

        this.marke = chooseMarke();
    }

    public TicketImpl getTicket() {
        return ticket;
    }

    public void setTicket(TicketImpl ticket) {
        this.ticket = ticket;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String toString(){

        return " Marke: "+ this.getMarke() +" , " + " Zeit: "+this.getTicket().getTime().getHours() + "h " +
                this.getTicket().getTime().getMinutes() + "min " +
                " , " + " ID: " + this.getTicket().getId();

    }
    public String chooseMarke(){

        Random rd = new Random();
        String autoMarken[] = {"Ferrari","Maserati","Lamborghini","Pagani", "Königsegg","Bugatti","Bentley","Rolls-Royce"};

        return autoMarken[rd.nextInt(autoMarken.length)];
    }
}
