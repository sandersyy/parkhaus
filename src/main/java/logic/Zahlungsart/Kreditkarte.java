package logic.Zahlungsart;

import java.util.regex.Pattern;

public class Kreditkarte extends Zahlungsart{

    private String kartenName;
    private String kreditkartennummer;

    public Kreditkarte(){}
    public String getKartenname(){
        return this.kartenName;
    }
    public String getKreditkartennummer(){
        return this.kreditkartennummer;
    }
    @Override
    public boolean checkData(String bankName, String nummer) {

        if(Pattern.matches("^[A-Z]{1}[a-z]*$",bankName) && Pattern.matches("^(\\d{16})$",nummer)){
            this.kartenName = bankName;
            this.kreditkartennummer = nummer;
            return true;
        }
        return false;
    }
}
