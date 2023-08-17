package logic;

import logic.Ticket.ParkhausTicket;
import logic.Ticket.StateTicketUngebucht;
import logic.Ticket.StateTicketVerloren;

import java.util.*;

public class Parkhaus implements ParkhausIF {

    private ArrayList<ParkhausTicket> parkplaetze;
    private Date actualTime;
    private  int capacity;
    private int counter = 0;

    public Parkhaus(int capacity){
        this.parkplaetze = new ArrayList<>(capacity);
        for(int i = 0; i < capacity - 15; ++i){ //init normal tickets
            ParkhausTicket tmp = new ParkhausTicket(stringFormat("T", i) );
            tmp.setState(new StateTicketUngebucht(tmp));
            this.parkplaetze.add(tmp);
        }
        for(int i = 0; i < 5; ++i){ //init frauen tickets
            ParkhausTicket tmp = new ParkhausTicket(stringFormat("F", i));
            tmp.setState(new StateTicketUngebucht(tmp));
            this.parkplaetze.add(tmp);
        }
        for(int i = 0; i < 5; ++i){ //init kinder tickets
            ParkhausTicket tmp = new ParkhausTicket(stringFormat("K", i));
            tmp.setState(new StateTicketUngebucht(tmp));
            this.parkplaetze.add(tmp);
        }
        for(int i = 0; i < 5; ++i){ //init behinderten tickets
            ParkhausTicket tmp = new ParkhausTicket(stringFormat("B", i));
            tmp.setState(new StateTicketUngebucht(tmp));
            this.parkplaetze.add(tmp);
        }
        this.capacity = capacity;
        this.actualTime = new Date();
    }

    public Date getActualTime(){
        return actualTime;
    }

    @Override
    public void payTicket(String id) throws IllegalStateException{ //leave the parking house
        if(counter > 0) {
            for (ParkhausTicket parkhausTicket : parkplaetze) { //iterates through parkplaetze
                if (Objects.equals(parkhausTicket.getId(), id)) { //if ticket id equals given id
                    if (parkhausTicket.getState().isActive() == 1||parkhausTicket.getState().isActive() == -2) {
                        //if ticket state is gebucht oder war verloren
                        parkhausTicket.setState(parkhausTicket.getState().getNext()); //set next state which is TicketBezahlt
                    } else throw new IllegalStateException("Ticket mit dieser ID wurde nicht gebucht."); //if there is no ticket found throw execption
                }
            }
        }
        else{
            throw new IllegalStateException("Parkhaus ist leer"); //if there is no ticket booked throw exception
        }
    }
    public void leave(String id){
        for(ParkhausTicket parkhausTicket : parkplaetze){ //iterates through parkplaetze
            if(Objects.equals(parkhausTicket.getId(), id)){ //if ticket id equals given id
                if(parkhausTicket.getState().isActive() == -1){ //if ticket is in bezahlt state
                    parkhausTicket.setState(parkhausTicket.getState().getNext()); //set next state which is TicketUngebucht
                    counter--; //decrease counter
                }

                else throw new IllegalStateException("Ticket wurde noch nicht bezahlt."); //if ticket isn't paid yet throw exception

            }
        }
    }

    public String reserveTicket(Date reservationTime){
        String result = "Ihr Beleg: <br>";
        if(!checkOpeningHours(reservationTime)){ //if parking house is closed return string that states that
            return "Das Parkhaus hat nur von 10:00 bis 22:00 geöffnet.";
        }
        if(parkplaetze.size() == counter){ //throws error if there is no ticket available
            throw new IllegalStateException("Parkhaus ist voll.");
        }
        counter++;
        parkplaetze.get(getNextFreeSlot()).reserveTicket();
        parkplaetze.get(getNextFreeSlot() - 1).setTime(reservationTime);
        result += parkplaetze.get(getNextFreeSlot()-1).getState().toString(reservationTime);
        return result;
    }

    public String cancelTicket(String id){
        if(checkIfTicketExists(id, 2)) {
            ParkhausTicket ticket = parkplaetze.stream()
                    .filter(parkhausTicket ->
                            parkhausTicket.getId().equals(id) && parkhausTicket.getState().isActive() == 2)
                    .findFirst().get();
            ticket.cancelTicket();
            counter--;
            return "Stornierung erfolgreich";
        }
        return "Ticket ID fehlerhaft";
    }

    public boolean checkIfTicketExists(String id, int state){
        return parkplaetze.stream()
                .anyMatch(parkhausTicket ->
                        parkhausTicket.getId().equals(id) && parkhausTicket.getState().isActive() == state);
    }
    @Override
    public String getTicket(char chr) throws IllegalStateException { //enter the parking house
        String result = "Ihr Beleg: <br>";

        if(!checkOpeningHours(actualTime)){ //if parking house is closed return string that states that
            return "Das Parkhaus hat nur von 10:00 bis 22:00 geöffnet.";
        }
        if(parkplaetze.size() == counter){ //throws error if there is no ticket available
            throw new IllegalStateException("Parkhaus ist voll.");
        }
        if(chr == 'K' || chr == 'F' || chr == 'B'){
        if(checkSpecialSlots(chr)){
            counter++;
            parkplaetze.get(getNextFreeSpecialSlot(chr)).getState().nextState();// TODO: huh??
            parkplaetze.get(getNextFreeSpecialSlot(chr) - 1).setTime(actualTime);// TODO: huh??
            result += parkplaetze.get(getNextFreeSpecialSlot(chr)-1).getState().toString(actualTime);
        }
        else if(!checkSpecialSlots(chr)){
                result += "Es gibt leider keine freien " + getTicketTypeName(chr) +"parkplätze mehr";
            }
        }
        else if(chr == 'T'){
            counter++;
            parkplaetze.get(getNextFreeSlot()).getState().nextState();
            parkplaetze.get(getNextFreeSlot() - 1).setTime(actualTime);
            result += parkplaetze.get(getNextFreeSlot()-1).getState().toString(actualTime);
        }
        else {
            result += "Kein gültiger Ticket-Typ. Bitte erneut versuchen.";
        }
        return result;


    }
    public int getNextFreeSlot(){
        for(int i = 0; i < 85; ++i){
            if(parkplaetze.get(i).getState().isActive() == 0 || parkplaetze.get(i).getState().isActive() == -2 ){
                return i;
            }
        }
        return -1;
    }

    public int getNextFreeSpecialSlot(char t){
        for(int i = 85; i < 100; ++i){
            if(parkplaetze.get(i).getState().isActive() == 0){
                if(parkplaetze.get(i).getId().charAt(0) == t){
                    return i;
                }
            }
        }
        return -1;
    }

    public ArrayList<ParkhausTicket> getParkplaetze() {
        return parkplaetze;
    }

    public void setParkplaetze(ArrayList<ParkhausTicket> parkplaetze) {
        this.parkplaetze = parkplaetze;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCounter() {
        return counter;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void activateReservedTickets(){
        parkplaetze.stream().filter(parkhausTicket -> parkhausTicket.getState().isActive() == 2 && parkhausTicket.getTime().before(actualTime)).forEach(
                parkhausTicket -> parkhausTicket.getState().nextState());
    }

    public float getAverage(){

        return (float) parkplaetze.stream().filter(x -> x.getState().calcPrice(actualTime) != 0).mapToDouble(x -> x.getState().calcPrice(actualTime)).average().orElse(0.0f);
    }
    public float getSum(){

        return (float) parkplaetze.stream().mapToDouble(x -> x.getState().calcPrice(actualTime)).reduce(0.0f,(x, y) -> x+y);
    }
    public boolean checkSpecialSlots(char chr){ //checks parkplaetze if there are any free special parking slots available
        return !parkplaetze.stream()
                .filter(parkplatz -> parkplatz.getState()
                        .isActive() >= 1 && parkplatz.getId().charAt(0) == chr)
                .map(parkplatz -> parkplatz.getState()
                        .isActive())
                .reduce(0, Integer::sum).equals(5);
    }
    public int getFreeSlots(){// return the number of actual free parking places
        return getCapacity()-counter;
        }
    public String getTicketTypeName(char a){

        if(a == 'F'){
            return "Frauen";
        }else if(a == 'B'){
            return "Behinderten";
        }else if(a == 'K'){
            return "Kinder";
        } else if (a == 'T') {
            return "";
        }

        throw new IllegalStateException("Kein Ticket mit diesem Kürzel");
    }
    public int getBookedSpecialSlots(char chr){
        return parkplaetze.stream()
                .filter(parkplatz -> parkplatz.getState()
                        .isActive() >= 1 && parkplatz
                        .getId().charAt(0) == chr)
                .map(parkplatz -> parkplatz.getState()
                        .isActive())
                .reduce(0, Integer::sum);
    }
    public String getActualPrice(float priceHour, float startPrice){

        return "Aktueller Stundenpreis: "+priceHour+ ", Preis pro angefange Stunde: "+startPrice;
    }

    public boolean checkOpeningHours(Date date){ //checks if new time is in opening hours
        return date.getHours() < 22 && date.getHours() > 8; //if after 22:00 or before 9 return false
    }
    public String getOpeningstime(Date date){

        String m=checkOpeningHours(date)&&checkOpeningsDay(date)?"Wir sind aktuell geöffnet!":"Wir sind aktuell geschlossen!";
        return "Aktuelle Öffnungszeiten:Montag bis Freitag 9:00 - 22:00 "+m;
    }
    public boolean checkOpeningsDay(Date date){// check if the actual day is and opening day
        return date.getDay()!=6&&date.getDay()!=0;
        //Calendar c=Calendar.getInstance();
        //return c.DAY_OF_WEEK!=7&&c.DAY_OF_WEEK!=1?true:false;//
    }

    public Date addMinutes(int minutesToAdd, Date parkhausTime) throws RuntimeException { // you can add Minutes to the actualTime of the logic.Parkhaus

        if (minutesToAdd < 0){
            throw new RuntimeException("Keine negativen Zahlen addieren bitte");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parkhausTime);
        calendar.add(Calendar.MINUTE, minutesToAdd); // adds the minutes you want to add

        Date newActualTime = calendar.getTime();

        actualTime = newActualTime;

        return newActualTime;
    }
    public boolean jumpTime(Date newActualTime, Date parkhausTime) throws RuntimeException { //damit kann man die actual Time auf ein neues Datum in der Zukunft setzen

        if (newActualTime.after(parkhausTime)|| newActualTime.equals(parkhausTime)){
            parkhausTime = newActualTime;
        }else{
            throw new RuntimeException("Raum Zeit Kontinuum verletzt");
        }
        return true;
    }

    public void setActualTime(Date newTime){
        actualTime = newTime;
        activateReservedTickets();
    }

    public int getTicketIndex(String id){
        for (int i = 0; i < 100; ++i){
            if(Objects.equals(parkplaetze.get(i).getId(), id)){
                return i;
            }
        }
        return -1;
    }
    public ParkhausTicket findLostTicket(String email){
        if (lostTicketExist(email)){
            ParkhausTicket p= parkplaetze.stream()
                        .filter(i->i.getEmail()!=null) //filtern nur über non null Ticket-Emails
                        .findFirst()
                        .get();
            p.setState(new StateTicketVerloren(p));// Ticket Zustand zu verloren wechseln
        return p;}
               else throw new NoSuchElementException("TICKET NICHT GEFUNDEN");

    }
    public boolean lostTicketExist(String email){
        if (counter==0)return false;//verhindert der fall Parkhaus wäre leer
        return parkplaetze.stream()
                .anyMatch((p -> {
                    String pEmail = p.getEmail();
                    return pEmail != null && pEmail.equals(email);//verhindert der fall Email wäre null
                }));
    }
    public ParkhausTicket getLastBookedTicket(char chr){
        if(chr == 'K' || chr == 'F' || chr == 'B'){
            return parkplaetze.get(getNextFreeSpecialSlot(chr)-1);
        }
        if(chr == 'T'){
            return parkplaetze.get(getNextFreeSlot()-1);
        }
        return null;
    }

    public String toString(){ //to String method for all parking house components

        float sum = getSum(); //return sum of all ticket prices in parking house at this time

        float average = getAverage(); //return average of all ticket prices in parking house at this time

        return "<html><body>" + "Die Summe des Tagesumsatzes: " + sum + "<br>" //Summe Tagesumsatz
                + "Der durchschnittliche Tagesumsatz: " + average + "<br>" //Aktueller Tagesumsatz
                + "Aktuell freie Plätze beträgt: " + getFreeSlots() + "<br>" //Aktuelle freie Parkplätze insgesamt
                + "Aktuell belegte Frauenparkplätze: " + getBookedSpecialSlots('F') + "/5<br>" //Aktuelle freie Frauenparkplätze
                + "Aktuell belegte Behindertenparkplätze: " + getBookedSpecialSlots('B') + "/5<br>" //Aktuelle freie Behindertenparkplätze
                + "Aktuell belegte Kinderparkplätze: " + getBookedSpecialSlots('K') + "/5<br>" //Aktuelle freie Kinderparkplätze
                + getOpeningstime(actualTime) +"<br>"//Aktuelle Öffnungzeiten
                + getActualPrice(5,2) + "<br>" //anzeige aktueller Preise
                + "</body></html>"; //ende des html dokuments

    }
    public String stringFormat(String s,int i){
        String res="";
        if (i<10){
            res=s+"00"+i;
        }
        else if (i<100){
            res=s+"0"+i;
        }
        else if (i<1000){
            res=s+i;
        }
        else {
            throw new IllegalStateException("No negative IDs available");
        }
    return res;
    }


}
