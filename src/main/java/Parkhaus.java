import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Parkhaus implements ParkhausIF {

    private ArrayList<Auto> parkplaetze;

    private  int capacity;
    private int counter = 0;

    public Parkhaus(int capacity){

        this.parkplaetze= new ArrayList<Auto>();
        this.capacity = capacity;
    }

    @Override
    public void payTicket() { //leave the parking house

        if(counter > 0) //removes first entry if there is one
            parkplaetze.remove(0); counter--;
    }
    @Override
    public String getTicket(char chr) throws IllegalStateException { //enter the parking house
        String result = "";


        if(parkplaetze.size() == capacity){ //throws error if there is no free tickets
            throw new IllegalStateException("Parkhaus ist voll.");
        }
        if(chr == 'K' || chr == 'F' || chr == 'B'){
        if(checkSpecialSlots(chr)){
            counter++;
            parkplaetze.add(new Auto( new TicketImpl(chr)));
            result = "Sie haben das Parkhaus betreten";
        }
        else if(!checkSpecialSlots(chr)){
                result = "Es gibt leider keine freien " + getTicketTypeName(chr) +"parkplätze mehr";
            }
        }
        else if(chr == 'T'){
            counter++;
            parkplaetze.add(new Auto( new TicketImpl(chr)));
            result = "Sie haben das Parkhaus betreten";
        }
        else {
            result = "Kein gültiger Ticket-Typ. Bitte erneut versuchen.";
        }
        return result;

         // add new ticket with 2 hours //toDo: add constructor for Ticket with two int instead of time, implement random time

    }
      public ArrayList<Auto> getParkplaetze() {
        return parkplaetze;
    }

    public void setParkplaetze(ArrayList<Auto> parkplaetze) {
        this.parkplaetze = parkplaetze;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getAverage(){ //returns average price of the current active tickets
        if(counter == 0){
            return 0;
        }
        return getSum() / (this.counter);
    }
    public float getSum(){ //returns sum of all prices of the current active tickets

        float sum = 0.0f;

        for (int j = 0; j < this.counter; j++) {

            sum += parkplaetze.get(j).getTicket().calcPrice();
        }
        return sum;
    }
        public int getfreiplatz(){// return the number of actual free parking places
        return getCapacity()-counter;
        }

        public boolean checkSpecialSlots(char chr){ //checks parkplaetze if there are any free special parking slots available
        int max = 5; // max special slots
            for(Auto auto : parkplaetze){
                if(Objects.equals(chr, auto.getTicket().getId().charAt(0))){ //if ticket id first letter equals str max is decremented by 1
                    max--;
                }
            }
            return max != 0; //if max is not 0 than return true
        }
    public String getTicketTypeName(char a){

        if(a == 'F'){
            return "Frauen";
        }else if(a == 'B'){
            return "Behinderten";
        }else if(a == 'K'){
            return "Kinder";
        }
        return " ";
    }
    public int getFreeSpecialSlots(char chr){

        int max = 5;
        int counter = 0;
        for(int i = 0; i < parkplaetze.size(); i++){

            if(parkplaetze.get(i).getTicket().getId().charAt(0) == chr){

                counter++;
            }
        }
        return max-counter;
    }
    public String getActualPrice(float priceHour, float startPrice){

        return "Aktueller Stundenpreis: "+priceHour+ " , "+" Preis pro angefange Stunde: "+startPrice;
    }
}
