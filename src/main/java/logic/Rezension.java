package logic;

import java.util.ArrayList;

public class Rezension {
    private int punkte; // soll im range 1-5 angegeben werdenn
    private String bewertung; // kurzer Text maximal 100 Zeichen
    private String username;

    private ArrayList<Rezension> allRezension = new ArrayList<Rezension>();

    public Rezension(int punkte, String bewertung, String username) throws IllegalStateException {
        if (0> punkte || punkte >5){
            throw new IllegalStateException("Sterne bitte im Bereich 0-5 angeben");
        }
        if (bewertung.length()>=500){
            throw new IllegalStateException("Bewertung darf maximal 100 Zeichen lang sein");
        }
        if ( username.isEmpty() || username.equals(" ")){
            this.username = "anonymous2s";
        }else{
            this.username = username;
        }

        this.punkte = punkte;
        this.bewertung = bewertung;
    }
    public String getUsername(){

        return this.username;
    }

    public int getPunkte() {
        return punkte;
    }

    public String getBewertung() {
        return bewertung;

    }

}
