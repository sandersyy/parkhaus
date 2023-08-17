package logic.Zahlungsart;

import java.util.regex.Pattern;

public class Bankkarte extends Zahlungsart{

    private String kartenName;
    private String ibanNummer;

    public Bankkarte(){}
    public String getKartenname(){
        return this.kartenName;
    }
    public String getBanknummer(){
        return this.ibanNummer;
    }
    @Override
    public boolean checkData(String bankName, String nummer) {

        if(Pattern.matches("^[A-Z]{1}[a-z]*$",bankName)&& Pattern.matches("^DE(\\d{12})",nummer)){
            this.kartenName = bankName;
            this.ibanNummer = nummer;
            return true;
        }
        return false;
    }

}
