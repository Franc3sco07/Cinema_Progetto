package cinema.funzioni;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ValidatoreCampi {

    public final static DateFormat DATEFORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public final static DateFormat NOTIMEFORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static boolean isValidEmail(String email) {
        //dopo la @ non puoi inserire numeri
        if (!email.toLowerCase().matches("((\\w+)(\\.?))+@{1}(([a-zA-z])+(\\.))+[a-zA-z]{2,3}")) {
            return false; //verificare espressione regalare
        }
        return true;
    }

    public static boolean isValidString(String stringa) { //verificare nome e cognome, stringa costituita solo da caratteri
        // if (!stringa.matches("[a-zA-Z0 \\s]*")) {
        if (!stringa.matches("([a-zA-Z]+( ?))+")) {
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(String pass) {
        if (!pass.matches("((?=.+\\d)(?=.+[a-z])(?=.+[A-Z])(?=.+[&%@#?!$]).{6,20})")) {//\d{2,}[&%@#?!$]{1,}
            return false; //[a-zA-Z]{6,}[\d]{2,}
        }//CiaoAA99&, cia77o&AA
        return true;
    }

    // codice fiscale tipo: RSSMRA85T10A562S
    public static boolean isValidCodiceFiscale(String codiceFiscale) {
        if (!codiceFiscale.matches("^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$")) {
            return false;
        }
        return true;
    }

    public static boolean isValidNumberCell(String number) {
        if (!number.matches("[0-9]{10}")) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}



