package logic;

import logic.Ticket.ParkhausTicket;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

public class CovidObserver { //Erweiterung für Use-Case Diagramm: Corona Kontaktverfolgung im Parkhaus
    public Stream<String> lastHour(Date actualTime, ArrayList<ParkhausTicket> parkhausTickets) {
        return parkhausTickets.stream()
                .filter(parkhausTicket -> parkhausTicket.getState().isActive() == 1) //only booked tickets are used
                .filter(parkhausTicket -> (isInLastHour(parkhausTicket, actualTime))) //filter the tickets in the last hour with helper function
                .map(ParkhausTicket::getEmail); // map to get email
    }

    public Stream<String> lastDasy(Date actualTime, ArrayList<ParkhausTicket> parkhausTickets) {
        return parkhausTickets.stream()
                .filter(parkhausTicket -> parkhausTicket.getState().isActive() == 1) //only booked tickets are used
                .filter(parkhausTicket -> (isInLastDay(parkhausTicket, actualTime))) //filter the tickets in the last day with helper function
                .map(ParkhausTicket::getEmail); //map to get email
    }

    public boolean isInLastHour(ParkhausTicket parkhausTicket, Date actualTime) {
        Date ticketTime = parkhausTicket.getTime(); //time when the ticket was booked
        return (ticketTime.getMinutes() - actualTime.getMinutes() > 0
                && (ticketTime.getHours() + 1) % 24 >= actualTime.getHours() //is ticket Time the same hour or hour before actual Time
                && ticketTime.getDay() == actualTime.getDay() // if ticket Day is the same as actual Day
                && ticketTime.getMonth() == actualTime.getMonth() // if ticket month is the same as actual month
                && ticketTime.getYear() == actualTime.getYear()); //if ticket year is the same as actual year
    }

    public boolean isInLastDay(ParkhausTicket parkhausTicket, Date actualTime) {
        Date ticketTime = parkhausTicket.getTime(); //time when the ticket was booked
        return (ticketTime.getDay() == actualTime.getDay() // if ticket Day is the same as actual Day
                && ticketTime.getMonth() == actualTime.getMonth() // if ticket month is the same as actual month
                && ticketTime.getYear() == actualTime.getYear()) //if ticket year is the same as actual year
                ||
                ((ticketTime.getDay() + 1) % 7 == actualTime.getDay() //second condition: ticket Day is the day before actual Day
                        && (ticketTime.getHours() - actualTime.getTime()) > 0 //if time difference is shorter than 24 hours
                        && ticketTime.getMonth() == actualTime.getMonth() // if ticket month is the same as actual month
                        && ticketTime.getYear() == actualTime.getYear()); //if ticket year is the same as actual year
    }
}
